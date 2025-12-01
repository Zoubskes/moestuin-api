package nl.moestuin.moestuinapi.tower;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tower")
public class Tower {

    // We gebruiken towername als primary key
    @Id
    @Column(name = "towername")
    private String towername;

    public Tower() {
    }

    public Tower(String towername) {
        this.towername = towername;
    }

    public String getTowername() {
        return towername;
    }

    public void setTowername(String towername) {
        this.towername = towername;
    }
}
