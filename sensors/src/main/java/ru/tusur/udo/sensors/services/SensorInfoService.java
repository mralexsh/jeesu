package ru.tusur.udo.sensors.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tusur.udo.sensors.dto.NodeInfo;
import ru.tusur.udo.sensors.dto.SensorInfo;
import ru.tusur.udo.sensors.interfaces.SensorsRuntime;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorInfoService {

    @Value("${node.name}")
    private String nodeName;

    @Value("${node.mode}")
    private String nodeMode;

    @Autowired
    SensorsRuntime sensorsRuntime;

    public String makeSensorNodeInfo() {
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setNodeName(nodeName);
        nodeInfo.setTimestamp(new Date().getTime());
        nodeInfo.setSensorsInfo(makeSensorsInfo());
        return toJSON(nodeInfo);
    }

    private List<SensorInfo> makeSensorsInfo() {
        return sensorsRuntime
                .getSensors()
                .stream()
                .map(sensor -> {
                    SensorInfo sensorInfo = new SensorInfo();
                    sensorInfo.setName(sensor.getName());
                    sensorInfo.setType(sensor.getType());
                    sensorInfo.setValue(sensor.getValue());
                    sensorInfo.setMode(nodeMode);
                    return sensorInfo;
                }).collect(Collectors.toList());
    }

    private String toJSON(NodeInfo nodeInfo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(nodeInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
