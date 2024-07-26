package andersen.com.tickettoridespring.controller;


import andersen.com.tickettoridespring.dto.PaymentResponse;
import andersen.com.tickettoridespring.dto.TicketRequestDTO;
import andersen.com.tickettoridespring.dto.TicketResponseDTO;
import andersen.com.tickettoridespring.entities.*;
import andersen.com.tickettoridespring.service.CityService;
import andersen.com.tickettoridespring.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final CityService cityService;


    @PostMapping("/create/ticket")
    public TicketResponseDTO saveTicket(@RequestBody TicketRequestDTO ticketRequestDTO)
    {

        return ticketService.saveTicket(ticketRequestDTO);


    }

    @GetMapping(value = "/find/ticket", produces = "application/json")
    public PaymentResponse findTicket(@RequestParam(name = "departure") String departure,
                                      @RequestParam(name = "arrival") String arrival) {
        City startCity = cityService.findByName(departure);
        City endCity = cityService.findByName(arrival);
        return ticketService.findTicket(startCity, endCity);
    }


}
