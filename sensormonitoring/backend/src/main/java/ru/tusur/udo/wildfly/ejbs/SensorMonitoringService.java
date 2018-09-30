package ru.tusur.udo.wildfly.ejbs;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.impl.JndiRegistry;


@Singleton
@Startup
public class SensorMonitoringService 
{
	private static final Logger log = Logger.getLogger(SensorMonitoringService.class.toString());

	@Inject
	SensorCamelContext sensorCamelContext;
	
	@Inject
	SensorRoutes sensorRoutes;
	
	@PostConstruct
	public void init() {		
		try {
			this.sensorCamelContext.addRoutes(this.sensorRoutes);
			this.sensorCamelContext.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
