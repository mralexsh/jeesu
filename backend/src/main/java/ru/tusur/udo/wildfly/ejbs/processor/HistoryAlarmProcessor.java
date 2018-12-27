package ru.tusur.udo.wildfly.ejbs.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named("historyAlarmProcessor")
public class HistoryAlarmProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

    }
}
