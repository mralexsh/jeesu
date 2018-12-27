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

    private List<SensorNodeDTO> state = new ArrayList<>();

    public List<SensorNodeDTO> getState() {
        return state;
    }

    public String toJSON() {
        SnapshotStateDTO self = this;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.writeValueAsString(new Object() {
                public List<SensorNodeDTO> getState() {
                    return self.getState();
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


    public void setState(SensorNodeDTO node) {
        this.state = this.state
                .stream()
                .filter(sensorNodeDTO -> !sensorNodeDTO.getNode().equals(node.getNode()))
                .collect(Collectors.toList());
        this.state.add(node);
    }
}
