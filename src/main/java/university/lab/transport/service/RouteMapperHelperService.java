package university.lab.transport.service;

import university.lab.transport.entity.Route;

import java.util.Set;

public interface RouteMapperHelperService {

    Set<Long> mapRoutes(Set<Route> routes);

    Set<Route> mapRouteIds(Set<Long> routeIds);

    Route fetchRouteById(Long routeId);
}
