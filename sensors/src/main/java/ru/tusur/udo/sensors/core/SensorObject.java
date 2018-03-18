package ru.tusur.udo.sensors.core;

import ru.tusur.udo.sensors.interfaces.Sensor;
import ru.tusur.udo.sensors.interfaces.SensorEmulator;

public class SensorObject implements Sensor, 
									 SensorEmulator {

	private int value;
	public void setValue(int value) {
		this.value = value;	
	}

	public String getId() {
		return null;
	}

	public int getValue() {
		return this.value;	
	}

	public int getType() {		
		return 0;
	}

	public long getTimeStamp() {
		return 0;
	}

}
