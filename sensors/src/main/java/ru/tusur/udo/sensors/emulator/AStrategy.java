package ru.tusur.udo.sensors.emulator;

public class AStrategy implements EmulationStrategy {

    private int direction = 1;

    private int ticks = 0;
    private int value;

    private int counter;
    private int max;
    private int min;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

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
    public void doEmulate(SensorSetter sensor) {
        this.ticks ++;
        if (this.ticks == this.counter) {
            this.ticks = 0;
            this.value += this.calcDirection();
        }
        sensor.setValue(this.value);
    }

    private int calcDirection() {
        if (this.value >= this.max) {
            this.direction = -1;
        }
        if (this.value <= this.min) {
            this.direction = 1;
        }
        return this.direction;
    }

}
