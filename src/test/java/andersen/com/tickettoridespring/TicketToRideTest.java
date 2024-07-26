package andersen.com.tickettoridespring;


import andersen.com.tickettoridespring.dto.TicketRequestDTO;
import andersen.com.tickettoridespring.dto.TicketResponseDTO;
import andersen.com.tickettoridespring.entities.Currency;
import andersen.com.tickettoridespring.entities.Ticket;
import andersen.com.tickettoridespring.entities.Traveller;
import andersen.com.tickettoridespring.repos.TicketRepository;
import andersen.com.tickettoridespring.repos.TravellerRepository;
import andersen.com.tickettoridespring.service.TicketService;
import andersen.com.tickettoridespring.service.TravellerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class TicketToRideTest {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private TravellerRepository travellerRepository;
    @InjectMocks
    private TicketService ticketService;
    @InjectMocks
    private TravellerService travellerService;

    @Test
    public void ticketToSave_Success() {
        Traveller traveller = new Traveller();
        traveller.setName("Ian");
        traveller.setTravellerAmount(27);
        travellerRepository.save(traveller);


        Ticket ticket = new Ticket();
        ticket.setDepartureCity("London");
        ticket.setArrivalCity("Bristol");
        ticket.setTraveller(traveller);
        ticket.setPrice(25);
        ticket.setSegments(7);
        ticket.setCurrency(Currency.GBP);
        traveller.setTicket(ticket);
        ticketRepository.save(ticket);

        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO(ticket.getDepartureCity(),
                ticket.getArrivalCity(), ticket.getSegments(), ticket.getPrice(),
                ticket.getCurrency().name(), ticket.getTraveller().getTravellerAmount(), ticket.getTraveller().getName());


        TicketResponseDTO ticketResponseDTO = ticketService.saveTicket(ticketRequestDTO);


        verify(ticketRepository).save(ticket);
        verify(travellerRepository).save(traveller);


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
        travellerRepository.save(traveller);

        Ticket ticket = new Ticket();
        ticket.setDepartureCity("London");
        ticket.setArrivalCity("Bristol");
        ticket.setTraveller(traveller);
        ticket.setPrice(25);
        ticket.setSegments(7);
        ticket.setCurrency(Currency.GBP);
        traveller.setTicket(ticket);
        ticketRepository.save(ticket);


        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO(ticket.getDepartureCity(),
                ticket.getArrivalCity(), ticket.getSegments(), ticket.getPrice(),
                ticket.getCurrency().name(), ticket.getTraveller().getTravellerAmount(), ticket.getTraveller().getName());

        TicketResponseDTO ticketResponseDTO = ticketService.saveTicket(ticketRequestDTO);

        verify(ticketRepository).save(ticket);
        verify(travellerRepository).save(traveller);


        Assertions.assertEquals("fail", ticketResponseDTO.getResult());
        Assertions.assertNull(ticketResponseDTO.getChange());
        Assertions.assertEquals(-5, ticketResponseDTO.getLackOf());
        Assertions.assertEquals("GBP", ticketResponseDTO.getCurrency());
    }


}
