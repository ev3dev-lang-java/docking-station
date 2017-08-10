package examples;

import ev3dev.sensors.Battery;
import ev3dev.sensors.ev3.EV3IRSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jabrena on 10/8/17.
 */
public @Slf4j class Aproximation1 {

    //Robot Configuration
    private static final EV3IRSensor ir1 = new EV3IRSensor(SensorPort.S1);
    private static final PilotConfig pilotConf = new PilotConfig();
    private static final DifferentialPilot pilot = pilotConf.getPilot();

    //Configuration
    private static int HALF_SECOND = 500;

    public static void main(String[] args){

        //To Stop the motor (Example: in case of pkill java for example)
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                pilot.stop();
                log.info("{}", Battery.getInstance().getVoltage());
                log.info("Catakroker!!!");
            }
        }));

        final SampleProvider sp = ir1.getSeekMode();

        float heading = 0;
        float distance = 0;

        //Control loop
        final int iteration_threshold = 50;
        for(int i = 0; i <= iteration_threshold; i++) {

            float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);

            heading = sample[0];
            distance = sample[1];

            log.info("Iteration: {}", i);
            log.info("Beacon Channel 1: Heading: {}, Distance: {}", heading, distance);
            log.info("Voltage: {}", Battery.getInstance().getVoltage());

            if(heading != 0){
                if(heading > 0){
                    pilot.rotate(heading);
                }else{
                    pilot.rotate(heading);
                }
            }

            if(Math.abs(heading) < 5){
                pilot.travel(distance/2);
            }

            Delay.msDelay(HALF_SECOND);
        }

        pilot.stop();
        System.exit(0);

    }
}
