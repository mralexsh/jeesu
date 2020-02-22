package ru.tusur.udo.sensors;

import java.util.Date;
import java.util.List;

import ru.tusur.udo.sensors.interfaces.Sensor;

public interface SensorsJSONSchema {

	String getNode();
	
	default long getTimeStamp() {
		return new Date().getTime();
	}
	
	default List<Sensor> getSensors() {
		return null;
	}
	
}
