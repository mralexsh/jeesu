package ru.tusur.udo.sensors;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.tusur.ru.sensors.camel.processors.EmulationProcessor;
import ru.tusur.ru.sensors.camel.processors.JSONProcessor;
import ru.tusur.udo.sensors.core.SensorEmulationRuntime;

@Configuration
public class AppContext {

	@Bean
	public ClassPathXmlApplicationContext classPathApplicationContext() {
		return new ClassPathXmlApplicationContext("applicationConfig.xml");
	}
	@Bean
	public JSONProcessor jProcessor() {
		return new JSONProcessor();
	}
	@Bean
	public EmulationProcessor emulationProcessor() {
		
		SensorEmulationRuntime sensorEmulationRuntime = (SensorEmulationRuntime)classPathApplicationContext().getBean("runtime");
		EmulationProcessor eProc = new EmulationProcessor();
		eProc.setSensorEmulationRuntime(sensorEmulationRuntime);
		return eProc;
	}
	@Bean
	public SensorRoutes sensorRoutes() {
		SensorRoutes routes = new SensorRoutes();
		routes.setEmulationProcessor(emulationProcessor());
		routes.setjProcessor(jProcessor());
		return routes;
	}
	@Bean
	public CamelContext camelContext() throws Exception {
		DefaultCamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(sensorRoutes());
		return camelContext;
	}
	

}
