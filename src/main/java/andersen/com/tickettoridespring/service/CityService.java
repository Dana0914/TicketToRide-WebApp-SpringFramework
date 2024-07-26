package andersen.com.tickettoridespring.service;

import andersen.com.tickettoridespring.entities.City;
import andersen.com.tickettoridespring.repos.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityService {
    private final CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }
    public City findById(Long id) {
        return cityRepository.findById(id);
    }
    public City findByName(String name) {
        return cityRepository.findByName(name);
    }
}
