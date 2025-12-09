package nl.moestuin.moestuinapi.pot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pots")
public class PotController {

    private final PotRepository repository;

    public PotController(PotRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pot> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pot> findById(@PathVariable Long id) {
        Optional<Pot> pot = repository.findById(id);
        return pot.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tower/{towername}")
    public List<Pot> findByTower(@PathVariable String towername) {
        return repository.findByTowername(towername);
    }

    @PostMapping
    public ResponseEntity<Pot> create(@RequestBody Pot pot) {
        Pot saved = repository.save(pot);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pot> update(@PathVariable Long id,
                                      @RequestBody Pot updated) {
        Optional<Pot> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pot pot = existing.get();
        pot.setTowername(updated.getTowername());

        Pot saved = repository.save(pot);
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
