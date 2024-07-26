package andersen.com.tickettoridespring.service;

import andersen.com.tickettoridespring.entities.Traveller;
import andersen.com.tickettoridespring.repos.TravellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellerService {
    private final TravellerRepository travellerRepository;

    public TravellerService(TravellerRepository travellerRepository) {
        this.travellerRepository = travellerRepository;
    }
    public List<Traveller> findAll() {
        return travellerRepository.findAll();
    }
    public Traveller findTravellerById(Long id) {
        return travellerRepository.findById(id);
    }
    public Traveller createTraveller(Traveller traveller) {
        return travellerRepository.save(traveller);
    }
    public void deleteTravellerById(Long id) {
        Traveller getId = travellerRepository.findById(id);
        travellerRepository.delete(getId);
    }
}
