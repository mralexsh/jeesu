package ru.tusur.udo.sensors;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.tusur.udo.sensors.emulator.DStrategy;
import ru.tusur.udo.sensors.emulator.FakeSensor;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class DStrategyTest {

    private int RANDOM_SEED = 17137;
    private int RANDOM_INTERVAL_SEED = 7;
    private static DStrategy dStrategy;
    private static FakeSensor fakeSensor;
    private int interval;


    private int calcInterval() {
        Random rand = new Random();
        return rand.nextInt(RANDOM_INTERVAL_SEED + 1) + 1;
    }
    private int calcSeedRange() {
        Random rand = new Random();
        return rand.nextInt(RANDOM_SEED + 1) + 100;
    }

    @BeforeClass
    public static void initObj() {
        dStrategy = new DStrategy();
        fakeSensor = new FakeSensor();
        fakeSensor.setEmulationStrategy(dStrategy);
    }

    @Before
    public void setupParams() {
        this.interval = calcInterval();
        fakeSensor.setValue(0);
        dStrategy.setCounter(this.interval);
    }
    private void commonTest() {
        int expectedValue = 0;
        int seedRange = calcSeedRange();
        for (int i = 1; i < seedRange; i++) {
            fakeSensor.emulate();
            if (i % interval == 0) {
                if (expectedValue == 0) {
                    expectedValue = 1;
                } else {
                    expectedValue = 0;
                }
            }
            assertEquals(fakeSensor.getValue(), expectedValue);
        }
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
