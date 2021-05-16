package university.lab.transport.service;

import university.lab.transport.dto.TransportLocationDto;

public interface TransportLocationByGpsTrackerService {

    TransportLocationDto getLocationByTransportId(Long transportId);
}
