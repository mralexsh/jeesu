package ru.tusur.udo.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ru.tusur.udo.server.dto.NodeInfo;
import ru.tusur.udo.server.services.AggregationService;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
public class MainPageController {

    @Value("${:classpath:/public/index.html}")
    private Resource index;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    AggregationService aggregationService;


    @GetMapping(value="/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ResponseEntity actions() throws IOException {
        return ResponseEntity.ok(new InputStreamResource(index.getInputStream()));
    }

    @Scheduled(fixedDelayString = "${sensors.runtime.interval}", initialDelayString = "${sensors.runtime.interval}")
    public void sendSensorsInfoToSocket() {
        simpMessagingTemplate.convertAndSend("/sensors/polling", aggregationService.getAggregationInfo());
    }

    @GetMapping(value="/info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map info() {
        return Collections.singletonMap("version", "1.0");
    }

}
