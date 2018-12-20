package ru.tusur.udo.sensors;

import org.springframework.context.ApplicationContext;

import ru.tusur.udo.sensors.emulator.SensorsEmulationRuntime;

public class SensorsController {
	public static void start(ApplicationContext ctx) {
		Object obj = ctx.getBean(SensorsEmulationRuntime.class);
       	if (obj instanceof SensorsEmulationRuntime) {
       		SensorsEmulationRuntime runtime = (SensorsEmulationRuntime) obj;
       		runtime.start();
       	}
	}
}
