package ru.tusur.udo.sensors.dto;

import java.util.List;

public class NodeInfo {
    private String nodeName;
    private long timestamp;
    private List<SensorInfo> sensorsInfo;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<SensorInfo> getSensorsInfo() {
        return sensorsInfo;
    }

    public void setSensorsInfo(List<SensorInfo> sensorsInfo) {
        this.sensorsInfo = sensorsInfo;
    }
}
