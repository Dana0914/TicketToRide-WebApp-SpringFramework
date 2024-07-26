package andersen.com.tickettoridespring.service;


import andersen.com.tickettoridespring.entities.Route;
import andersen.com.tickettoridespring.repos.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RouteService {
    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
    public Route getRouteById(Long id) {
        return routeRepository.findRouteById(id);
    }
    public Integer findSegmentLengthBetweenCities(Long departure, Long arrival) {
        return routeRepository.findSegmentLengthBetweenTowns(departure, arrival);
    }

}
