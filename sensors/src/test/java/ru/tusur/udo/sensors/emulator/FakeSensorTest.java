package ru.tusur.udo.sensors.emulator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FakeSensorTest extends TestCase {
	public FakeSensorTest( String testName ) {
		super( testName );
	}
	public static Test suite() {
        return new TestSuite( FakeSensorTest.class );
    }
	
	 public void testFakeSensor() {
		 FakeSensor fakeSensor = new FakeSensor();
		 assertNotNull(fakeSensor);
		 
		 
		 fakeSensor.setImei("test imei");
		 fakeSensor.setStatus(0); 
		 fakeSensor.setType(1);
		 fakeSensor.setValue(34);
		 
		 assertEquals(fakeSensor.getImei(), "test imei");
		 assertEquals(fakeSensor.getStatus(), 0);
		 assertEquals(fakeSensor.getType(), 1);
		 assertEquals(fakeSensor.getValue(), 34);
		 
		 for (int i = 0; i < 10000; i ++) {
			 fakeSensor.setValue(i);
			 assertEquals(fakeSensor.getValue(), i);
		 }
		 
		 
	 }
}
