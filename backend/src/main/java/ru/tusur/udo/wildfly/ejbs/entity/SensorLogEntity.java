package ru.tusur.udo.wildfly.ejbs.entity;


import javax.persistence.*;

@Entity
@Table(name="sensors_log")
public class SensorLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "imei")
    private String imei;

    @Column(name = "status")
    private int status;

    @Column(name = "type")
    private int type;

    @Column(name = "value")
    private int value;

    @Column(name = "node")
    private String node;

    @Column(name = "date_time")
    private long dateTime;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }
}
