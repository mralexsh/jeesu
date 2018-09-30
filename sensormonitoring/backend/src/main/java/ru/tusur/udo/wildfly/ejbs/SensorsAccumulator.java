package ru.tusur.udo.wildfly.ejbs;

import java.util.logging.Logger;

import javax.ejb.Startup;
import javax.ejb.Stateless;

import org.apache.camel.Body;
import org.apache.camel.Consume;
import org.apache.camel.Handler;

@Stateless
public class SensorsAccumulator {
	private static final Logger log = Logger.getLogger(SensorsAccumulator.class.toString());
	
	@Handler
	public void handleMessage(String jsonSensors) {
		log.info("ACCUMULATOR ++++++++++" + jsonSensors);
	}
}
