package ru.tusur.udo.wildfly.ejbs.alarm;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

//Keep it simple yet

@Stateless
public class AlarmPool {

    private List<Alarm> alarmsConfig = new ArrayList<>();

    public AlarmPool() {
        this.alarmsConfig.add(new Alarm() {

            @Override
            public AlarmStatus getAlarmStatus() {
                return AlarmStatus.INFO;
            }
            @Override
            public String getImeiLink() {
                return "IMEI123";
            }

            @Override
            public Boolean check(int value) {
                return value > 10 && value < 80;
            }

            @Override
            public String getAlarmMessage() {
                return "Normal value";
            }
        });
        this.alarmsConfig.add(new Alarm() {
            @Override
            public AlarmStatus getAlarmStatus() {
                return AlarmStatus.WARNING;
            }
            @Override
            public String getImeiLink() {
                return "IMEI123";
            }

            @Override
            public Boolean check(int value) {
                return value >= 80 && value < 90;
            }

            @Override
            public String getAlarmMessage() {
                return "Maximum value";
            }
        });
        this.alarmsConfig.add(new Alarm() {
            @Override
            public AlarmStatus getAlarmStatus() {
                return AlarmStatus.ERROR;
            }
            @Override
            public String getImeiLink() {
                return "IMEI123";
            }

            @Override
            public Boolean check(int value) {
                return value >= 90;
            }

            @Override
            public String getAlarmMessage() {
                return "Critical maximum value";
            }
        });
        this.alarmsConfig.add(new Alarm() {
            @Override
            public AlarmStatus getAlarmStatus() {
                return AlarmStatus.WARNING;
            }
            @Override
            public String getImeiLink() {
                return "IMEI123";
            }

            @Override
            public Boolean check(int value) {
                return value < 20 && value >= 10;
            }

            @Override
            public String getAlarmMessage() {
                return "Minimum value";
            }
        });
        this.alarmsConfig.add(new Alarm() {
            @Override
            public AlarmStatus getAlarmStatus() {
                return AlarmStatus.ERROR;
            }
            @Override
            public String getImeiLink() {
                return "IMEI123";
            }

            @Override
            public Boolean check(int value) {
                return value < 10;
            }

            @Override
            public String getAlarmMessage() {
                return "Critical minimum value";
            }
        });


    }

    public List<Alarm> getAlarmsConfig() {
        return alarmsConfig;
    }

    public void setAlarmsConfig(List<Alarm> alarmsConfig) {
        this.alarmsConfig = alarmsConfig;
    }

}
