package ru.tusur.udo.ejbs.camel;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import ru.tusur.udo.ejbs.dto.SensorNode;
import ru.tusur.udo.ejbs.dto.SensorsSnapshot;

@Stateless
@Named("sensorsAggregationStrategy")
public class SensorsAggregationStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		if (oldExchange == null) {			
			SensorNode node = newExchange.getIn().getBody(SensorNode.class);
			SensorsSnapshot snapshot = new SensorsSnapshot();
			snapshot.setOperator("TEST OPERATOR");
			snapshot.setTimeStamp(new Date().getTime());
			snapshot.setNodes(new ArrayList<SensorNode>());
			snapshot.getNodes().add(node);
			newExchange.getIn().setBody(snapshot);
			return newExchange;
		} else {
			SensorsSnapshot snapshot = oldExchange.getIn().getBody(SensorsSnapshot.class);
			SensorNode node = newExchange.getIn().getBody(SensorNode.class);
			snapshot.getNodes().add(node);
			newExchange.getIn().setBody(snapshot);
			return newExchange;
		}
		
				
		
	}
	
	
}
