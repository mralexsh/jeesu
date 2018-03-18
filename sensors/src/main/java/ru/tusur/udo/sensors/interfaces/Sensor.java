package ru.tusur.udo.sensors.interfaces;

public interface Sensor {
	String getId();
	int getValue();
	int getType();
	long getTimeStamp();
}
