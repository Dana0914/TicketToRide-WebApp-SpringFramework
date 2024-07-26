package andersen.com.tickettoridespring.repos;

import andersen.com.tickettoridespring.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    @Query(value = "SELECT segment FROM route WHERE departure_city_id = :departure AND arrival_city_id = :arrival", nativeQuery = true)
    Integer findSegmentLengthBetweenTowns(@Param("departure") Long departure,
                                          @Param("arrival") Long arrival);
    Route findRouteById(Long id);
}
