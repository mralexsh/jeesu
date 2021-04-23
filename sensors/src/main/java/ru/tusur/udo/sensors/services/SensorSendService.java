package ru.tusur.udo.sensors.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

@Service
public class SensorSendService extends Thread {

    private static final Logger LOG =
            LoggerFactory.getLogger(SensorSendService.class);


    @Value("${polling.interval}")
    private int pollingInterval;

    @Value("${server.url}")
    private String serverUrl;


    @Autowired
    SensorInfoService sensorInfoService;

    private HttpURLConnection http;
    private URL url;

    @PostConstruct
    private void init() throws IOException {
        url = new URL(serverUrl);
        start();
    }

    public void run() {
        while (true) {
            try {
                sendSensorsInfo(sensorInfoService.makeSensorNodeInfo());
                sleep(pollingInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendSensorsInfo(String sensorNodeInfo) {
        byte[] out = sensorNodeInfo.getBytes(StandardCharsets.UTF_8);
        try (OutputStream os = getOutputStream(out)) {
            if (os != null) os.write(out);
        } catch (IOException e) {
            LOG.error("Sending error:" + e.getMessage());
        }
    }

    private OutputStream getOutputStream(final byte[] out) {
        try {
            URLConnection urlConnection = url.openConnection();
            http = (HttpURLConnection) urlConnection;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.setFixedLengthStreamingMode(out.length);
            http.connect();
            return http.getOutputStream();
        } catch (IOException e) {
            LOG.error("Error occurred due connection to  " + serverUrl + " " + e.getMessage());
        }
        return null;
    }

}
