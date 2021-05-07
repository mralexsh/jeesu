package com.gmail.shimonchuk;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class MessageValidator {

    public void validate(Message msg) throws MessageValidationException {
        if (msg instanceof TextMessage) {
            try {
                String msgPayload = ((TextMessage) msg).getText();
                if (msgPayload.equals(""))
                    throw new MessageValidationException("Incoming message must contain at least one character.");
            } catch (JMSException e) {
                throw new MessageValidationException("Error occurred while parsing incoming message: " + e.getMessage());
            }
        } else {
            throw new MessageValidationException("Unexpected message type: " + msg.getClass());
        }
    }

}
