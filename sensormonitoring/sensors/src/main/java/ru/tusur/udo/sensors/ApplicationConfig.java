package ru.tusur.udo.sensors;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.tusur.udo.sensors.core.JSONProcessor;
import ru.tusur.udo.sensors.core.Sensor;
import ru.tusur.udo.sensors.core.SensorRoutes;
import ru.tusur.udo.sensors.core.SensorRuntime;
import ru.tusur.udo.sensors.emulator.EmulationRuntime;


@Configuration
@ComponentScan(basePackages = {"ru.tusur.udo.sensors.core"})
@PropertySource("classpath:application.properties")
public class ApplicationConfig {	
	
	@Bean 
	public ClassPathXmlApplicationContext xmlContext() {			
		return new ClassPathXmlApplicationContext("ApplicationConfig.xml");				
	}
	
	@Bean
	SensorRuntime sensorRuntime() {		
		EmulationRuntime r = new EmulationRuntime();
		r.setSensors((List<Sensor>) xmlContext().getBean("sensors"));		
		r.start();
		return r;		
	}
	
	
	@Bean
	SensorRoutes sensorRoutes() {
		SensorRoutes routes = new SensorRoutes();		
		return routes;
	}
	
	@Bean
	CamelContext camelContext() throws Exception {
		CamelContext ctx = new DefaultCamelContext();
		ctx.addRoutes(sensorRoutes());
		ctx.start();
		return ctx;
	}
	
	
	
}
