package ru.tusur.udo.ejbs.dto;

import java.util.List;

public class EnrichedSensorNode {
	
	String node;
	long timeStamp;
	List<EnrichedSensor> sensors;
	
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public List<EnrichedSensor> getSensors() {
		return sensors;
	}
	public void setSensors(List<EnrichedSensor> sensors) {
		this.sensors = sensors;
	}
	
	
}
