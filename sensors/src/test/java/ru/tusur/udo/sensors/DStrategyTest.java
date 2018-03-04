package ru.tusur.udo.sensors;

import junit.framework.TestCase;
import ru.tusur.udo.sensors.DStrategy;
import ru.tusur.udo.sensors.SensorObject;

public class DStrategyTest extends TestCase {
	public void testDStrategy() {
		SensorObject sensor = new SensorObject();
		DStrategy strategy = new DStrategy(3);		
		assertEquals(sensor.getValue(), 0);
		strategy.doEmulate(sensor);
		assertEquals(sensor.getValue(), 0);
		strategy.doEmulate(sensor);
		assertEquals(sensor.getValue(), 0);
		strategy.doEmulate(sensor);
		assertEquals(sensor.getValue(), 1);
		strategy.doEmulate(sensor);
		assertEquals(sensor.getValue(), 1);
		strategy.doEmulate(sensor);
		assertEquals(sensor.getValue(), 1);
		strategy.doEmulate(sensor);
		assertEquals(sensor.getValue(), 0);
		
		DStrategy strategy2 = new DStrategy(2);		
		assertEquals(sensor.getValue(), 0);
		strategy2.doEmulate(sensor);
		assertEquals(sensor.getValue(), 0);
		strategy2.doEmulate(sensor);
		assertEquals(sensor.getValue(), 1);
		strategy2.doEmulate(sensor);
		assertEquals(sensor.getValue(), 1);				
		strategy2.doEmulate(sensor);
		assertEquals(sensor.getValue(), 0);
		
		
		int superInterval = 8760;
		DStrategy strategy3 = new DStrategy();
		strategy3.setInterval(superInterval);
		for (int i = 0; i < superInterval - 1; i++) {
			strategy3.doEmulate(sensor);
			assertEquals(sensor.getValue(), 0);
		}
		strategy3.doEmulate(sensor);
		assertEquals(sensor.getValue(), 1);
		for (int i = 0; i < superInterval - 1; i++) {
			strategy3.doEmulate(sensor);
			assertEquals(sensor.getValue(), 1);
		}
		strategy3.doEmulate(sensor);
		assertEquals(sensor.getValue(), 0);
		
	}
	
}
