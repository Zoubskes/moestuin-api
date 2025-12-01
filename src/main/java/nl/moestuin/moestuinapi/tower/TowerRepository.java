package nl.moestuin.moestuinapi.tower;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TowerRepository extends JpaRepository<Tower, String> {

    // Extra query methods zijn nu niet nodig, maar kun je later toevoegen
}
