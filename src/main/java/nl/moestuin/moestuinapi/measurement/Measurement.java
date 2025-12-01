package nl.moestuin.moestuinapi.measurement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "measurements")
public class Measurement {

    @Id
    private String id;

    @Field("device_id")
    private String deviceId;

    @Field("received_at")
    private String receivedAt;          // bijv. "2025-12-01T12:39:39.461660442Z"

    @Field("ambientTemperature")
    private Double ambientTemperature;

    @Field("distance")
    private Double distance;

    @Field("lux")
    private Integer lux;

    @Field("pH")
    private Double pH;

    @Field("pump")
    private Integer pump;

    @Field("tds")
    private Integer tds;

    @Field("temperature")
    private Double temperature;

    public Measurement() {
    }

    // Getters & setters

    public String getId() {
        return id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(String receivedAt) {
        this.receivedAt = receivedAt;
    }

    public Double getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(Double ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getLux() {
        return lux;
    }

    public void setLux(Integer lux) {
        this.lux = lux;
    }

    public Double getPH() {
        return pH;
    }

    public void setPH(Double pH) {
        this.pH = pH;
    }

    public Integer getPump() {
        return pump;
    }

    public void setPump(Integer pump) {
        this.pump = pump;
    }

    public Integer getTds() {
        return tds;
    }

    public void setTds(Integer tds) {
        this.tds = tds;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
