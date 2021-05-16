package university.lab.transport.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import university.lab.transport.entity.Route;
import university.lab.transport.repository.RouteRepository;
import university.lab.transport.service.RouteMapperHelperService;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class RouteMapperHelperServiceImpl implements RouteMapperHelperService {

    private final RouteRepository routeRepository;

    public Set<Long> mapRoutes(Set<Route> routes) {
        if (CollectionUtils.isEmpty(routes)) return null;

        return routes.stream()
                .map(Route::getRouteId)
                .collect(Collectors.toSet());
    }

    public Set<Route> mapRouteIds(Set<Long> routeIds) {
        if (CollectionUtils.isEmpty(routeIds)) return null;

        return routeIds.stream()
                .map(routeRepository::getOne)
                .collect(Collectors.toSet());
    }

    @Override
    public Route fetchRouteById(Long routeId) {
        if (Objects.isNull(routeId)) return null;

        return routeRepository.getOne(routeId);
    }
}
