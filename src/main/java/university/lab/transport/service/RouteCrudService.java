package university.lab.transport.service;

import university.lab.transport.dto.RouteDto;

import java.util.List;

public interface RouteCrudService {
    RouteDto saveRoteByDto(RouteDto routeDto);

    List<RouteDto> fetchAllRouteDto();

    RouteDto fetchRouteDtoById(Long routeId);

    RouteDto updateRouteByDto(RouteDto routeDto);

    void removeRouteById(Long routeId);
}
