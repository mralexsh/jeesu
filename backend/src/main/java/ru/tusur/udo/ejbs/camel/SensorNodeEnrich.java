package ru.tusur.udo.ejbs.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.tusur.udo.ejbs.dto.EnrichedSensorNode;
import ru.tusur.udo.ejbs.dto.SensorNode;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.ArrayList;

@Stateless
@Named("sensorNodeEnrich")
public class SensorNodeEnrich implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        SensorNode sensorNode = exchange.getIn().getBody(SensorNode.class);
        if (sensorNode != null) {
            EnrichedSensorNode enrichedSensorNode = new EnrichedSensorNode();
            enrichedSensorNode.setSensors(new ArrayList<>());
            enrichedSensorNode.setNode(sensorNode.getNode());
            enrichedSensorNode.setTimeStamp(sensorNode.getTimeStamp());
            exchange.getIn().setHeader("EnrichedSensorNode", enrichedSensorNode);
        }
    }
}
