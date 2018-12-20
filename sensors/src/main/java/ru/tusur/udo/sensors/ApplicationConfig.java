package ru.tusur.udo.sensors;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.tusur.udo.sensors.core.SensorsRoutes;
import ru.tusur.udo.sensors.emulator.FakeSensor;

@Configuration
@ComponentScan(basePackages = {"ru.tusur.udo.sensors"})
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
	
	private static Logger log = LoggerFactory.getLogger(ApplicationConfig.class);
	
	@Bean
	ApplicationContext xmlContext() {
		return new ClassPathXmlApplicationContext("ApplicationConfig.xml");
	}
	
	@Bean
	public Map<String, FakeSensor> fakeSensors() {
		ApplicationContext ctx = this.xmlContext();
		return ctx.getBeansOfType(FakeSensor.class);
	}
	
	@Bean
	public SensorsRoutes sensorsRoutes() {
		return new SensorsRoutes();
	}
	
	@Bean
	CamelContext camelContext() throws Exception {
		CamelContext ctx = new DefaultCamelContext();
		ctx.addRoutes(this.sensorsRoutes());
		ctx.start();
		return ctx;
	}
	
	
}
