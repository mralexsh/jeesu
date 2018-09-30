package ru.tusur.udo.sensors.core;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Component
public class JSONProcessor implements Processor {
	private static Logger log = LoggerFactory.getLogger(JSONProcessor.class);
	
	@Value("${runtime.node}")
	private String nodeName;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Object ob = exchange.getIn().getBody();
		List<Sensor> sensors;
		if (ob instanceof List) {
			 if(((List)ob).size() > 0 && (((List)ob).get(0) instanceof Sensor)){
				 sensors = (List<Sensor>) ob;
				 exchange.getOut().setBody(this.convertToJson(sensors));
			 }
		}
	}

	private String convertToJson(List<Sensor> sensors) throws JsonProcessingException {		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(new SensorJSONSchema() {
			public String getNode() {
				return nodeName;
			}
			public List<Sensor> getSensors() {
				return sensors;
			}
		});		
	}
	
}
