package university.lab.transport.entity;

import lombok.*;
import university.lab.transport.entity.type.TransportType;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

@Entity
public class PublicTransport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publicTransportId;

    private TransportType type;

    private String brand;

    private String numberSign;

    private Long transportNumber;

    private Long sitsNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Route route;
}
