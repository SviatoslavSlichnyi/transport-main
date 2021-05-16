package university.lab.transport.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import university.lab.transport.dto.RouteDto;
import university.lab.transport.service.RouteCrudService;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/route")
public class RouteController {

    private final RouteCrudService routeCrudService;

    @GetMapping("/get/{routeId}")
    public RouteDto fetchRoute(@PathVariable Long routeId) {
        return routeCrudService.fetchRouteDtoById(routeId);
    }

    @GetMapping("/get/all")
    public List<RouteDto> fetchAllRouteDto() {
        return routeCrudService.fetchAllRouteDto();
    }

    @PostMapping("/save")
    public RouteDto saveRoute(@RequestBody RouteDto routeDto) {
        return routeCrudService.saveRoteByDto(routeDto);
    }

    @PutMapping("/update")
    public RouteDto updateRoute(@RequestBody RouteDto routeDto) {
        return routeCrudService.updateRouteByDto(routeDto);
    }

    @DeleteMapping("/remove/{routeId}")
    public void removeRouteById(@PathVariable Long routeId) {
        routeCrudService.removeRouteById(routeId);
    }
}
