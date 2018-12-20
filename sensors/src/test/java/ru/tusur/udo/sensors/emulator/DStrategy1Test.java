package ru.tusur.udo.sensors.emulator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DStrategy1Test  extends TestCase {
	public DStrategy1Test( String testName ) {
		super( testName );
	}
	public static Test suite() {
        return new TestSuite( DStrategy1Test.class );
    }
	public void testDStrategy1() {
		DStrategy1 dStrategy1 = new DStrategy1();
		assertNotNull(dStrategy1);
		
		FakeSensor fakeSensor = new FakeSensor();
		assertNotNull(fakeSensor);
		
		fakeSensor.setEmulationStrategy(dStrategy1);
		assertEquals(fakeSensor.getEmulationStrategy(), dStrategy1);
		
		for (int i = 0; i < 17137; i++) {
			fakeSensor.emulate();
			if (i % 2 == 0) {
				assertEquals(fakeSensor.getValue(), 1);
			} else {
				assertEquals(fakeSensor.getValue(), 0);
			}
		}
	}
}
