package university.lab.transport.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

    private Long stationNumber;

    private String streetName;

    private Long streetNumber;

    @ManyToMany(mappedBy = "stations")
    private final Set<Route> routes = new HashSet<>();
}
