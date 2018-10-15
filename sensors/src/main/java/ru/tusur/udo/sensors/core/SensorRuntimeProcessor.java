package ru.tusur.udo.sensors.core;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorRuntimeProcessor implements Processor {
	private static Logger log = LoggerFactory.getLogger(SensorRuntimeProcessor.class);
	
	@Autowired
	SensorRuntime sensorRuntime;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getOut().setBody(this.sensorRuntime.getSensors());
	}

}
