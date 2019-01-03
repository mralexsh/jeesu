package ru.tusur.udo.wildfly.ejbs.service;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.camel.ProducerTemplate;
import ru.tusur.udo.wildfly.ejbs.SensorsCamelContext;
import ru.tusur.udo.wildfly.ejbs.SensorsRoutes;
import ru.tusur.udo.wildfly.ejbs.dto.SensorNodeDTO;
import ru.tusur.udo.wildfly.ejbs.dto.SnapshotStateDTO;


@Singleton
@Startup
public class SensorsMonitoringService {

	private static final Logger log = 
			Logger.getLogger(SensorsMonitoringService.class.toString());
	
	@Inject
	private SensorsCamelContext sensorsCamelContext;
	
	@Inject
	private SensorsRoutes sensorsRoutes;

	@Inject
	private SnapshotStateDTO snapshotStateDTO;
	
	@PostConstruct
	public void init() {
		try {
			this.sensorsCamelContext.addRoutes(this.sensorsRoutes);
			this.sensorsCamelContext.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProducerTemplate getSensorsStartTemplate() {
		return this.sensorsCamelContext.getSensorsStartTemplate();
	}
	public SnapshotStateDTO getSensorsSnapshot()	{
		return this.snapshotStateDTO;
	}
	public void setSensorsSnapshot(SensorNodeDTO node) {
		this.snapshotStateDTO.setSensorNode(node);
	}

}
