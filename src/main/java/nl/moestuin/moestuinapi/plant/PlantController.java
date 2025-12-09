package nl.moestuin.moestuinapi.plant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    private final PlantRepository repository;

    public PlantController(PlantRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Plant> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> findById(@PathVariable Long id) {
        Optional<Plant> plant = repository.findById(id);
        return plant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Plant> findByName(@PathVariable String name) {
        return repository.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plant> create(@RequestBody Plant plant) {
        Plant saved = repository.save(plant);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> update(@PathVariable Long id,
                                        @RequestBody Plant updated) {
        Optional<Plant> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Plant plant = existing.get();
        plant.setName(updated.getName());
        plant.setImage(updated.getImage());
        plant.setDescription(updated.getDescription());
        plant.setGrowthcycle(updated.getGrowthcycle());

        Plant saved = repository.save(plant);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
