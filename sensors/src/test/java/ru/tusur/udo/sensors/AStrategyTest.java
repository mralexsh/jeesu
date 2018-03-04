package ru.tusur.udo.sensors;

import junit.framework.TestCase;
import ru.tusur.udo.sensors.AStrategy;
import ru.tusur.udo.sensors.SensorObject;

public class AStrategyTest extends TestCase {
	public void testAStrategy() {
		SensorObject sensor = new SensorObject();
		AStrategy strategy = new AStrategy();
		int interval = 4;
		int min = 17;
		int max = 215;
		
		strategy.setInterval(interval);
		strategy.setMin(min);
		strategy.setMax(max);
		for(int i = 0; i < (max - min) * interval; i++) {
			strategy.doEmulate(sensor);
			int t = min + (i + 1) / interval;
			assertEquals(sensor.getValue(), t);						
		}
		assertEquals(sensor.getValue(), max);
		for(int i = 0; i < (max - min) * interval; i++) {
			strategy.doEmulate(sensor);
			int t = max - (i + 1) / interval;
			assertEquals(sensor.getValue(), t);						
		}
		assertEquals(sensor.getValue(), min);
	}
	
}