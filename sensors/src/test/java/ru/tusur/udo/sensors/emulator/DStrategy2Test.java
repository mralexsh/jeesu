package ru.tusur.udo.sensors.emulator;


import junit.framework.TestCase;
import ru.tusur.udo.sensors.emulator.DStrategy2;

public class DStrategy2Test extends TestCase {
	
	private FakeSensor sensor;
	private EmulationStrategy strategy;
	
	
	@Override
	public void setUp() {
		this.sensor = new FakeSensor();
		this.strategy = new DStrategy2();
		this.sensor.setEmulationStrategy(this.strategy);
	}
	
	
	public void testDStrategy2()	
	{
		int ticks_counter = 5;
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
