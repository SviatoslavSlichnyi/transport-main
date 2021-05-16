package university.lab.transport.mapper;

import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import university.lab.transport.config.SpringMapperConfig;
import university.lab.transport.dto.RouteDto;
import university.lab.transport.entity.PublicTransport;
import university.lab.transport.entity.Route;
import university.lab.transport.entity.Station;
import university.lab.transport.repository.PublicTransportRepository;
import university.lab.transport.repository.StationRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Setter

@Mapper(config = SpringMapperConfig.class)
public abstract class RouteMapper {

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private PublicTransportRepository transportRepository;

    @Mapping(target = "startStationId", source = "startStation.stationId")
    @Mapping(target = "endStationId", source = "endStation.stationId")
    @Mapping(target = "stationIds", expression = "java(mapStations(route.getStations()))")
    @Mapping(target = "transportIds", expression = "java(mapTransportIds(route.getTransports()))")
    public abstract RouteDto map(Route route);

    @Mapping(target = "startStation", expression = "java(fetchStation(routeDto.getStartStationId()))")
    @Mapping(target = "endStation", expression = "java(fetchStation(routeDto.getEndStationId()))")
    @Mapping(target = "stations", expression = "java(mapStationIds(routeDto.getStationIds()))")
    @Mapping(target = "transports", expression = "java(fetchTransports(routeDto.getTransportIds()))")
    public abstract Route map(RouteDto routeDto);

    protected List<Long> mapStations(List<Station> stations) {
        if (CollectionUtils.isEmpty(stations)) return null;

        return stations.stream()
                .map(Station::getStationId)
                .collect(Collectors.toList());
    }

    protected List<Station> mapStationIds(List<Long> stationIds) {
        if (CollectionUtils.isEmpty(stationIds)) return null;

        return stationIds.stream()
                .map(stationRepository::getOne)
                .collect(Collectors.toList());
    }

    protected Station fetchStation(Long stationId) {
        if (Objects.isNull(stationId)) return null;

        return stationRepository.getOne(stationId);
    }

    protected List<Long> mapTransportIds(List<PublicTransport> transports) {
        if (Objects.isNull(transports)) return null;

        return transports.stream()
                .map(PublicTransport::getPublicTransportId)
                .collect(Collectors.toList());
    }

    protected List<PublicTransport> fetchTransports(List<Long> transportIds) {
        if (Objects.isNull(transportIds)) return null;

        return transportIds.stream()
                .map(transportRepository::getOne)
                .collect(Collectors.toList());
    }
}
