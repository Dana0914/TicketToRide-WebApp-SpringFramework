package andersen.com.tickettoridespring.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "departureCity")
    private Set<Route> departureRoutes;

    @OneToMany(mappedBy = "arrivalCity")
    private Set<Route> arrivalRoutes;

}
