package nl.moestuin.moestuinapi.plantinpot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plant-in-pot")
public class PlantInPotController {

    private final PlantInPotRepository repository;

    public PlantInPotController(PlantInPotRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<PlantInPot> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{potId}")
    public ResponseEntity<PlantInPot> findById(@PathVariable Long potId) {
        Optional<PlantInPot> pip = repository.findById(potId);
        return pip.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlantInPot> create(@RequestBody PlantInPot pip) {
        if (repository.existsById(pip.getPotId())) {
            return ResponseEntity.badRequest().build();
        }
        PlantInPot saved = repository.save(pip);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{potId}")
    public ResponseEntity<PlantInPot> update(@PathVariable Long potId,
                                             @RequestBody PlantInPot updated) {
        Optional<PlantInPot> existing = repository.findById(potId);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PlantInPot pip = existing.get();
        pip.setPlantId(updated.getPlantId());
        pip.setUser(updated.getUser());
        pip.setStartTimestamp(updated.getStartTimestamp());

        PlantInPot saved = repository.save(pip);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{potId}")
    public ResponseEntity<Void> delete(@PathVariable Long potId) {
        if (!repository.existsById(potId)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(potId);
        return ResponseEntity.noContent().build();
    }
}
