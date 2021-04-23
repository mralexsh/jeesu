package ru.tusur.udo.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.tusur.udo.server.dto.NodeInfo;
import ru.tusur.udo.server.services.AggregationService;

@RestController
public class SensorsAggregationController {
    private static final Logger LOG = LoggerFactory.getLogger(SensorsAggregationController.class);

    @Autowired
    AggregationService aggregationService;

    @RequestMapping(value="/aggregator", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void aggregate(@RequestBody NodeInfo nodeInfo) {
        aggregationService.aggregate(nodeInfo);
    }

}
