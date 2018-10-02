package ru.tusur.udo.wildfly.ejbs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Stateless
public class SensorsAccumulator  {
	private static final Logger log = Logger.getLogger(SensorsAccumulator.class.toString());
	
	private List<SensorNodeDTO> nodes;
	
	public List<SensorNodeDTO> getNodes() {
		return nodes;
	}
	public void setNodes(List<SensorNodeDTO> nodes) {
		this.nodes = nodes;
	}
	@PostConstruct
	public void init() {
		this.nodes = new ArrayList<>();
	}		
	public String getSensorsSnapshot() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(new Object() {
				public List<SensorNodeDTO> getNodes() {
					return nodes;
				}
			});
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
}
