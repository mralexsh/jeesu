package ru.tusur.udo.sensors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonConvertProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {		
		
		SensorsJSONSchema node = exchange.getIn().getBody(SensorsJSONSchema.class);
		
		ObjectMapper mapper = new ObjectMapper();		
		String json = mapper.writeValueAsString(node);
		
		exchange.getIn().setBody(json);
		
	}

}
