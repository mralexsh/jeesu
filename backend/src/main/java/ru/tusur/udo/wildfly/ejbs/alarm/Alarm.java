package ru.tusur.udo.wildfly.ejbs.alarm;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.UUID;

public class Alarm {
    private AlarmActivityState alarmActivityState;
    private AlarmAcknowledgeState alarmAcknowledgeState;

    private AlarmStatus alarmStatus;
    private String alarmMessage;
    private String imeiLink;
    private String alarmId;
    private long timestamp;

    public AlarmActivityState getAlarmActivityState() {
        return alarmActivityState;
    }

    public void setAlarmActivityState(AlarmActivityState alarmActivityState) {
        this.alarmActivityState = alarmActivityState;
    }

    public AlarmAcknowledgeState getAlarmAcknowledgeState() {
        return alarmAcknowledgeState;
    }

    public void setAlarmAcknowledgeState(AlarmAcknowledgeState alarmAcknowledgeState) {
        this.alarmAcknowledgeState = alarmAcknowledgeState;
    }

    public String getAcknowledgeId() {
        return calcAcknowledgeId();
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public  Alarm()
    {
        this.alarmId = UUID.randomUUID().toString();
        this.timestamp = new Date().getTime();
        this.alarmAcknowledgeState = AlarmAcknowledgeState.NOT_ACK;
    }
    public String getAlarmId() {
        return alarmId;
    }
    public AlarmStatus getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(AlarmStatus alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getImeiLink() {
        return imeiLink;
    }

    public void setImeiLink(String imeiLink) {
        this.imeiLink = imeiLink;
    }

    public Boolean check(int value) {
        return false;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    private String calcAcknowledgeId() {
        String hashingStr = imeiLink + alarmStatus + alarmMessage;
        return DigestUtils.md5Hex(hashingStr);
    }

}
