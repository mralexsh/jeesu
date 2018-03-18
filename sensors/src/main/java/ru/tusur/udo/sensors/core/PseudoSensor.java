package ru.tusur.udo.sensors.core;

import java.util.Date;

import ru.tusur.udo.sensors.interfaces.EmulationStrategy;
import ru.tusur.udo.sensors.interfaces.Sensor;
import ru.tusur.udo.sensors.interfaces.SensorEmulator;

public class PseudoSensor implements Sensor, SensorEmulator {

	private int value;
	private int type;
	private String id;
	private long timeStamp;
	private Date date;
	private EmulationStrategy emulationStrategy;
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
	public EmulationStrategy getEmulationStrategy() {
		return emulationStrategy;
	}
	public void setEmulationStrategy(EmulationStrategy emulattionStrategy) {
		this.emulationStrategy = emulattionStrategy;
	}
	public void doEmulate() {
		if (this.emulationStrategy != null) {
			this.emulationStrategy.doEmulate(this);	
		}
	}

}
