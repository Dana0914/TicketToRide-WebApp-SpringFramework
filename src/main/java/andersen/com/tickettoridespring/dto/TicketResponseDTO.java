package andersen.com.tickettoridespring.dto;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Data
public class TicketResponseDTO {
    private String result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer change;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer lackOf;
    private String currency;

}
