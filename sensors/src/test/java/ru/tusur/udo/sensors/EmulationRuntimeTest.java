package ru.tusur.udo.sensors;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import ru.tusur.udo.sensors.core.Sensor;
import ru.tusur.udo.sensors.core.SensorRuntime;
import ru.tusur.udo.sensors.emulator.EmulationRuntime;

public class EmulationRuntimeTest extends TestCase {
	
	private EmulationRuntime sensorRuntime;
	private List<Sensor> sensors;
	
	@Override
	public void setUp() {
		this.sensors = new ArrayList<>();
		this.sensorRuntime = new EmulationRuntime();
		this.sensorRuntime.setSensors(sensors);
	}
	
	public void testEmulationRuntime() {
		assertNotNull(this.sensorRuntime);
		List<Sensor> sensors = this.sensorRuntime.getSensors();
		assertNotNull(sensors);
	}
}
