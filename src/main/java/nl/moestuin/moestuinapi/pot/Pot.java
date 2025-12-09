package nl.moestuin.moestuinapi.pot;

import jakarta.persistence.*;

@Entity
@Table(name = "pot")
public class Pot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pot_id")
    private Long potId;

    @Column(name = "towername", nullable = false)
    private String towername;

    public Pot() {
    }

    public Long getPotId() {
        return potId;
    }

    public void setPotId(Long potId) {
        this.potId = potId;
    }

    public String getTowername() {
        return towername;
    }

    public void setTowername(String towername) {
        this.towername = towername;
    }
}
