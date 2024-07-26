package andersen.com.tickettoridespring;


import andersen.com.tickettoridespring.dto.TicketResponseDTO;
import andersen.com.tickettoridespring.entities.Ticket;
import andersen.com.tickettoridespring.entities.Traveller;
import andersen.com.tickettoridespring.repos.TicketRepository;
import andersen.com.tickettoridespring.service.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TicketToRideTest {
    @Mock
    private TicketRepository ticketRepository;
    @InjectMocks
    private TicketService ticketService;

    @Test
    public void ticketToSave_Success() {
        Traveller traveller = new Traveller();
        traveller.setName("Ian");
        traveller.setTravellerAmount(27);
        Ticket ticket = new Ticket();
        ticket.setDepartureCity("London");
        ticket.setArrivalCity("Bristol");
        ticket.setTraveller(traveller);
        ticket.setPrice(25);
        ticket.setSegments(7);
        ticket.setCurrency("GBP");

        TicketResponseDTO ticketResponseDTO = ticketService.saveTicket(ticket);

        verify(ticketRepository).save(ticket);
        Assertions.assertEquals("success", ticketResponseDTO.getResult());
        Assertions.assertEquals(2, ticketResponseDTO.getChange());
        Assertions.assertNull(ticketResponseDTO.getLackOf());
        Assertions.assertEquals("GBP", ticketResponseDTO.getCurrency());
    }

    @Test
    public void ticketToSave_Failure() {
        Traveller traveller = new Traveller();
        traveller.setName("Ian");
        traveller.setTravellerAmount(20);
        Ticket ticket = new Ticket();
        ticket.setDepartureCity("London");
        ticket.setArrivalCity("Bristol");
        ticket.setTraveller(traveller);
        ticket.setPrice(25);
        ticket.setSegments(7);
        ticket.setCurrency("GBP");

        TicketResponseDTO ticketResponseDTO = ticketService.saveTicket(ticket);

        verify(ticketRepository).save(ticket);
        Assertions.assertEquals("fail", ticketResponseDTO.getResult());
        Assertions.assertNull(ticketResponseDTO.getChange());
        Assertions.assertEquals(-5, ticketResponseDTO.getLackOf());
        Assertions.assertEquals("GBP", ticketResponseDTO.getCurrency());
    }


}
