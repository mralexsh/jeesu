package ru.tusur.udo.wildfly.ejbs.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.tusur.udo.wildfly.ejbs.dto.SensorNodeDTO;
import ru.tusur.udo.wildfly.ejbs.service.AlarmService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

@Stateless
@Named("operationAlarmProcessor")
public class OperationAlarmProcessor implements Processor {

    @Inject
    AlarmService alarmService;

    @Override
    public void process(Exchange exchange) throws Exception {
        SensorNodeDTO node = (SensorNodeDTO) exchange.getIn().getBody();
        exchange.getOut().setBody(alarmService.updateAlarmPool(node));
    }
}
