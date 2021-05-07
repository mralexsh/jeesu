package com.gmail.shimonchuk;

import javax.jms.JMSException;

public class App {
    public static void main(String[] args) throws JMSException {
        try {
            JMSContext activeMQContext = new ActiveMQContext(
                    Props.activeMQUser(),
                    Props.activeMQPassword(),
                    Props.connectionURI());
            try {
                MessageRetranslation activeMqRetranslation = initMessageRetranslation(activeMQContext);
                activeMqRetranslation.start();
            } catch (JMSException e) {
                System.out.println("Unable to initialize input/output queues: " + e.getMessage());
                activeMQContext.closeJmsConnection();
            }
        } catch (JMSException e) {
            System.out.println("Unable to initialize activeMQ context: " + e.getMessage());
            throw e;
        }
    }


    public static MessageRetranslation initMessageRetranslation(JMSContext activeMQContext) throws JMSException {
        MessageRetranslation activeMqRetranslation = new MessageRetranslation();
        activeMqRetranslation.setMessageValidator(new MessageValidator());
        activeMqRetranslation.setInputQueue(
                QueueBuilder.createConsumer(activeMQContext, Props.inputQueue()));
        activeMqRetranslation.addOutputQueue(
                QueueBuilder.createProducer(activeMQContext, Props.outputQueue1()));
        activeMqRetranslation.addOutputQueue(
                QueueBuilder.createProducer(activeMQContext, Props.outputQueue2()));
        return  activeMqRetranslation;
    }
}
