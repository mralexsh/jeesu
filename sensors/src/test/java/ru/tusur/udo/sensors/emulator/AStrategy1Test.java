package ru.tusur.udo.sensors.emulator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AStrategy1Test  extends TestCase {
	public AStrategy1Test( String testName ) {
		super( testName );
	}
	public static Test suite() {
        return new TestSuite( AStrategy1Test.class );
    }
	public void testAStrategy1() {
		AStrategy1 aStrategy1 = new AStrategy1();
		assertNotNull(aStrategy1);
		
		FakeSensor fakeSensor = new FakeSensor();
		assertNotNull(fakeSensor);
		
		fakeSensor.setEmulationStrategy(aStrategy1);
		assertEquals(fakeSensor.getEmulationStrategy(), aStrategy1);
		
		int interval = 3;
		int min = 0;
		int max = 100;
		
		for(int i = 0; i < (max - min) * interval; i++) {
			aStrategy1.doEmulate(fakeSensor);
			int t = min + (i + 1) / interval;
			assertEquals(fakeSensor.getValue(), t);						
		}
		assertEquals(fakeSensor.getValue(), max);
		for(int i = 0; i < (max - min) * interval; i++) {
			aStrategy1.doEmulate(fakeSensor);
			int t = max - (i + 1) / interval;
			assertEquals(fakeSensor.getValue(), t);						
		}
		assertEquals(fakeSensor.getValue(), min);
		
	}
}



