package ru.tusur.udo.sensors;
import org.springframework.context.ApplicationContext;
import ru.tusur.udo.sensors.interfaces.SensorRuntime;

public class SensorsController {

	public static void start(ApplicationContext ctx) {
		Object bean = ctx.getBean(SensorRuntime.class);
        if (bean instanceof SensorRuntime) {
        	SensorRuntime s = (SensorRuntime) bean;
        	s.start();        	
        }
	}
	
}
