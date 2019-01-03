package ru.tusur.udo.wildfly.ejbs.service;

import ru.tusur.udo.wildfly.ejbs.alarm.Alarm;
import ru.tusur.udo.wildfly.ejbs.alarm.AlarmAcknowledgeState;
import ru.tusur.udo.wildfly.ejbs.alarm.AlarmActivityState;
import ru.tusur.udo.wildfly.ejbs.alarm.AlarmPool;
import ru.tusur.udo.wildfly.ejbs.dto.AlarmDTO;
import ru.tusur.udo.wildfly.ejbs.dto.SensorDTO;
import ru.tusur.udo.wildfly.ejbs.dto.SensorNodeDTO;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Startup
public class AlarmService {

    @Inject
    private AlarmPool alarmPool;

    @PostConstruct
    public void init() {

    }

    public SensorNodeDTO updateAlarmPool(SensorNodeDTO node) {
        node.getSensors().forEach(sensorDTO -> sensorDTO.setAlarms(calcAlarms(sensorDTO)));
        return node;
    }

    private List<AlarmDTO> calcAlarms(SensorDTO sensorDTO) {
        return this.alarmPool.getAlarmsConfig()
                .stream()
                .filter(alarm -> alarm.getImeiLink().equals(sensorDTO.getImei()))
                .map(alarm -> createAlarmState(alarm, sensorDTO))
                .collect(Collectors.toList());
    }

    private AlarmDTO createAlarmState(Alarm alarm, SensorDTO sensorDTO) {
        AlarmDTO alarmDTO = new AlarmDTO();
        if (alarm.check(sensorDTO.getValue())) {
            alarmDTO.setAlarmAcknowledgeState(AlarmAcknowledgeState.NOT_ACK);
            alarmDTO.setAlarmActivityState(AlarmActivityState.ON);
        } else {
            alarmDTO.setAlarmAcknowledgeState(AlarmAcknowledgeState.ACK);
            alarmDTO.setAlarmActivityState(AlarmActivityState.OFF);
        }

        alarmDTO.setAlarmId(alarm.getAlarmId());
        alarmDTO.setTimestamp(alarm.getTimestamp());
        alarmDTO.setAlarmMessage(alarm.getAlarmMessage());
        alarmDTO.setAlarmStatus(alarm.getAlarmStatus());

        return alarmDTO;
    }


}
