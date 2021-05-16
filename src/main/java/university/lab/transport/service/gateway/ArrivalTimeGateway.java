package university.lab.transport.service.gateway;

import university.lab.arrival.dto.CalculatedArrivalTimeDto;
import university.lab.transport.dto.ArrivalTimeDto;

public interface ArrivalTimeGateway {
    CalculatedArrivalTimeDto calculateArrivalTime(ArrivalTimeDto arrivalTimeDto);
}
