package ru.tusur.udo.sensors.core;

import java.util.Date;
import java.util.List;

public interface SensorJSONSchema {
	default String getNode() {
		return null;
	}
	default long getTimeStamp() {
		return new Date().getTime();
	}
	default List<Sensor> getSensors() {
		return null;
	}
}
