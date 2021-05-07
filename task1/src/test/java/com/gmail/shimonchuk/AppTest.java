package com.gmail.shimonchuk;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.jms.*;

import static org.junit.Assert.assertEquals;


public class AppTest {
    static String testInputQueue = Props.inputQueue();
    static String testOutputQueue1 = Props.outputQueue1();
    static String testOutputQueue2 = Props.outputQueue2();
    static String goodMessage = "TESTING MESSAGE";
    static String badMessage = "";
    static long waitingTime = 500;
    static String validationMsg = "Incoming message must contain at least one character.";

    static JMSContext testJmsContext;

    @BeforeClass
    public static void initTestActiveMQContext() throws JMSException {
        testJmsContext = new ActiveMQContext("vm://localhost?broker.persistent=false");
    }

    static void sendToInput(String queue, String msg) throws JMSException {
        MessageProducer p = QueueBuilder.createProducer(testJmsContext, queue);
        p.send(testJmsContext.getJmsSession().createTextMessage(msg));
    }

    static String receiveFromOutput(String queue) throws JMSException {
        MessageConsumer c = QueueBuilder.createConsumer(testJmsContext, queue);
        Message msg = c.receive();
        if (msg instanceof TextMessage) {
            return ((TextMessage) msg).getText();
        } else {
            return "INVALID MESSAGE FORMAT";
        }
    }

    static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    @Test
    public void testing_message_translation_without_errors() {
        try {
            MessageRetranslation activeMqRetranslation = App.initMessageRetranslation(testJmsContext);
            activeMqRetranslation.start();
            sendToInput(testInputQueue, goodMessage);
            delay(waitingTime);
            String output1 = receiveFromOutput(testOutputQueue1);
            String output2 = receiveFromOutput(testOutputQueue2);
            assertEquals(goodMessage, output1);
            assertEquals(goodMessage, output2);
            assertEquals(null, activeMqRetranslation.getValidationError());
            activeMqRetranslation.interrupt();
            testJmsContext.closeJmsConnection();
        } catch (JMSException e) {
            System.out.println("Unable to initialize input/output queues: " + e.getMessage());
            testJmsContext.closeJmsConnection();
        }

    }

    @Test
    public void testing_message_translation_with_errors() {
        try {
            MessageRetranslation activeMqRetranslation = App.initMessageRetranslation(testJmsContext);
            activeMqRetranslation.start();
            sendToInput(testInputQueue, badMessage);
            delay(waitingTime);
            activeMqRetranslation.interrupt();
            testJmsContext.closeJmsConnection();
            assertEquals(validationMsg, activeMqRetranslation.getValidationError());
        } catch (JMSException e) {
            System.out.println("Unable to initialize input/output queues: " + e.getMessage());
            testJmsContext.closeJmsConnection();
        }
    }
}
