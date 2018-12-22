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

	@Inject
	private SnapshotState snapshotState;
	
	@PostConstruct
	public void init() {
		try {
			this.sensorsCamelContext.addRoutes(this.sensorsRoutes);
			this.setSensorsStartTemplate(this.sensorsCamelContext.createProducerTemplate());
			this.sensorsCamelContext.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public ProducerTemplate getSensorsStartTemplate() {
		return this.sensorsStartTemplate;
	}


	public void setSensorsStartTemplate(ProducerTemplate sensorsStartTemplate) {
		this.sensorsStartTemplate = sensorsStartTemplate;
	}


	public SnapshotState getSensorsSnapshot()	{
		return this.snapshotState;
	}


	public void setSensorsSnapshot(SensorNodeDTO node) {
		this.snapshotState.setState(node);
	}
	
	
}
