package nl.moestuin.moestuinapi.plantinpot;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "plant_in_pot")
public class PlantInPot {

    @Id
    @Column(name = "pot_id")
    private Long potId;

    @Column(name = "plant_id", nullable = false)
    private Long plantId;

    @Column(name = "user")
    private String user;

    @Column(name = "start_timestamp")
    private OffsetDateTime startTimestamp;

    public PlantInPot() {
    }

    public Long getPotId() {
        return potId;
    }

    public void setPotId(Long potId) {
        this.potId = potId;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public OffsetDateTime getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(OffsetDateTime startTimestamp) {
        this.startTimestamp = startTimestamp;
    }
}
