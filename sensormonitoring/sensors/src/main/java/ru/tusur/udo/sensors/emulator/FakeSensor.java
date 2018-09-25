package ru.tusur.udo.sensors.emulator;

import ru.tusur.udo.sensors.core.Sensor;

public class FakeSensor implements Sensor, SensorSetter {
	private String imei;
	private int status;
	private int value;
	private int type;
	
	private EmulationStrategy emulationStrategy; 
	
	public void setEmulationStrategy(EmulationStrategy emulationStrategy) {
		this.emulationStrategy = emulationStrategy;
	}

	@Override
	public String getImei() {		
		return this.imei;
	}	

	@Override
	public int getStatus() {
		return this.status;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public int getType() {
		return this.type;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public void emulate() {
		this.emulationStrategy.doEmulate(this);
	}
	
	

}
