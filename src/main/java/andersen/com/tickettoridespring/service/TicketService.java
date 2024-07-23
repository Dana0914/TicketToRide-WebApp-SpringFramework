package andersen.com.tickettoridespring.service;


import andersen.com.tickettoridespring.dto.PaymentResponse;
import andersen.com.tickettoridespring.dto.TicketRequestDTO;
import andersen.com.tickettoridespring.dto.TicketResponseDTO;
import andersen.com.tickettoridespring.entities.City;
import andersen.com.tickettoridespring.entities.Currency;
import andersen.com.tickettoridespring.entities.Ticket;
import andersen.com.tickettoridespring.repos.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final RouteService routeService;

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
    public TicketResponseDTO saveTicket(Ticket ticket) {
        TicketRequestDTO ticketRequest = new TicketRequestDTO(ticket.getDepartureCity(),
                ticket.getArrivalCity(), ticket.getSegments(), ticket.getPrice(),
                ticket.getCurrency(), ticket.getTraveller().getTravellerAmount(), ticket.getTraveller());
        ticket.setDepartureCity(ticket.getDepartureCity());
        ticket.setArrivalCity(ticket.getArrivalCity());
        ticket.setSegments(ticket.getSegments());
        ticket.setPrice(ticket.getPrice());
        ticket.setCurrency(ticket.getCurrency());
        ticket.setTraveller(ticket.getTraveller());
        ticketRepository.save(ticket);
        String res = ticketRequest.getTravellerAmount() >= ticketRequest.getPrice() ? "success" : "fail";

        if (ticketRequest.getTravellerAmount() >= ticketRequest.getPrice()) {
            int change = Math.abs(ticketRequest.getPrice() - ticketRequest.getTravellerAmount());
            return new TicketResponseDTO(res, change, null, ticketRequest.getCurrency());
        }
        int lackOf = ticketRequest.getTravellerAmount() - ticketRequest.getPrice();
        return new TicketResponseDTO(res, null, lackOf, ticketRequest.getCurrency());
    }
    // find ticket
    public PaymentResponse findTicket(City departure, City arrival) {
        Integer segmentCount = routeService.findSegmentLengthBetweenCities(departure.getId(), arrival.getId());
        int price = calculatePrice(segmentCount);
        return new PaymentResponse(segmentCount, price, Currency.GBP.name());
    }
    // calculate the price of ticket based on number of segments
    private int calculatePrice(Integer segments) {
        int price = 0;
        int quantityOfThreeSegmentsPairs;
        while(segments > 0) {
            if (segments >= 3) {
                quantityOfThreeSegmentsPairs = segments / 3;
                price = price + (quantityOfThreeSegmentsPairs * 10);
                segments = segments - (quantityOfThreeSegmentsPairs * 3);
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
