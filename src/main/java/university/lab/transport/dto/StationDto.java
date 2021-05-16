package university.lab.transport.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class StationDto {

    private Long stationId;

    private Long stationNumber;

    private String streetName;

    private Long streetNumber;

    private Set<Long> routeIds;
}
