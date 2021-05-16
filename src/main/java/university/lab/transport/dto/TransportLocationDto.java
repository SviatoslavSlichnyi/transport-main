package university.lab.transport.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class TransportLocationDto {

    Double x;

    Double y;

    String lastSignalTime;
}
