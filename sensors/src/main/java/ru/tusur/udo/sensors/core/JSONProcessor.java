package ru.tusur.udo.sensors.core;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class JSONProcessor implements Processor {

	@Value("${location.node}")
	private String locationNode;
	
	@Override
	public void process(Exchange msg) throws Exception {
		
		Object ob = msg.getIn().getBody();
		if (ob instanceof List) {
			if ( ((List) ob).size() > 0 && ((List) ob).get(0) instanceof Sensor) {
				List<Sensor> sensors = (List<Sensor>) ob;
				msg.getOut().setBody(this.convertToJson(sensors));	
			}
		}
		
	}
	
	private String convertToJson(List<Sensor> sensors) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(new SensorsJSONSchema() {
			public String getNode() {
				return locationNode;
			}
			public List<Sensor> getSensors() {
				return  sensors;
			}
		});
	}

}
