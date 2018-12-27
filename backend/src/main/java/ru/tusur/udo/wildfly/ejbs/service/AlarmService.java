package ru.tusur.udo.wildfly.ejbs.service;

import ru.tusur.udo.wildfly.ejbs.dto.SensorDTO;
import ru.tusur.udo.wildfly.ejbs.dto.SensorNodeDTO;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class AlarmService {

    @PostConstruct
    public void init() {

    }

    public SensorNodeDTO updateAlarmPool(SensorNodeDTO node) {
        return node;
    }




}
