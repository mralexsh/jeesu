package ru.tusur.udo.sensors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
