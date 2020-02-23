package ru.tusur.udo.sensors;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.tusur.udo.sensors.emulator.AStrategy;
import ru.tusur.udo.sensors.emulator.FakeSensor;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class AStrategyTest {

    private int RANDOM_SEED = 7;
    private int MAX_SCALE_VALUE = 119;
    private  int interval;
    private  int min;
    private  int max;

    private static AStrategy aStrategy;
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

    @BeforeClass
    public static void initObj() {
        aStrategy = new AStrategy();
        fakeSensor = new FakeSensor();
        fakeSensor.setEmulationStrategy(aStrategy);
    }
    @Before
    public void setupParams() {
        this.interval = calcInterval();
        this.min = calcMin();
        this.max = calcMax();
        aStrategy.setCounter(this.interval);
        aStrategy.setMin(this.min);
        aStrategy.setMax(this.max);
    }

    private void commonTest() {
        for(int i = 0; i < (max - min) * interval; i++) {
            aStrategy.doEmulate(fakeSensor);
            int t = min + (i + 1) / interval;
            assertEquals(fakeSensor.getValue(), t);
        }
        assertEquals(fakeSensor.getValue(), max);
        for(int i = 0; i < (max - min) * interval; i++) {
            aStrategy.doEmulate(fakeSensor);
            int t = max - (i + 1) / interval;
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
