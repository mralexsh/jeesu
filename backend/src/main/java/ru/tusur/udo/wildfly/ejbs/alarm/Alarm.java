package ru.tusur.udo.wildfly.ejbs.alarm;

public class Alarm {
    private AlarmActivityState alarmActivityState;
    private AlarmAcknowledgeState alarmAcknowledgeState;

    private AlarmStatus alarmStatus;
    private String alarmMessage;
    private String imeiLink;

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


}
