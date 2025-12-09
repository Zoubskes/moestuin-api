package nl.moestuin.moestuinapi.tower;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/towers")
public class TowerController {

    private final TowerRepository repository;

    public TowerController(TowerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Tower> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{towername}")
    public ResponseEntity<Tower> findById(@PathVariable String towername) {
        Optional<Tower> tower = repository.findById(towername);
        return tower.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tower> create(@RequestBody Tower tower) {
        if (repository.existsById(tower.getTowername())) {
            return ResponseEntity.badRequest().build();
        }
        Tower saved = repository.save(tower);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{towername}")
    public ResponseEntity<Tower> update(@PathVariable String towername,
                                        @RequestBody Tower updated) {
        Optional<Tower> existing = repository.findById(towername);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Tower tower = existing.get();
        tower.setDeviceId(updated.getDeviceId());
        // towername blijft de id; je kunt eventueel ook hernoemen:
        // tower.setTowername(updated.getTowername());

        Tower saved = repository.save(tower);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{towername}")
    public ResponseEntity<Void> delete(@PathVariable String towername) {
        if (!repository.existsById(towername)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(towername);
        return ResponseEntity.noContent().build();
    }
}
