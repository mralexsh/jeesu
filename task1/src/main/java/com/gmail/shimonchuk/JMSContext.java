package com.gmail.shimonchuk;

import javax.jms.Session;

public interface JMSContext {
    Session getJmsSession();
    void closeJmsConnection();
}
