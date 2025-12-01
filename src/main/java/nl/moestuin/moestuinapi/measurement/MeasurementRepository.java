package nl.moestuin.moestuinapi.measurement;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MeasurementRepository extends MongoRepository<Measurement, String> {

    // alle metingen van één device
    List<Measurement> findByDeviceId(String deviceId);

    // laatste meting van een device
    Optional<Measurement> findTop1ByDeviceIdOrderByReceivedAtDesc(String deviceId);
}
