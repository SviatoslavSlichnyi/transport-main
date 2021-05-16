package university.lab.transport.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import university.lab.transport.dto.StationDto;
import university.lab.transport.service.StationCrudService;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/station")
public class StationController {

    private final StationCrudService stationCrudService;

    @GetMapping("/get/{stationId}")
    public StationDto getStationById(@PathVariable Long stationId) {
        return stationCrudService.fetchStationDtoById(stationId);
    }

    @GetMapping("/get/all")
    public List<StationDto> getAllStationDto() {
        return stationCrudService.fetchAllStationDto();
    }

    @PostMapping("/save")
    public StationDto saveStationByDto(@RequestBody StationDto stationDto) {
        return stationCrudService.saveStationByDto(stationDto);
    }

    @PutMapping("/update")
    public StationDto updateStationByDto(@RequestBody StationDto stationDto) {
        return stationCrudService.updateStationByDto(stationDto);
    }

    @DeleteMapping("/remove/{stationId}")
    public void removeStationById(@PathVariable Long stationId) {
        stationCrudService.removeStationById(stationId);
    }
}
