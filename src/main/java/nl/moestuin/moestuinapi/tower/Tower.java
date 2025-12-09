package nl.moestuin.moestuinapi.tower;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tower")
public class Tower {

    @Id
    @Column(name = "towername")
    private String towername;

    @Column(name = "device_id", nullable = false, unique = true)
    private String deviceId;

    public Tower() {
    }

    public Tower(String towername, String deviceId) {
        this.towername = towername;
        this.deviceId = deviceId;
    }

    public String getTowername() {
        return towername;
    }

    public void setTowername(String towername) {
        this.towername = towername;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
