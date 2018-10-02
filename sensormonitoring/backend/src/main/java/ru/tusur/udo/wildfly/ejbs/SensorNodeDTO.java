package ru.tusur.udo.wildfly.ejbs;

import java.util.List;

public class SensorNodeDTO {
	private String node;
	private long  timeStamp;
	private List<SensorDTO> sensors;
	
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timestamp) {
		this.timeStamp = timestamp;
	}
	public List<SensorDTO> getSensors() {
		return sensors;
	}
	public void setSensors(List<SensorDTO> sensors) {
		this.sensors = sensors;
	}
}
