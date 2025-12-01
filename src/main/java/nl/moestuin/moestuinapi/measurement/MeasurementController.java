package nl.moestuin.moestuinapi.measurement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    private final MeasurementRepository repository;

    public MeasurementController(MeasurementRepository repository) {
        this.repository = repository;
    }

    // 1) Alle measurements
    @GetMapping
    public List<Measurement> getAll() {
        return repository.findAll();
    }

    // 2) Alle measurements voor een bepaald device, bv. /api/measurements/device/bloempot-v1
    @GetMapping("/device/{deviceId}")
    public List<Measurement> getByDevice(@PathVariable String deviceId) {
        return repository.findByDeviceId(deviceId);
    }

    // 3) Laatste measurement voor een device, bv. /api/measurements/device/bloempot-v1/latest
    @GetMapping("/device/{deviceId}/latest")
    public ResponseEntity<Measurement> getLatestByDevice(@PathVariable String deviceId) {
        return repository.findTop1ByDeviceIdOrderByReceivedAtDesc(deviceId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
