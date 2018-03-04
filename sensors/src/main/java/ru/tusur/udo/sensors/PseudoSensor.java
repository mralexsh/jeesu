package ru.tusur.udo.sensors;

import java.util.Date;

public class PseudoSensor implements Sensor, SensorEmulator {

	private int value;
	private int type;
	private String id;
	private long timeStamp;
	private Date date;
	private EmulationStrategy emulattionStrategy;
	public PseudoSensor() {
		this.date = new Date();
	}
	public void setValue(int value) {
		this.value = value;
		this.timeStamp = this.date.getTime();
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getValue() {
		return this.value;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return this.type;
	}
	public long getTimeStamp() {
		return this.timeStamp;
	}
	public EmulationStrategy getEmulattionStrategy() {
		return emulattionStrategy;
	}
	public void setEmulattionStrategy(EmulationStrategy emulattionStrategy) {
		this.emulattionStrategy = emulattionStrategy;
	}
	public void doEmulate() {
		if (this.emulattionStrategy != null) {
			this.emulattionStrategy.doEmulate(this);	
		}
	}

}
