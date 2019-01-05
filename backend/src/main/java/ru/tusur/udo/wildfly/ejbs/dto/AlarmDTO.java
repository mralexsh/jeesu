package ru.tusur.udo.wildfly.ejbs.dto;

import ru.tusur.udo.wildfly.ejbs.alarm.AlarmAcknowledgeState;
import ru.tusur.udo.wildfly.ejbs.alarm.AlarmActivityState;
import ru.tusur.udo.wildfly.ejbs.alarm.AlarmStatus;

public class AlarmDTO {
    private AlarmAcknowledgeState alarmAcknowledgeState;
    private AlarmActivityState alarmActivityState;
    private AlarmStatus alarmStatus;
    private String alarmMessage;
    private String alarmId;
    private long timestamp;
    private String acknowledgeId;

    public String getAcknowledgeId() {
        return acknowledgeId;
    }

    public void setAcknowledgeId(String acknowledgeId) {
        this.acknowledgeId = acknowledgeId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

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
