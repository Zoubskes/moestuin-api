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

    // Alle towers ophalen
    @GetMapping
    public List<Tower> getAll() {
        return repository.findAll();
    }

    // Eén tower op towername
    @GetMapping("/{towername}")
    public ResponseEntity<Tower> getByTowername(@PathVariable String towername) {
        Optional<Tower> tower = repository.findById(towername);
        return tower.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Nieuwe tower aanmaken
    // POST /api/towers  met body: { "towername": "tower-1" }
    @PostMapping
    public Tower create(@RequestBody Tower tower) {
        return repository.save(tower);
    }

    // Tower hernoemen (update van key)
    // PUT /api/towers/{towername}  body: { "towername": "nieuwe-naam" }
    @PutMapping("/{towername}")
    public ResponseEntity<Tower> update(
            @PathVariable String towername,
            @RequestBody Tower updated
    ) {
        if (!repository.existsById(towername)) {
            return ResponseEntity.notFound().build();
        }

        // als je de primary key wilt "hernoemen"
        // eerst oude verwijderen, dan nieuwe opslaan
        repository.deleteById(towername);
        Tower saved = repository.save(new Tower(updated.getTowername()));
        return ResponseEntity.ok(saved);
    }

    // Tower verwijderen
    @DeleteMapping("/{towername}")
    public ResponseEntity<Void> delete(@PathVariable String towername) {
        if (!repository.existsById(towername)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(towername);
        return ResponseEntity.noContent().build();
    }
}
