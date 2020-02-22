package ru.tusur.udo.sensors.interfaces;

import java.util.List;

public interface SensorRuntime {
	List<Sensor> getSensors();
	public void start();
}
