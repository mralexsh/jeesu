package ru.tusur.udo.sensors;

import junit.framework.TestCase;
import ru.tusur.udo.sensors.interfaces.Sensor;
import ru.tusur.udo.sensors.interfaces.SensorEmulator;

class SensorEmulatorImpl implements Sensor, SensorEmulator {

	private int value;
	public String getId() {
		return "TEST SENSOR";
	}

	public int getValue() {
		return this.value;
	}

	public int getType() {
		return 0;
	}

	public long getTimeStamp() {
		return 123456789;
	}

	public void setValue(int value) {
		this.value = value;		
	}
		
}


public class SensorEmulatorImplTest extends TestCase {
		
	
	public void testSensorEmulatorImpl()
	{        
		SensorEmulator s = new SensorEmulatorImpl();
		s.setValue(777);
		
		if (s instanceof Sensor) {
			Sensor sensor = (Sensor) s;
			assertEquals(sensor.getValue(), 777);
			s.setValue(555);
			assertEquals(sensor.getValue(), 555);
		}
					
	}	
}
