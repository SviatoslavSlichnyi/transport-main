package university.lab.transport.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import university.lab.arrival.dto.CalculatedArrivalTimeDto;
import university.lab.transport.dto.ArrivalTimeDto;
import university.lab.transport.dto.PublicTransportDto;
import university.lab.transport.dto.RouteDto;
import university.lab.transport.dto.StationDto;
import university.lab.transport.entity.type.MeasurementType;
import university.lab.transport.service.ArrivalService;
import university.lab.transport.service.PublicTransportCrudService;
import university.lab.transport.service.RouteCrudService;
import university.lab.transport.service.StationCrudService;
import university.lab.transport.service.gateway.ArrivalTimeGateway;

@RequiredArgsConstructor

@Service
public class ArrivalServiceImpl implements ArrivalService {

    private final PublicTransportCrudService transportCrudService;
    private final RouteCrudService routeCrudService;
    private final StationCrudService stationCrudService;
    private final ArrivalTimeGateway arrivalTimeGateway;

    @Override
    public ArrivalTimeDto determineTransportArrivalTimeToStationByTransportId(Long stationId, Long transportId) {
        PublicTransportDto transportDto = transportCrudService.fetchTransportDtoById(transportId);
        RouteDto routeDto = routeCrudService.fetchRouteDtoById(transportDto.getRouteId());
        StationDto stationDto = stationCrudService.fetchStationDtoById(stationId);

        ArrivalTimeDto arrivalTimeDto = ArrivalTimeDto.builder()
                .transport(transportDto)
                .route(routeDto)
                .selectedStation(stationDto)
                .distanceMeasurementType(MeasurementType.KM)
                .build();

        CalculatedArrivalTimeDto calculatedArrivalTimeDto = arrivalTimeGateway.calculateArrivalTime(arrivalTimeDto);

        arrivalTimeDto.setExpectedArrivalTime(calculatedArrivalTimeDto.getExpectedArrivalTime().toString());
        arrivalTimeDto.setDistance(calculatedArrivalTimeDto.getDistance());
        arrivalTimeDto.setDistanceMeasurementType(
                MeasurementType.findByShortCode(calculatedArrivalTimeDto.getDistanceMeasurementTypeShortCode()));

        return arrivalTimeDto;
    }
}
