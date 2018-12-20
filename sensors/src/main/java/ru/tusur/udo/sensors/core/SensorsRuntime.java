package ru.tusur.udo.sensors.core;

import java.util.List;

import ru.tusur.udo.sensors.emulator.FakeSensor;

public interface SensorsRuntime {
	List<Sensor> getSensors();
}
