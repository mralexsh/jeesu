package ru.tusur.udo.sensors;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tusur.udo.sensors.config.ApplicationConfig;

public class App {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }
}
