package university.lab.transport.mapper;

import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import university.lab.transport.config.SpringMapperConfig;
import university.lab.transport.dto.PublicTransportDto;
import university.lab.transport.entity.PublicTransport;
import university.lab.transport.entity.Route;
import university.lab.transport.service.RouteMapperHelperService;

import java.util.Objects;

@Setter

@Mapper(config = SpringMapperConfig.class)
public abstract class PublicTransportMapper {

    @Autowired
    private RouteMapperHelperService routeMapperHelperService;

    @Mapping(target = "location", ignore = true)
    @Mapping(target = "routeId", source = "route.routeId")
    public abstract PublicTransportDto map(PublicTransport publicTransport);

    @Mapping(target = "route", expression = "java(fetchRouteById(publicTransportDto.getRouteId()))")
    public abstract PublicTransport map(PublicTransportDto publicTransportDto);

    protected Route fetchRouteById(Long routeId) {
        if (Objects.isNull(routeId)) return null;

        return routeMapperHelperService.fetchRouteById(routeId);
    }

}
