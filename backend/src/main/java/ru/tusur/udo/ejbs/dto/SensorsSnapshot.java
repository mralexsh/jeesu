package ru.tusur.udo.ejbs.dto;

import java.util.List;

public class SensorsSnapshot {
	String operator;
	long timeStamp;
	List<SensorNode> nodes;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public List<SensorNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<SensorNode> nodes) {
		this.nodes = nodes;
	}
	
	
	
}
