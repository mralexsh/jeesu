package ru.tusur.udo.sensors;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.tusur.udo.sensors.emulator.DStrategy;
import ru.tusur.udo.sensors.emulator.EmulationStrategy;
import ru.tusur.udo.sensors.emulator.FakeSensor;

public class DStrategyTest {


	@Test
	public void testDStrategy() {			
		int TESTS_AMOUNT = 7769;		
		int testingValue = 0;
		int ticks = 123;
		
		FakeSensor sensor = new FakeSensor();		
		EmulationStrategy strategy = new DStrategy();	
		strategy.setTicks(123);		
		sensor.setEmulationStrategy(strategy);
		
		
		for(int i = 0; i < TESTS_AMOUNT; i++) {
			sensor.emulate();			
			if ((i != 0) && (i % ticks == 0)) {
				testingValue = testingValue > 0 ? 0 : 1;
			}									
			assertEquals(sensor.getValue(), testingValue);						
		}		
		
		testingValue = 0;
		ticks = 17;
		strategy.setTicks(ticks);
		
		for(int i = 0; i < TESTS_AMOUNT; i++) {
			sensor.emulate();			
			if ((i != 0) && (i % ticks == 0)) {
				testingValue = testingValue > 0 ? 0 : 1;
			}									
			assertEquals(sensor.getValue(), testingValue);						
		}
		
		
	}

}
