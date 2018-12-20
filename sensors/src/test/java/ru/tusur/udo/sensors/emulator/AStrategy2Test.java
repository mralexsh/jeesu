package ru.tusur.udo.sensors.emulator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AStrategy2Test  extends TestCase {
	public AStrategy2Test( String testName ) {
		super( testName );
	}
	public static Test suite() {
        return new TestSuite( AStrategy1Test.class );
    }
	public void testAStrategy2() {
		AStrategy1 aStrategy2 = new AStrategy1();
		assertNotNull(aStrategy2);
		
		FakeSensor fakeSensor = new FakeSensor();
		assertNotNull(fakeSensor);
		
		fakeSensor.setEmulationStrategy(aStrategy2);
		assertEquals(fakeSensor.getEmulationStrategy(), aStrategy2);
		
		int interval = 5;
		int min = 0;
		int max = 100;
		
		for(int i = 0; i < (max - min) * interval; i++) {
			aStrategy2.doEmulate(fakeSensor);
			int t = min + (i + 1) / interval;
			assertEquals(fakeSensor.getValue(), t);						
		}
		assertEquals(fakeSensor.getValue(), max);
		for(int i = 0; i < (max - min) * interval; i++) {
			aStrategy2.doEmulate(fakeSensor);
			int t = max - (i + 1) / interval;
			assertEquals(fakeSensor.getValue(), t);						
		}
		assertEquals(fakeSensor.getValue(), min);
		
	}
}



