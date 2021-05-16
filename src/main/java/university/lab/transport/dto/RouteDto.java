package university.lab.transport.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class RouteDto {

    private Long routeId;

    private String name;

    private String routeNumber;

    private LocalTime interval;

    private Set<LocalTime> schedule;

    private Long startStationId;

    private Long endStationId;

    private List<Long> stationIds;

    private List<Long> transportIds;
}
