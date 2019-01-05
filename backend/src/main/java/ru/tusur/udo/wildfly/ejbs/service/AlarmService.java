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

    private List<Alarm> alarms;

    public AlarmPool getAlarmPool() {
        return alarmPool;
    }

    public void setAlarmPool(AlarmPool alarmPool) {
        this.alarmPool = alarmPool;
        this.alarms = alarmPool.getAlarmsConfig();
    }

    @PostConstruct
    public void init() {
        this.alarms = alarmPool.getAlarmsConfig();
    }
    public void acknowledgeState(String acknowledgeId) {
        this.alarms.forEach(alarm -> {
            if (alarm.getAcknowledgeId().equals(acknowledgeId)) {
                alarm.setAlarmAcknowledgeState(AlarmAcknowledgeState.ACK);
            }
        });
    }
    public SensorNodeDTO updateAlarmPool(SensorNodeDTO node) {
        node.getSensors().forEach(sensorDTO -> sensorDTO.setAlarms(calcAlarms(sensorDTO)));
        return node;
    }

    private List<AlarmDTO> calcAlarms(SensorDTO sensorDTO) {
        return this.alarms
                .stream()
                .filter(alarm -> alarm.getImeiLink().equals(sensorDTO.getImei()))
                .map(alarm -> createAlarmState(alarm, sensorDTO))
                .collect(Collectors.toList());
    }

    private AlarmDTO createAlarmState(Alarm alarm, SensorDTO sensorDTO) {
        AlarmDTO alarmDTO = new AlarmDTO();
        if (alarm.check(sensorDTO.getValue())) {
            alarmDTO.setAlarmActivityState(AlarmActivityState.ON);
            if (needToResetAcknowledge(alarm, sensorDTO)) {
                alarmDTO.setAlarmAcknowledgeState(AlarmAcknowledgeState.NOT_ACK);
            } else {
                alarmDTO.setAlarmAcknowledgeState(alarm.getAlarmAcknowledgeState());
            }
        } else {
            alarmDTO.setAlarmActivityState(AlarmActivityState.OFF);
            alarmDTO.setAlarmAcknowledgeState(alarm.getAlarmAcknowledgeState());
        }

        alarmDTO.setAlarmId(alarm.getAlarmId());
        alarmDTO.setAcknowledgeId(alarm.getAcknowledgeId());
        alarmDTO.setTimestamp(alarm.getTimestamp());
        alarmDTO.setAlarmMessage(alarm.getAlarmMessage());
        alarmDTO.setAlarmStatus(alarm.getAlarmStatus());

        return alarmDTO;
    }
    private boolean needToResetAcknowledge(Alarm alarm, SensorDTO sensorDTO) {
        List<AlarmDTO> l = sensorDTO.getAlarms()
                .stream()
                .filter(alarmDTO -> alarmDTO.getAcknowledgeId().equals(alarm.getAcknowledgeId()))
                .collect(Collectors.toList());
        return (l.size() == 1) && (l.get(0).getAlarmActivityState() == AlarmActivityState.OFF);

    }

}
