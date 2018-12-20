package ru.tusur.udo.sensors.core;

import java.util.Date;
import java.util.List;

public interface SensorsJSONSchema {
	
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
