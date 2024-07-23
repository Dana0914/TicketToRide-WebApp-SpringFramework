package andersen.com.tickettoridespring.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
@Entity
@Table(name = "traveller")
public class Traveller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @Column(name = "traveller_amount")
    private Integer travellerAmount;

    @JsonIgnore
    @OneToMany(mappedBy = "traveller")
    private List<Ticket> ticket;


}
