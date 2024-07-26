package andersen.com.tickettoridespring.repos;

import andersen.com.tickettoridespring.entities.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TravellerRepository extends JpaRepository<Traveller, Integer> {
    Traveller findByName(String name);
    Traveller findById(Long id);
}
