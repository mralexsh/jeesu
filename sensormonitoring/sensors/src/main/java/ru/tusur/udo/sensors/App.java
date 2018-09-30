package ru.tusur.udo.sensors;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import ru.tusur.udo.sensors.core.JSONProcessor;
import ru.tusur.udo.sensors.core.Sensor;
import ru.tusur.udo.sensors.core.SensorRuntime;
import ru.tusur.udo.sensors.emulator.DStrategy;
import ru.tusur.udo.sensors.emulator.EmulationStrategy;

public class App 
{
	private static Logger log = LoggerFactory.getLogger(App.class);
	
	public static void main( String[] args )
    {				
		log.info("СТАРТ ПРОГРАММЫ");
		ApplicationContext ctx = 
				new AnnotationConfigApplicationContext(ApplicationConfig.class);		
	
		while(true) {
			
		}
		
		
    }
}
