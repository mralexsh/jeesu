package com.gmail.shimonchuk;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

public class ActiveMQContext implements JMSContext {

    Connection jmsConnection;
    Session jmsSession;

    public ActiveMQContext(String connectionURI) throws JMSException {
        initJmsConnectionAndSession(new ActiveMQConnectionFactory(connectionURI));
    }

    public ActiveMQContext(String user,
                              String password,
                              String connectionURI) throws JMSException {
        initJmsConnectionAndSession(new ActiveMQConnectionFactory(user, password, connectionURI));
    }

    private void initJmsConnectionAndSession(ActiveMQConnectionFactory factory) throws JMSException {
        jmsConnection = factory.createQueueConnection();
        jmsConnection.start();
        jmsSession = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public Session getJmsSession() {
        return jmsSession;
    }

    public void closeJmsConnection() {
        if (jmsConnection != null) {
            try {
                jmsConnection.close();
            } catch (JMSException e) {
                System.out.println("Error occurred while closing jms connection: " + e.getMessage());
            }
        }
    }
}
