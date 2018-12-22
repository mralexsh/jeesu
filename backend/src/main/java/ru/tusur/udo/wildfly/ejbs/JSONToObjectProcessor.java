package ru.tusur.udo.wildfly.ejbs;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.io.IOException;

@Stateless
@Named("jsonToObjectProcessor")
public class JSONToObjectProcessor implements Processor {

    @Override
    public void process(Exchange msg) throws Exception {
        msg.getOut().setBody(
                this.convertToNodeSensors(msg.getIn().getBody().toString()), SensorNodeDTO.class);

    }

    private Object convertToNodeSensors(String body) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        SensorNodeDTO obj = mapper.readValue(body, SensorNodeDTO.class);
        return obj;
    }

}