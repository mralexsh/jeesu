package ru.tusur.udo.wildfly.ejbs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.LinkedList;

import java.util.Queue;

@Stateless
public class SnapshotState {

    private Queue<SensorNodeDTO> state = new LinkedList<>();
    private int jitterSize = 10;

    public Queue<SensorNodeDTO> getState() {
        return state;
    }

    public String toJSON() {
        SnapshotState self = this;
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(new Object() {
                public Queue<SensorNodeDTO> getState() {
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
        if (this.state.size() > this.jitterSize) {
            this.state.remove();
        }
        this.state.add(node);
    }
}
