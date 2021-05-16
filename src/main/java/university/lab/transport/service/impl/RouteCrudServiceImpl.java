package university.lab.transport.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.lab.transport.dto.RouteDto;
import university.lab.transport.entity.Route;
import university.lab.transport.exception.BusinessOperationRuntimeError;
import university.lab.transport.mapper.RouteMapper;
import university.lab.transport.repository.PublicTransportRepository;
import university.lab.transport.repository.RouteRepository;
import university.lab.transport.service.RouteCrudService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class RouteCrudServiceImpl implements RouteCrudService {

    private final RouteRepository routeRepository;
    private final PublicTransportRepository transportRepository;
    private final RouteMapper routeMapper;

    @Override
    public RouteDto saveRoteByDto(RouteDto routeDto) {
        validateSaveAction(routeDto);
        return saveOfUpdateRouteByDto(routeDto);
    }

    @Override
    public List<RouteDto> fetchAllRouteDto() {
        return routeRepository.findAll().stream()
                .map(routeMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public RouteDto fetchRouteDtoById(Long routeId) {
        Route foundRoute = routeRepository.getOne(routeId);
        return routeMapper.map(foundRoute);
    }

    @Override
    public RouteDto updateRouteByDto(RouteDto routeDto) {
       validateUpdateAction(routeDto);
       return saveOfUpdateRouteByDto(routeDto);
    }

    @Override
    @Transactional
    public void removeRouteById(Long routeId) {
        routeRepository.deleteById(routeId);
    }

    private RouteDto saveOfUpdateRouteByDto(RouteDto routeDto) {
        Route route = routeMapper.map(routeDto);
        Route savedRoute = routeRepository.save(route);
        route.getTransports().forEach(publicTransport -> {
            publicTransport.setRoute(route);
            transportRepository.save(publicTransport);
        });
        return routeMapper.map(savedRoute);
    }

    private void validateSaveAction(RouteDto routeDto) {
        Long routeId = routeDto.getRouteId();
        if (routeId == null) return;

        boolean exists = routeRepository.existsById(routeId);
        if (exists) {
            throw new BusinessOperationRuntimeError("Route already exists");
        }
    }

    private void validateUpdateAction(RouteDto routeDto) {
        Long routeId = routeDto.getRouteId();

        if (routeId == null)
            throw new BusinessOperationRuntimeError("Route Id is not specified");

        if (!routeRepository.existsById(routeId))
            throw new EntityNotFoundException("Route was not found by id: " + routeId);
    }
}
