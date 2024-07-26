package andersen.com.tickettoridespring.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
@Setter
@Getter
public class PaymentResponse {
    private Integer segments;
    private Integer price;
    private String currency;
}
