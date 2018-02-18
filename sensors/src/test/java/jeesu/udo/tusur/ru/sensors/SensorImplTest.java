package jeesu.udo.tusur.ru.sensors;

import jeesu.udo.tusur.ru.sensors.interfaces.Sensor;
import junit.framework.TestCase;

class SensorImpl implements Sensor {

	public String getId() {
		return "test sensor";
	}

	public int getValue() {
		return 666;
	}

	public long getTimeStamp() {
		return 123456;
	}
	
}

public class SensorImplTest extends TestCase {
	  public void testSensorImpl()
	    {
		        Sensor sensor = new SensorImpl();
		        assertEquals(sensor.getId(),  "test sensor");
		        assertEquals(sensor.getValue(),  666);
		        assertEquals(sensor.getTimeStamp(),  123456);
	    }
}
