package ru.tusur.udo.sensors;

public interface Sensor {
	String getId();
	int getValue();
	int getType();
	long getTimeStamp();
}
