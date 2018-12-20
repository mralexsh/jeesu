package ru.tusur.udo.wildfly.ejbs;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.camel.ProducerTemplate;


@Singleton
@Startup
public class SensorsMonitoringService {

	private static final Logger log = 
			Logger.getLogger(SensorsMonitoringService.class.toString());
	
	@Inject
	private SensorsCamelContext sensorsCamelContext;
	
	@Inject
	private SensorsRoutes sensorsRoutes;
	
	
	private ProducerTemplate sensorsStartTemplate;
	
	private String sensorsSnapshot;
	
	@PostConstruct
	public void init() {
		try {
			this.sensorsCamelContext.addRoutes(this.sensorsRoutes);
			this.setSensorsStartTemplate(this.sensorsCamelContext.createProducerTemplate());
			this.sensorsCamelContext.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("---------------УРА БИН СТАРТАНУЛ------------");
	}


	public ProducerTemplate getSensorsStartTemplate() {
		return this.sensorsStartTemplate;
	}


	public void setSensorsStartTemplate(ProducerTemplate sensorsStartTemplate) {
		this.sensorsStartTemplate = sensorsStartTemplate;
	}


	public String getSensorsSnapshot() {
		return sensorsSnapshot;
	}


	public void setSensorsSnapshot(String sensorsSnapshot) {
		this.sensorsSnapshot = sensorsSnapshot;
	}
	
	
}
