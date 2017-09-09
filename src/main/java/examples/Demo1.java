package examples;

import com.intrbiz.iot.hs110.HS110Client;
import com.intrbiz.iot.hs110.model.GetRealtime;
import ev3dev.actuators.lego.motors.Motor;
import ev3dev.sensors.Battery;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jabrena on 9/9/17.
 */
public @Slf4j class Demo1 {

    private static final int MAX_ITERATIONS = 20;

    //Robot Configuration
    private static final PilotConfig pilotConf = new PilotConfig();
    private static final DifferentialPilot pilot = pilotConf.getPilot();

    //EV3 Battery threshold
    //private static float EV3BATTERY_WARN_LEVEL = 7.60f;
    private static float EV3BATTERY_WARN_LEVEL = 8.00f;
    private static float EV3BATTERY_OK_LEVEL = 8.15f;
    private static float EV3BATTERY_UNDOCK_LEVEL = 8.25f;
    private static float EV3BATTERY_MAX_LEVEL = 8.50f;

    //HS110
    private static final String HS110_CONFIG_FILE = "HS110.properties";
    private static final String HS110_CONFIG_IP_FIELD = "ip";
    private static HS110Client hs110;

    //MOTORS Used to Uncharge quickly
    private static RegulatedMotor extraMotor1 = Motor.B;
    private static RegulatedMotor extraMotor2 = Motor.C;

    public static void main(String[] args) throws Exception {

        LOGGER.info("Running a simple FMS to Recharge an EV3 battery");

        //To Stop the motor (Example: in case of pkill java for example)
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                pilot.stop();
                LOGGER.info("{}", Battery.getInstance().getVoltage());
                LOGGER.info("Catakroker!!!");

                extraMotor1.stop();
                extraMotor2.stop();
            }
        }));

        extraMotor1 = Motor.B;
        extraMotor2 = Motor.C;

        final Battery EV3Battery = Battery.getInstance();

        final String hs110Location = getHS110Location();
        hs110 = new HS110Client(hs110Location);

        enableLed(0);

        float ev3Voltage = 0.0f;
        boolean charging = false;
        int iteration = 0;

        while(true) {

            iteration++;
            extraMotor1.stop();
            extraMotor2.stop();

            ev3Voltage = EV3Battery.getVoltage();
            charging = updateCharging();
            LOGGER.info("Iteration: {}, EV3 Battery: {}, Charging: {}", iteration, ev3Voltage, charging);

            if (ev3Voltage <= EV3BATTERY_WARN_LEVEL) {
                LOGGER.info("STATE: Go to Docking Station");

                pilot.forward();
                if (charging) {
                    pilot.stop();
                }
            } else if(
                    (ev3Voltage > EV3BATTERY_WARN_LEVEL) &&
                    (ev3Voltage <= EV3BATTERY_OK_LEVEL)) {
                LOGGER.info("STATE: Charging process");

                if(charging) {
                    pilot.stop();
                    Delay.msDelay(10000);
                }else {
                    pilot.forward();
                }

            } else if (
                    (ev3Voltage > EV3BATTERY_OK_LEVEL) &&
                    (ev3Voltage <= EV3BATTERY_UNDOCK_LEVEL)) {

                LOGGER.info("STATE: Charging Good Level");

                if (charging) {
                    pilot.stop();
                    Delay.msDelay(10000);
                } else {
                    pilot.forward();
                }

            } else if (
                    (ev3Voltage > EV3BATTERY_UNDOCK_LEVEL) &&
                    (ev3Voltage <= EV3BATTERY_MAX_LEVEL)) {

                Delay.msDelay(30000);

                if(charging){
                    LOGGER.info("STATE: Undocking process");

                    pilot.travel(-20);
                    pilot.stop();
                }

                charging = updateCharging();
                if(!charging){
                    LOGGER.info("STATE: Uncharging process");
                    //extraMotor1.forward();
                    extraMotor2.forward();
                }

                Delay.msDelay(5000);

            }

        }

    }

    private static boolean updateCharging() throws Exception {
        GetRealtime response = hs110.consumption();
        return (response.getPower() == 0.0d) ? false : true;
    }

    private static String getHS110Location() throws IOException {
        final InputStream in = ClassLoader.getSystemResourceAsStream(HS110_CONFIG_FILE);
        final Properties prop = new Properties();
        prop.load(in);
        in.close();

        return prop.getProperty(HS110_CONFIG_IP_FIELD);
    }

    private static void enableLed(final int i) throws Exception {
        if (i % 2 == 0) {
            hs110.ledOn();
        } else {
            hs110.ledOff();
        }
    }

}
