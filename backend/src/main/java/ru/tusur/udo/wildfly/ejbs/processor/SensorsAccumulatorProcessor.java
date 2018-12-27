package ru.tusur.udo.wildfly.ejbs.processor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.tusur.udo.wildfly.ejbs.dto.SensorNodeDTO;
import ru.tusur.udo.wildfly.ejbs.service.SensorsMonitoringService;


@Stateless
@Named("sensorsAccumulatorProcessor")
public class SensorsAccumulatorProcessor  implements Processor {

	@Inject
	SensorsMonitoringService monitoringService;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		this.monitoringService.setSensorsSnapshot((SensorNodeDTO) exchange.getIn().getBody());
		
	}

}
