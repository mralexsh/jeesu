package ru.tusur.udo.wildfly.ejbs.dto;

import ru.tusur.udo.wildfly.ejbs.alarm.AlarmAcknowledgeState;
import ru.tusur.udo.wildfly.ejbs.alarm.AlarmActivityState;
import ru.tusur.udo.wildfly.ejbs.alarm.AlarmStatus;

public class AlarmDTO {
    private AlarmAcknowledgeState alarmAcknowledgeState;
    private AlarmActivityState alarmActivityState;
    private AlarmStatus alarmStatus;
    private String alarmMessage;

    public AlarmAcknowledgeState getAlarmAcknowledgeState() {
        return alarmAcknowledgeState;
    }

    public void setAlarmAcknowledgeState(AlarmAcknowledgeState alarmAcknowledgeState) {
        this.alarmAcknowledgeState = alarmAcknowledgeState;
    }

    public AlarmActivityState getAlarmActivityState() {
        return alarmActivityState;
    }

    public void setAlarmActivityState(AlarmActivityState alarmActivityState) {
        this.alarmActivityState = alarmActivityState;
    }

    public AlarmStatus getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(AlarmStatus alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }
}
