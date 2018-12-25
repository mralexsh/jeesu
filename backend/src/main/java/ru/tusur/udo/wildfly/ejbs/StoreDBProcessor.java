package ru.tusur.udo.wildfly.ejbs;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.tusur.udo.wildfly.ejbs.repository.SensorLogRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

@Stateless
@Named("storeDBProcessor")
public class StoreDBProcessor  implements Processor {

    @Inject
    SensorLogRepository sensorLogRepository;

    @Override
    public void process(Exchange msg) throws Exception {
        SensorNodeDTO node = (SensorNodeDTO) msg.getIn().getBody();
        node.getSensors().forEach(sensor -> sensorLogRepository.create(sensor, node.getNode()));
    }
}
