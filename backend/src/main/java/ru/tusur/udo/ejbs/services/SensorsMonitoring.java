package ru.tusur.udo.ejbs.services;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import ru.tusur.udo.ejbs.camel.SensorsCamelContext;
import ru.tusur.udo.ejbs.camel.SensorsRoutes;

@Singleton
@Startup
public class SensorsMonitoring {
 
	@Inject
	private SensorsCamelContext sensorsCamelContext;
	
	@Inject
	private SensorsRoutes sensorsRoutes;
	
	@PostConstruct
	public void init() throws Exception {		
		sensorsCamelContext.addRoutes(sensorsRoutes);
		sensorsCamelContext.start();	
	}
	
	public void fireSensors(String sensorsJSON) {
		sensorsCamelContext.getApiControllerTemplate()
		.sendBody("seda:apiController", sensorsJSON);
	}
	
	public String retrieveSnapshot() {
		return sensorsCamelContext.getWsConsumerTemplate()
		.receiveBody("direct://wsController", String.class);
	}
	
	
}
