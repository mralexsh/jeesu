package ru.tusur.udo.wildfly.ejbs;

import javax.ejb.Stateless;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

@Stateless
public class SensorsCamelContext extends DefaultCamelContext {

    private ProducerTemplate sensorsStartTemplate;

    public SensorsCamelContext() {
        this.sensorsStartTemplate = createProducerTemplate();
    }

    public ProducerTemplate getSensorsStartTemplate() {
        return sensorsStartTemplate;
    }

}