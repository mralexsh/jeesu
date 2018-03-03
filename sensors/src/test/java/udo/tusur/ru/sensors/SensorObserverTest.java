package udo.tusur.ru.sensors;

import junit.framework.TestCase;
class Log implements SensorObservable {
	private String message;
	public String getMessage() {
		return this.message;
	}
	public void handle(SensorObserver o) {
		if (o instanceof Alarm) {
			Alarm a = (Alarm) o;
			this.message = a.getTestMessage();
		}
	}
}
class Email implements SensorObservable {
	private String message;
	public String getMessage() {
		return this.message;
	}
	public void handle(SensorObserver o) {
		if (o instanceof Alarm) {
			Alarm a = (Alarm) o;
			this.message = a.getTestMessage();
		}
	}
}
class Telegram implements SensorObservable {
	private String message;
	public String getMessage() {
		return this.message;
	}
	public void handle(SensorObserver o) {
		if (o instanceof Alarm) {
			Alarm a = (Alarm) o;
			this.message = a.getTestMessage();
		}
	}
}
public class SensorObserverTest extends TestCase {
	public void testSensorObserver() {
		
		Alarm a = new Alarm();
		
		Log l = new Log();
		Email mail = new Email();
		Telegram t = new Telegram();
		
		a.register(l);
		a.register(mail);
		a.register(t);
		
		String testMsg = "Super test message";
		a.sendMessage(testMsg);
		
		assertEquals(l.getMessage(), testMsg);
		assertEquals(mail.getMessage(), testMsg);
		assertEquals(t.getMessage(), testMsg);
		
	}
}
