package andersen.com.tickettoridespring.repos;

import andersen.com.tickettoridespring.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findById(Long ticketId);


}
