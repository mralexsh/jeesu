package ru.tusur.udo.wildfly.ejbs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class SnapshotStateDTO {

    private List<SensorNodeDTO> sensorNode = new ArrayList<>();

    public List<SensorNodeDTO> getSensorNode() {
        return sensorNode;
    }

    public String toJSON() {
        SnapshotStateDTO self = this;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.writeValueAsString(new Object() {
                public List<SensorNodeDTO> getRoot() {
                    return self.getSensorNode();
                }
                public long getTimestamp() {
                    return new Date().getTime();
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }


    public void setSensorNode(SensorNodeDTO node) {
        this.sensorNode = this.sensorNode
                .stream()
                .filter(sensorNodeDTO -> !sensorNodeDTO.getNode().equals(node.getNode()))
                .collect(Collectors.toList());
        this.sensorNode.add(node);
    }
}
