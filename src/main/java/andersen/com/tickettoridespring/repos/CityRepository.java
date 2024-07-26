package andersen.com.tickettoridespring.repos;

import andersen.com.tickettoridespring.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    City findById(Long ticketId);
    City findByName(String name);

}
