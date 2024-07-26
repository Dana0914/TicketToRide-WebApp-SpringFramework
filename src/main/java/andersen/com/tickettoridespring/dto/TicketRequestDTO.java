package andersen.com.tickettoridespring.dto;


import andersen.com.tickettoridespring.entities.Traveller;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter

public class TicketRequestDTO {
    private String departure;
    private String arrival;
    private Integer segments;
    private Integer price;
    private String currency;
    private Integer travellerAmount;
    private Traveller traveller;
}
