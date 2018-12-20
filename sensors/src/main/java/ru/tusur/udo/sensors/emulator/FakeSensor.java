package ru.tusur.udo.sensors.emulator;

import ru.tusur.udo.sensors.core.Sensor;

public class FakeSensor 
	implements SensorSetter, Sensor {
	
	private String imei;
	private int value;
	private int status;
	private int type;
	
	EmulationStrategy emulationStrategy;
	
	public EmulationStrategy getEmulationStrategy() {
		return emulationStrategy;
	}

	public void setEmulationStrategy(EmulationStrategy emulationStrategy) {
		this.emulationStrategy = emulationStrategy;
	}

	public void emulate() {
		this.emulationStrategy.doEmulate(this);
	}
	
	public void setImei(String imei) {
		this.imei = imei;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getImei() {
		return this.imei;
	}

	public int getValue() {
		return this.value;
	}

	public int getStatus() {
		return this.status;
	}

	public int getType() {
		return this.type;
	}

	public void setValue(int value) {
		this.value = value;
	}
	public Sensor toPureSensor() {
		FakeSensor self = this;
		return new Sensor() {

			@Override
			public String getImei() {
				return self.getImei();
			}

			@Override
			public int getValue() {
				return self.getValue();
			}

			@Override
			public int getStatus() {
				return self.getStatus();
			}

			@Override
			public int getType() {
				return self.getType();
			}
		}; 
	}

}
