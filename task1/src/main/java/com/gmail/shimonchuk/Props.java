package com.gmail.shimonchuk;

public class Props {

    public static String activeMQUser() {
        return env("ACTIVEMQ_USER", "admin");
    }
    public static String activeMQPassword() {
        return env("ACTIVEMQ_PASSWORD", "admin");
    }
    /*public static String connectionURI() {
        return env("CONNECTION_URI", "amqp://localhost:5672");
    }*/
    public static String connectionURI() {
        return env("CONNECTION_URI", "tcp://DESKTOP-8A75GM3:61616");
    }


    public static String inputQueue() {
        return env("INPUT_QUEUE", "task1.input");
    }
    public static String outputQueue1() {
        return env("OUTPUT_QUEUE1", "task1.output1");
    }
    public static String outputQueue2() {
        return env("OUTPUT_QUEUE2", "task1.output2");
    }

    private static String env(String key, String defaultValue) {
        String rc = System.getenv(key);
        if (rc == null)
            return defaultValue;
        return rc;
    }
}
