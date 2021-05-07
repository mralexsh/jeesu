package com.gmail.shimonchuk;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;

public class QueueBuilder {

    public static MessageConsumer createConsumer(JMSContext activeMQContext, String queue) throws JMSException {
        Destination destination = activeMQContext.getJmsSession().createQueue("queue://" + queue);
        return activeMQContext.getJmsSession().createConsumer(destination);
    }

    public static MessageProducer createProducer(JMSContext activeMQContext, String queue) throws JMSException {
        Destination destination = activeMQContext.getJmsSession().createQueue("queue://" + queue);
        return activeMQContext.getJmsSession().createProducer(destination);
    }
}
