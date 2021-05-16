package university.lab.transport.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.lab.transport.dto.StationDto;
import university.lab.transport.entity.Station;
import university.lab.transport.exception.BusinessOperationRuntimeError;
import university.lab.transport.mapper.StationMapper;
import university.lab.transport.repository.StationRepository;
import university.lab.transport.service.StationCrudService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class StationCrudServiceImpl implements StationCrudService {

    private final StationRepository stationRepository;
    private final StationMapper stationMapper;

    @Override
    public StationDto saveStationByDto(StationDto stationDto) {
        validateSaveAction(stationDto);
        return saveOrUpdateRouteByDto(stationDto);
    }

    @Override
    public List<StationDto> fetchAllStationDto() {
        return stationRepository.findAll().stream()
                .map(stationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public StationDto fetchStationDtoById(Long stationId) {
        Station foundStation = stationRepository.getOne(stationId);
        return stationMapper.map(foundStation);
    }

    @Override
    public StationDto updateStationByDto(StationDto stationDto) {
        validateUpdateAction(stationDto);
        return saveOrUpdateRouteByDto(stationDto);
    }

    @Override
    @Transactional
    public void removeStationById(Long stationId) {
        stationRepository.deleteById(stationId);
    }

    private StationDto saveOrUpdateRouteByDto(StationDto stationDto) {
        Station station = stationMapper.map(stationDto);
        Station savedStation = stationRepository.save(station);
        return stationMapper.map(savedStation);
    }

    private void validateSaveAction(StationDto stationDto) {
        Long stationId = stationDto.getStationId();

        if (stationId == null) return;

        boolean exists = stationRepository.existsById(stationId);
        if (exists) {
            throw new BusinessOperationRuntimeError("Station already exists");
        }
    }

    private void validateUpdateAction(StationDto stationDto) {
        Long stationId = stationDto.getStationId();

        if (stationId == null)
            throw new BusinessOperationRuntimeError("Route Id is not specified");

        if (!stationRepository.existsById(stationId))
            throw new EntityNotFoundException("Route was not found by id: " + stationId);
    }
}
