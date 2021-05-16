package university.lab.transport.service;

import university.lab.transport.dto.StationDto;

import java.util.List;

public interface StationCrudService {
    StationDto saveStationByDto(StationDto stationDto);

    List<StationDto> fetchAllStationDto();

    StationDto fetchStationDtoById(Long stationId);

    StationDto updateStationByDto(StationDto stationDto);

    void removeStationById(Long stationId);
}
