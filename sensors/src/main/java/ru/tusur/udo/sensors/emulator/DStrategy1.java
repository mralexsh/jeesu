package ru.tusur.udo.sensors.emulator;

public class DStrategy1 implements EmulationStrategy {

	private int testValue;
	
	public DStrategy1() {
		this.testValue = 0;
	}
	
	@Override
	public void doEmulate(SensorSetter sensor) {
		
		if (this.testValue == 0) {
			this.testValue = 1;
		} else {
			this.testValue = 0;
		}
		sensor.setValue(this.testValue);
	}
}
