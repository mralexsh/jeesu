package ru.tusur.udo.sensors.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tusur.udo.sensors.emulator.FakeSensor;

import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"ru.tusur.udo.sensors"})
public class ApplicationConfig {

    @Bean
    ApplicationContext xmlContext() {
        return new ClassPathXmlApplicationContext("ApplicationConfig.xml");
    }


    @Bean
    public Map<String, FakeSensor> fakeSensorsMap() {
        return xmlContext().getBeansOfType(FakeSensor.class);
    }
}
