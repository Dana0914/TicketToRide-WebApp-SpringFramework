package andersen.com.tickettoridespring.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
@Data
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "departure")
    private String departureCity;
    @Column(name = "arrival")
    private String arrivalCity;
    @Column(name = "price")
    private Integer price;
    @Column(name = "segments")
    private Integer segments;
    @Column(name = "currency")
    private String currency;



    @ManyToOne
    @JoinColumn(name = "traveller_id", nullable = false)
    private Traveller traveller;





}
