package ru.tusur.udo.sensors.core;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollectSensorsProcessor implements Processor{
	private static Logger log = LoggerFactory.getLogger(CollectSensorsProcessor.class);
	
	@Autowired
	SensorRuntime sensorRuntime;
	
	
	@Override
	public void process(Exchange msg) throws Exception {
		msg.getOut().setBody(sensorRuntime.getSensors());
	}

}
