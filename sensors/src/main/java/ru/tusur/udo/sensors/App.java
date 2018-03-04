package ru.tusur.udo.sensors;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args )
    {
    	new App();    	
    }
    public App() {
		ClassPathXmlApplicationContext ctx =  new ClassPathXmlApplicationContext("applicationConfig.xml");
    	SensorEmulationRuntime runtime = (SensorEmulationRuntime) ctx.getBean("runtime");
    	runtime.start();
    	ctx.close();
    }
}
