import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tusur.udo.sensors.emulator.AnalogSensorStrategy;
import ru.tusur.udo.sensors.emulator.FakeSensor;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalogEmulationStrategyTest {

    private int RANDOM_SEED = 7;
    private int MAX_SCALE_VALUE = 119;
    private  int ticks;
    private  int min;
    private  int max;

    private static AnalogSensorStrategy aStrategy;
    private static FakeSensor fakeSensor;

    private int calcInterval() {
        Random rand = new Random();
        return rand.nextInt(RANDOM_SEED + 1) +1;
    }
    private int calcMin() {
        Random rand = new Random();
        return rand.nextInt(RANDOM_SEED + 1);
    }
    private int calcMax() {
        Random rand = new Random();
        return rand.nextInt(RANDOM_SEED + 1) + MAX_SCALE_VALUE;
    }

    @BeforeAll
    public static void initObj() {
        aStrategy = new AnalogSensorStrategy();
        fakeSensor = new FakeSensor();
        fakeSensor.setEmulationStrategy(aStrategy);
    }

    @BeforeEach
    public void setupParams() {
        this.ticks = calcInterval();
        this.min = calcMin();
        this.max = calcMax();
        aStrategy.setTicks(this.ticks);
        aStrategy.setMin(this.min);
        aStrategy.setMax(this.max);
    }

    private void commonTest() {
        for(int i = 0; i < (max - min) * ticks; i++) {
            aStrategy.doEmulate(fakeSensor);
            int t = min + (i + 1) / ticks;
            assertEquals(fakeSensor.getValue(), t);
        }
        assertEquals(fakeSensor.getValue(), max);
        for(int i = 0; i < (max - min) * ticks; i++) {
            aStrategy.doEmulate(fakeSensor);
            int t = max - (i + 1) / ticks;
            assertEquals(fakeSensor.getValue(), t);
        }
        assertEquals(fakeSensor.getValue(), min);
    }


    @Test
    public void testOne() {
        commonTest();
    }
    @Test
    public void testTwo() {
        commonTest();
    }
    @Test
    public void testThree() {
        commonTest();
    }
    @Test
    public void testFour() {
        commonTest();
    }
    @Test
    public void testFive() {
        commonTest();
    }

}
