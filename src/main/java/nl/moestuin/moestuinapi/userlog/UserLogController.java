package nl.moestuin.moestuinapi.userlog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-logs")
public class UserLogController {

    private final UserLogRepository repository;

    public UserLogController(UserLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<UserLog> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLog> findById(@PathVariable Long id) {
        Optional<UserLog> log = repository.findById(id);
        return log.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserLog> create(@RequestBody UserLog log) {
        UserLog saved = repository.save(log);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserLog> update(@PathVariable Long id,
                                          @RequestBody UserLog updated) {
        Optional<UserLog> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserLog log = existing.get();
        log.setUser(updated.getUser());
        log.setGebeurtenis(updated.getGebeurtenis());
        log.setTimestamp(updated.getTimestamp());

        UserLog saved = repository.save(log);
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
