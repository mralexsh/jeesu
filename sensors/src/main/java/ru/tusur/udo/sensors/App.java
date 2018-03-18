package ru.tusur.udo.sensors;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App 
{
	public static void main( String[] args )
    {
    	try {
			new App();
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    }

    public App() throws Exception {
    	AnnotationConfigApplicationContext javaCtx = new AnnotationConfigApplicationContext(AppContext.class);
    	CamelContext camelContext = javaCtx.getBean(CamelContext.class);
    	camelContext.start();
    	while(true) { }    
    }
}
