package andersen.com.tickettoridespring.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Entity
@Table(name = "traveller")
public class Traveller {
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "traveller_amount")
    private Integer travellerAmount;

    @ToString.Exclude
    @OneToOne(mappedBy = "traveller")
    private Ticket ticket;


}
