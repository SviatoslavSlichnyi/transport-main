package university.lab.transport.dto;

import lombok.*;
import university.lab.transport.entity.type.MeasurementType;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ArrivalTimeDto {

    private PublicTransportDto transport;

    private RouteDto route;

    private StationDto selectedStation;

    private String expectedArrivalTime;

    private Double distance;

    private MeasurementType distanceMeasurementType;
}
