package udo.tusur.ru.sensors;

public interface Sensor {
	String getId();
	int getValue();
	int getType();
	long getTimeStamp();
}
