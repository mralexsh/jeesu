package ru.tusur.udo.sensors;

import junit.framework.TestCase;
import ru.tusur.udo.sensors.Sensor;

class SensorImpl implements Sensor {

	public String getId() {
		return "TEST SENSOR";
	}

	public int getValue() {
		return 666;
	}

	public int getType() {
		return 0;
	}

	public long getTimeStamp() {
		return 123456789;
	}
		
}


public class SensorImplTest extends TestCase {
	public void testSensorImpl()
    {        
        Sensor s = new SensorImpl();        
		assertEquals(s.getId(), "TEST SENSOR");
		assertEquals(s.getValue(), 666);
		assertEquals(s.getType(), 0);
		assertEquals(s.getTimeStamp(), 123456789);
    }
}
