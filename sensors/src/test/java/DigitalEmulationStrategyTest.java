import org.junit.jupiter.api.Test;
import ru.tusur.udo.sensors.emulator.DigitalSensorStrategy;
import ru.tusur.udo.sensors.emulator.FakeSensor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitalEmulationStrategyTest {

    @Test
    void sensor1() {
        int ticks = 3;
        int expectedValue = 0;
        DigitalSensorStrategy emulationStrategy = new DigitalSensorStrategy();
        emulationStrategy.setTicks(ticks);
        FakeSensor fakeSensor = new FakeSensor();
        fakeSensor.setEmulationStrategy(emulationStrategy);

        for (int i = 1; i < 13764; i++) {
            fakeSensor.emulate();
            if (i % ticks == 0) {
                if (expectedValue == 0) {
                    expectedValue = 1;
                } else {
                    expectedValue = 0;
                }
            }
            assertEquals(expectedValue, fakeSensor.getValue());
        }
    }

    @Test
    void sensor2() {
        int ticks = 17;
        int expectedValue = 0;
        DigitalSensorStrategy emulationStrategy = new DigitalSensorStrategy();
        emulationStrategy.setTicks(ticks);
        FakeSensor fakeSensor = new FakeSensor();
        fakeSensor.setEmulationStrategy(emulationStrategy);

        for (int i = 1; i < 17665; i++) {
            fakeSensor.emulate();
            if (i % ticks == 0) {
                if (expectedValue == 0) {
                    expectedValue = 1;
                } else {
                    expectedValue = 0;
                }
            }
            assertEquals(expectedValue, fakeSensor.getValue());
        }
    }
}
