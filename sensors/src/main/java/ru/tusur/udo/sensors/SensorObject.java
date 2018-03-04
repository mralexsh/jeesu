package ru.tusur.udo.sensors;

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
