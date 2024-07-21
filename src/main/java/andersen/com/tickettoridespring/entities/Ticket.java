package andersen.com.tickettoridespring.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    @Column(name = "price")
    private Integer price;
    @Column(name = "currency")
    private Character currency;
    @Column(name = "result")
    private String result;
    @Column(name = "change")
    private Integer change;
    @Column(name = "lack_of")
    private Integer lackOf;

    @ManyToOne
    @JoinColumn(name = "traveller_id", nullable = false)
    private Traveller traveller;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;




}
