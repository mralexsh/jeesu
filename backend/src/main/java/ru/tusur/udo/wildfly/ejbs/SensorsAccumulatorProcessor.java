package ru.tusur.udo.wildfly.ejbs;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


@Stateless
@Named("sensorsAccumulatorProcessor")
public class SensorsAccumulatorProcessor  implements Processor {

	@Inject
	SensorsMonitoringService monitoringService;
	
	@Override
	public void process(Exchange msg) throws Exception {
		
		this.monitoringService.setSensorsSnapshot((SensorNodeDTO) msg.getIn().getBody());
		
	}

}
