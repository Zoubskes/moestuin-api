package nl.moestuin.moestuinapi.pot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PotRepository extends JpaRepository<Pot, Long> {

    List<Pot> findByTowername(String towername);
}
