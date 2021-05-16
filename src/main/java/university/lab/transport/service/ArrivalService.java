package university.lab.transport.service;

import university.lab.transport.dto.ArrivalTimeDto;

public interface ArrivalService {
    ArrivalTimeDto determineTransportArrivalTimeToStationByTransportId(Long stationId, Long transportId);
}
