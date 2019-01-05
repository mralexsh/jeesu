package ru.tusur.udo.wildfly.ejbs;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.tusur.udo.wildfly.ejbs.alarm.*;
import ru.tusur.udo.wildfly.ejbs.dto.AlarmDTO;
import ru.tusur.udo.wildfly.ejbs.dto.SensorDTO;
import ru.tusur.udo.wildfly.ejbs.dto.SensorNodeDTO;
import ru.tusur.udo.wildfly.ejbs.service.AlarmService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AlarmServiceTest {
    private static AlarmPool alarmPool;
    private static AlarmService alarmService;
    private static List<Alarm> alarmsConfig = new ArrayList<>();
    private static SensorNodeDTO nodeDTO;
    private static List<SensorDTO> sensors;
    private static SensorDTO sensorDTO;
    private static String TESTING_IMEI = "I6534";
    private static String alarm1Hash;
    private static String alarm2Hash;

    @BeforeClass
    public static void initAlarmObjects() {
        alarmPool = new AlarmPool();
        alarmsConfig.add(new Alarm() {
            @Override
            public AlarmStatus getAlarmStatus() {
                return AlarmStatus.INFO;
            }
            @Override
            public String getImeiLink() {
                return TESTING_IMEI;
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
        alarmsConfig.add(new Alarm() {
            @Override
            public AlarmStatus getAlarmStatus() {
                return AlarmStatus.WARNING;
            }
            @Override
            public String getImeiLink() {
                return TESTING_IMEI;
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
        alarmPool.setAlarmsConfig(alarmsConfig);
        alarm1Hash = alarmsConfig.get(0).getAcknowledgeId();
        alarm2Hash = alarmsConfig.get(1).getAcknowledgeId();
        alarmService = new AlarmService();
        alarmService.setAlarmPool(alarmPool);

        nodeDTO = new SensorNodeDTO();
        sensorDTO = new SensorDTO();
        sensorDTO.setImei(TESTING_IMEI);
        sensors = new ArrayList<>();
        sensors.add(sensorDTO);
        nodeDTO.setSensors(sensors);
    }
    @Before
    public void setupService() {

    }
    @Test
    public void testOne() {
        assertNotNull(alarmPool);
        assertNotNull(alarmService);
    }

    @Test
    public void testTwo() {
        sensorDTO.setValue(50);
        SensorNodeDTO r = alarmService.updateAlarmPool(nodeDTO);
        SensorDTO s = r.getSensors().get(0);
        assertNotNull(s);
        List<AlarmDTO> alarms = s.getAlarms();
        assertNotNull(alarms);
        assertEquals(alarms.size(), alarmsConfig.size());
        AlarmDTO a = alarms.get(0);
        assertEquals(a.getAlarmActivityState(), AlarmActivityState.ON);
        assertEquals(a.getAlarmAcknowledgeState(), AlarmAcknowledgeState.NOT_ACK);

        a = alarms.get(1);
        assertEquals(a.getAlarmActivityState(), AlarmActivityState.OFF);
        assertEquals(a.getAlarmAcknowledgeState(), AlarmAcknowledgeState.NOT_ACK);
    }
    @Test
    public void testThree() {
        sensorDTO.setValue(85);
        SensorNodeDTO r = alarmService.updateAlarmPool(nodeDTO);
        SensorDTO s = r.getSensors().get(0);
        assertNotNull(s);
        List<AlarmDTO> alarms = s.getAlarms();
        assertNotNull(alarms);
        assertEquals(alarms.size(), alarmsConfig.size());
        AlarmDTO a = alarms.get(0);
        assertEquals(a.getAlarmActivityState(), AlarmActivityState.OFF);
        assertEquals(a.getAlarmAcknowledgeState(), AlarmAcknowledgeState.NOT_ACK);

        a = alarms.get(1);
        assertEquals(a.getAlarmActivityState(), AlarmActivityState.ON);
        assertEquals(a.getAlarmAcknowledgeState(), AlarmAcknowledgeState.NOT_ACK);
    }
    @Test
    public void testFour() {
        sensorDTO.setValue(50);
        alarmService.acknowledgeState(alarm1Hash);
        alarmService.acknowledgeState(alarm2Hash);
        SensorNodeDTO r = alarmService.updateAlarmPool(nodeDTO);
        SensorDTO s = r.getSensors().get(0);
        assertNotNull(s);
        List<AlarmDTO> alarms = s.getAlarms();
        assertNotNull(alarms);
        assertEquals(alarms.size(), alarmsConfig.size());

        AlarmDTO a = alarms.get(0);
        AlarmDTO b = alarms.get(1);

        assertEquals(a.getAlarmActivityState(), AlarmActivityState.ON);
        assertEquals(a.getAlarmAcknowledgeState(), AlarmAcknowledgeState.ACK);
        assertEquals(b.getAlarmActivityState(), AlarmActivityState.OFF);
        assertEquals(b.getAlarmAcknowledgeState(), AlarmAcknowledgeState.ACK);
    }

    @Test
    public void testFive() {
        sensorDTO.setValue(50);
        alarmService.acknowledgeState(alarm1Hash);
        alarmService.acknowledgeState(alarm2Hash);

        alarmService.updateAlarmPool(nodeDTO);
        sensorDTO.setValue(85);
        alarmService.updateAlarmPool(nodeDTO);
        SensorNodeDTO r = alarmService.updateAlarmPool(nodeDTO);
        SensorDTO s = r.getSensors().get(0);
        assertNotNull(s);
        List<AlarmDTO> alarms = s.getAlarms();
        assertNotNull(alarms);
        assertEquals(alarms.size(), alarmsConfig.size());

        AlarmDTO a = alarms.get(0);
        AlarmDTO b = alarms.get(1);

        assertEquals(a.getAlarmActivityState(), AlarmActivityState.OFF);
        assertEquals(a.getAlarmAcknowledgeState(), AlarmAcknowledgeState.ACK);
        assertEquals(b.getAlarmActivityState(), AlarmActivityState.ON);
        assertEquals(b.getAlarmAcknowledgeState(), AlarmAcknowledgeState.NOT_ACK);
    }
}
