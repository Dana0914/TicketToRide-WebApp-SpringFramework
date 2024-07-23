package andersen.com.tickettoridespring.controller;


import andersen.com.tickettoridespring.dto.PaymentResponse;
import andersen.com.tickettoridespring.dto.TicketResponseDTO;
import andersen.com.tickettoridespring.entities.City;
import andersen.com.tickettoridespring.entities.Currency;
import andersen.com.tickettoridespring.entities.Ticket;
import andersen.com.tickettoridespring.entities.Traveller;
import andersen.com.tickettoridespring.service.CityService;
import andersen.com.tickettoridespring.service.TicketService;
import andersen.com.tickettoridespring.service.TravellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final TravellerService travellerService;
    private final CityService cityService;

    @GetMapping(value = "/create/ticket", produces = "application/json")
    public ResponseEntity<TicketResponseDTO> saveTicket()
    {
        Traveller traveller = new Traveller();
        traveller.setName("Mark");
        traveller.setTravellerAmount(20);
        travellerService.createTraveller(traveller);
        City startCity = cityService.findById(1L);
        City endCity = cityService.findById(4L);
        Ticket ticket = new Ticket();
        ticket.setTraveller(traveller);
        ticket.setPrice(25);
        ticket.setSegments(7);
        ticket.setDepartureCity(startCity.getName());
        ticket.setArrivalCity(endCity.getName());
        ticket.setCurrency(Currency.GBP.name());

        TicketResponseDTO ticketResponseDTO = ticketService.saveTicket(ticket);
        return ResponseEntity.ok(ticketResponseDTO);
    }
    @GetMapping(value = "/find/ticket", produces = "application/json")
    public ResponseEntity<PaymentResponse> findTicket() {
        City startCity = cityService.findById(1L);
        City endCity = cityService.findById(4L);
        PaymentResponse priceResponse = ticketService.findTicket(startCity, endCity);
        return ResponseEntity.ok(priceResponse);
    }


}
