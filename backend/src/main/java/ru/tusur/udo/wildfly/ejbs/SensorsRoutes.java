package ru.tusur.udo.wildfly.ejbs;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import ru.tusur.udo.wildfly.ejbs.service.SensorsMonitoringService;

@Stateless
public class SensorsRoutes extends RouteBuilder {

    private static final Logger log =
            Logger.getLogger(SensorsMonitoringService.class.toString());

    @Inject
    @Named("jsonToObjectProcessor")
    Processor jsonToObjectProcessor;

    @Inject
    @Named("sensorsAccumulatorProcessor")
    Processor sensorAccumulatorProcessor;

    @Inject
    @Named("storeDBProcessor")
    Processor storeDBProcessor;

    @Inject
    @Named("operationAlarmProcessor")
    Processor operationAlarmProcessor;

    @Inject
    @Named("historyAlarmProcessor")
    Processor historyAlarmProcessor;


    @Override
    public void configure() throws Exception {

        from("seda:sensorsStartPoint")
                .to("direct:jsonToObject");

        from("direct:jsonToObject")
                .process(this.jsonToObjectProcessor)
                .to("direct:storeSensorsLog")
                .to("direct:operationAlarms");

        from("direct:storeSensorsLog")
                .process(this.storeDBProcessor);

        from("direct:operationAlarms")
                .process(this.operationAlarmProcessor)
                .to("direct:historyAlarms");

        from("direct:historyAlarms")
                .process(this.historyAlarmProcessor)
                .to("direct:mergeNodes");


        from("direct:mergeNodes")
                .process(this.sensorAccumulatorProcessor);


    }

}
