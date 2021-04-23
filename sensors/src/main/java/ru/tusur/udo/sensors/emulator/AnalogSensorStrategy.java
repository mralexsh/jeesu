package ru.tusur.udo.sensors.emulator;

public class AnalogSensorStrategy implements EmulationStrategy {

    private int direction = 1;
    private int ticks = 0;
    private int value;
    private int counter;
    private int max;
    private int min;


    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
        this.value = min;
    }

    @Override
    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    @Override
    public void doEmulate(FakeSensor sensor) {
        counter ++;
        if (counter == ticks) {
            counter = 0;
            value += calcDirection();
        }
        sensor.setValue(value);
    }

    private int calcDirection() {
        if (value >= max) {
            direction = -1;
        }
        if (value <= min) {
            direction = 1;
        }
        return direction;
    }
}
