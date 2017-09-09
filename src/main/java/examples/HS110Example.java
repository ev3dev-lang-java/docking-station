package examples;

import com.intrbiz.iot.hs110.HS110Client;
import com.intrbiz.iot.hs110.model.GetRealtime;
import ev3dev.sensors.Battery;
import lejos.utility.Delay;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public @Slf4j class HS110Example {

    private static final int MAX_ITERATIONS = 20;

    private static final String HS110_CONFIG_FILE = "HS110.properties";
    private static final String HS110_CONFIG_IP_FIELD = "ip";
    private static HS110Client hs110;

    public static void main(String[] args) throws Exception {

        final Battery EV3Battery = Battery.getInstance();

        final String hs110Location = getHS110Location();
        hs110 = new HS110Client(hs110Location);

        enableLed(0);

        float ev3Voltage = 0.0f;
        boolean charging = false;

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            GetRealtime response = hs110.consumption();

            ev3Voltage = EV3Battery.getVoltage();
            charging = (response.getPower() == 0.0d)? false : true;

            LOGGER.info("EV3 Battery: {}, Charging: {}", ev3Voltage, charging);

            enableLed(i);

            Delay.msDelay(500);
        }

        enableLed(0);
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
