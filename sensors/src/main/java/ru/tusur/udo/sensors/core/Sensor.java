package ru.tusur.udo.sensors.core;

public interface Sensor {
	String getImei();
	int getStatus();
	int getValue();
	int getType();
}
