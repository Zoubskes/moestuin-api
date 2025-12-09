package nl.moestuin.moestuinapi.plant;

import jakarta.persistence.*;

@Entity
@Table(name = "plant")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id")
    private Long plantId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "image", unique = true)
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "growthcycle")
    private Integer growthcycle;

    public Plant() {
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGrowthcycle() {
        return growthcycle;
    }

    public void setGrowthcycle(Integer growthcycle) {
        this.growthcycle = growthcycle;
    }
}
