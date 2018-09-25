package ru.tusur.udo.sensors;

import java.util.ArrayList;

import junit.framework.TestCase;
import ru.tusur.udo.sensors.emulator.DStrategy;
import ru.tusur.udo.sensors.emulator.EmulationRuntime;
import ru.tusur.udo.sensors.emulator.EmulationStrategy;
import ru.tusur.udo.sensors.emulator.FakeSensor;

public class DStrategyTest extends TestCase {
	
	private FakeSensor sensor;
	private EmulationStrategy strategy;
	
	
	@Override
	public void setUp() {
		this.sensor = new FakeSensor();
		this.strategy = new DStrategy();
		this.sensor.setEmulationStrategy(this.strategy);
	}
	
	
	public void testDStrategy1()	
	{
		int ticks_counter = 5;
		this.strategy.setTicksCounter(ticks_counter);
		int value = 0;
		for (int i = 1; i < 10000; i++) {
			this.sensor.emulate();			
			if (i % ticks_counter == 0) {
				if (value == 1) {
					value = 0;
				} else {
					value = 1;
				}				
			}
			assertEquals(this.sensor.getValue(), value);
		}								
	}
	public void testDStrategy2()	
	{
		int ticks_counter = 17;
		this.strategy.setTicksCounter(ticks_counter);
		int value = 0;
		for (int i = 1; i < 10000; i++) {
			this.sensor.emulate();			
			if (i % ticks_counter == 0) {
				if (value == 1) {
					value = 0;
				} else {
					value = 1;
				}				
			}
			assertEquals(this.sensor.getValue(), value);
		}								
	}
	
}
