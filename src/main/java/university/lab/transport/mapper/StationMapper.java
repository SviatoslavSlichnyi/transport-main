package university.lab.transport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import university.lab.transport.config.SpringMapperConfig;
import university.lab.transport.dto.StationDto;
import university.lab.transport.entity.Route;
import university.lab.transport.entity.Station;
import university.lab.transport.service.RouteMapperHelperService;

import java.util.Set;

@Mapper(config = SpringMapperConfig.class)
public abstract class StationMapper {

    @Autowired
    private RouteMapperHelperService routeMapperHelperService;

    @Mapping(target = "routeIds", expression = "java(mapRoutes(station))")
    public abstract StationDto map(Station station);

    @Mapping(target = "routes", expression = "java(mapRouteIdsByDto(stationDto))")
    public abstract Station map(StationDto stationDto);


    protected Set<Long> mapRoutes(Station station) {
        return routeMapperHelperService.mapRoutes(station.getRoutes());
    }

    protected Set<Route> mapRouteIdsByDto(StationDto stationDto) {
        return routeMapperHelperService.mapRouteIds(stationDto.getRouteIds());
    }
}
