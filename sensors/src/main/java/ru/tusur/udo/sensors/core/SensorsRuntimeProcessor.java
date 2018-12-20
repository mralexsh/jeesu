package ru.tusur.udo.sensors.core;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SensorsRuntimeProcessor implements Processor {

	@Autowired
	private SensorsRuntime sensorsRuntime;
	
	@Override
	public void process(Exchange msg) throws Exception {
		msg.getOut().setBody(this.sensorsRuntime.getSensors());
	}

}
