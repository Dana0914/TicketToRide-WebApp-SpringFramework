package andersen.com.tickettoridespring.service;


import andersen.com.tickettoridespring.dto.PaymentResponse;
import andersen.com.tickettoridespring.dto.TicketRequestDTO;
import andersen.com.tickettoridespring.dto.TicketResponseDTO;
import andersen.com.tickettoridespring.entities.*;
import andersen.com.tickettoridespring.repos.RouteRepository;
import andersen.com.tickettoridespring.repos.TicketRepository;
import andersen.com.tickettoridespring.repos.TravellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TravellerRepository travellerRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TravellerRepository travellerRepository, RouteRepository routeRepository) {
        this.ticketRepository = ticketRepository;
        this.travellerRepository = travellerRepository;
        this.routeRepository = routeRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    public Ticket findTicketById(Long id) {
        return ticketRepository.findById(id);
    }
    public void deleteTicket(Long id) {
        Ticket getId = ticketRepository.findById(id);
        ticketRepository.delete(getId);
    }
    // save a ticket
    public TicketResponseDTO saveTicket(TicketRequestDTO ticketRequestDTO) {
        Ticket ticket = new Ticket();
        Traveller traveller = new Traveller();
        traveller.setName(ticketRequestDTO.getTraveller());
        traveller.setTravellerAmount(ticketRequestDTO.getTravellerAmount());
        travellerRepository.save(traveller);

        ticket.setDepartureCity(ticketRequestDTO.getDeparture());
        ticket.setArrivalCity(ticketRequestDTO.getArrival());
        ticket.setSegments(ticketRequestDTO.getSegments());
        ticket.setPrice(ticketRequestDTO.getPrice());
        ticket.setCurrency(Currency.valueOf(ticketRequestDTO.getCurrency()));
        ticket.setTraveller(traveller);
        traveller.setTicket(ticket);
        ticketRepository.save(ticket);


        String res = ticket.getTraveller().getTravellerAmount() >= ticket.getPrice() ? "success" : "fail";
        if (ticket.getTraveller().getTravellerAmount() >= ticket.getPrice()) {
            int change = Math.abs(ticket.getPrice() - ticket.getTraveller().getTravellerAmount());
            return new TicketResponseDTO(res, change, null, ticket.getCurrency().name());
        }
        int lackOf = ticket.getTraveller().getTravellerAmount() - ticket.getPrice();
        return new TicketResponseDTO(res, null, lackOf, ticket.getCurrency().name());
    }


    // find ticket
    public PaymentResponse findTicket(City departure, City arrival) {
        Integer segmentCount = routeRepository.findSegmentLengthBetweenTowns(departure.getId(), arrival.getId());
        int price = calculatePrice(segmentCount);
        return new PaymentResponse(segmentCount, price, Currency.GBP.name());
    }
    // calculate the price of ticket based on number of segments
    private int calculatePrice(Integer segments) {
        int price = 0;
        while(segments > 0) {
            if (segments >= 3) {
                price = price + 10;
                segments -= 3;
            } else if (segments == 2) {
                price = price + 7;
                segments -= 2;
            } else {
                price = price + 5;
                segments -= 1;
            }
        }
        return price;
    }


}
