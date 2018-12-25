package ru.tusur.udo.wildfly.ejbs.repository;

import ru.tusur.udo.wildfly.ejbs.SensorDTO;
import ru.tusur.udo.wildfly.ejbs.entity.SensorLogEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SensorLogRepository {

    @PersistenceContext(unitName = "sensors")
    private EntityManager entityManager;

    public void create(SensorDTO sensorLog, String node) {
        SensorLogEntity logEntity = new SensorLogEntity();
        logEntity.setValue(sensorLog.getValue());
        logEntity.setType(sensorLog.getType());
        logEntity.setStatus(sensorLog.getStatus());
        logEntity.setImei(sensorLog.getImei());
        logEntity.setDateTime(sensorLog.getTimestamp());
        logEntity.setNode(node);
        this.entityManager.persist(logEntity);
    }
}
