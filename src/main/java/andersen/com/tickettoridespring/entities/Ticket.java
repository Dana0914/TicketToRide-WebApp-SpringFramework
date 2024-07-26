package andersen.com.tickettoridespring.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ToString.Exclude
    @Column(name = "departure")
    private String departureCity;
    @Column(name = "arrival")
    private String arrivalCity;
    @ToString.Exclude
    @Column(name = "price")
    private Integer price;
    @Column(name = "segments")
    @ToString.Exclude
    private Integer segments;
    @Enumerated(EnumType.STRING)
    @ToString.Exclude
    @Column(name = "currency")
    private Currency currency;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "traveller_id", nullable = false)
    private Traveller traveller;





}
