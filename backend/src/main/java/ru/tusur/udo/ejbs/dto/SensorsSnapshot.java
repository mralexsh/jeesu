package ru.tusur.udo.ejbs.dto;

import java.util.List;

public class SensorsSnapshot {
	String operator;
	long timeStamp;
	List<EnrichedSensorNode> nodes;
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
	public List<EnrichedSensorNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<EnrichedSensorNode> nodes) {
		this.nodes = nodes;
	}
	
	
	
}
