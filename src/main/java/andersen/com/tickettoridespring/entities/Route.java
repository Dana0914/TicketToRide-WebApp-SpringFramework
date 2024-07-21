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
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private City departureCity;
    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private City arrivalCity;

    @OneToMany(mappedBy = "route")
    List<Ticket> tickets;

    @ManyToMany
    @JoinTable(
            name = "route_segments",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "segments_id")
    )
    private List<Segments> segments;


}
