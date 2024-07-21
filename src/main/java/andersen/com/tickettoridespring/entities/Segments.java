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
@Table(name = "segments")
public class Segments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "segment")
    private Integer segment;
    @Column(name = "segment_boundary")
    private Integer segmentBoundary;

    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private Segments departureCity;
    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private Segments arrivalCity;

    @ManyToMany(mappedBy = "segments")
    private List<Route> routes;


}
