package com.gmail.shimonchuk;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import java.util.ArrayList;
import java.util.List;

public class MessageRetranslation extends Thread {

    private String validationError = null;
    private MessageConsumer inputQueue;
    private List<MessageProducer> outputQueues;
    private MessageValidator messageValidator;

    public String getValidationError() {
        return validationError;
    }

    public MessageRetranslation() {
        outputQueues = new ArrayList<>();
    }

    public void setInputQueue(MessageConsumer inputQueue) {
        this.inputQueue = inputQueue;
    }

    public void addOutputQueue(MessageProducer messageProducer) {
        outputQueues.add(messageProducer);
    }

    public void setMessageValidator(MessageValidator messageValidator) {
        this.messageValidator = messageValidator;
    }

    public void run() {
        System.out.println("Starting ActiveMQ retranslation..");
        if (inputQueue != null && outputQueues != null && outputQueues.size() > 0) {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Message msg = inputQueue.receive();
                    try {
                        validationError = null;
                        messageValidator.validate(msg);
                        outputQueues.forEach(messageProducer -> {
                            try {
                                messageProducer.send(msg);
                            } catch (JMSException e) {
                                System.out.println("Error occurred while sending message: " + e.getMessage());
                            }
                        });
                    } catch (MessageValidationException e) {
                        validationError = e.getMessage();
                        System.out.println(validationError);
                    }
                } catch (JMSException e) {
                    System.out.println("Error occurred while receiving message: " + e.getMessage());
                }
            }

        } else {
            System.out.println("ERROR: Message translator must be initialized properly");
        }

    }

}
