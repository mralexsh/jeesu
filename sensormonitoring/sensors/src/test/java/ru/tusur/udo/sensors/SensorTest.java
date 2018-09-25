package ru.tusur.udo.sensors;

import junit.framework.TestCase;
import ru.tusur.udo.sensors.core.Sensor;

class SensorImpl implements Sensor 
{

	public String getImei() {
		return "SUPER IMEI";
	}

	public int getStatus() {
		return 22;
	}

	public int getValue() {
		return 666;
	}

	public int getType() {
		return 2;
	}
}


public class SensorTest  extends TestCase{
	public void testSensor() {
		Sensor s = new SensorImpl();
		assertEquals(s.getImei(), "SUPER IMEI");
		assertEquals(s.getStatus(), 22);
		assertEquals(s.getValue(), 666);
		assertEquals(s.getType(), 2);
	}
}
