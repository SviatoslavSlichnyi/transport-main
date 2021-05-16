package university.lab.transport.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.lab.transport.dto.PublicTransportDto;
import university.lab.transport.entity.PublicTransport;
import university.lab.transport.exception.BusinessOperationRuntimeError;
import university.lab.transport.mapper.PublicTransportMapper;
import university.lab.transport.repository.PublicTransportRepository;
import university.lab.transport.service.PublicTransportCrudService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class PublicTransportCrudServiceImpl implements PublicTransportCrudService {

    private final PublicTransportRepository transportRepository;
    private final PublicTransportMapper transportMapper;

    @Override
    public PublicTransportDto saveTransportByDto(PublicTransportDto transportDto) {
        validateSaveAction(transportDto);
        return saveOrUpdateTransportByDto(transportDto);
    }

    @Override
    public List<PublicTransportDto> fetchAllPublicTransportDtos() {
        return transportRepository.findAll().stream()
                .map(transportMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public PublicTransportDto fetchTransportDtoById(Long transportId) {
        PublicTransport transport = transportRepository.getOne(transportId);
        return transportMapper.map(transport);
    }

    @Override
    public PublicTransportDto updateTransportByDto(PublicTransportDto transportDto) {
        validateUpdateAction(transportDto);
        return saveOrUpdateTransportByDto(transportDto);
    }

    @Override
    @Transactional
    public void removeById(Long transportId) {
        transportRepository.deleteById(transportId);
    }

    private PublicTransportDto saveOrUpdateTransportByDto(PublicTransportDto transportDto) {
        PublicTransport transport = transportMapper.map(transportDto);
        PublicTransport savedTransport = transportRepository.save(transport);
        return transportMapper.map(savedTransport);
    }

    private void validateSaveAction(PublicTransportDto transportDto) {
        Long publicTransportId = transportDto.getPublicTransportId();
        if (publicTransportId == null) return;

        boolean exists = transportRepository.existsById(publicTransportId);
        if (exists) {
            throw new BusinessOperationRuntimeError("Transport already exists");
        }
    }

    private void validateUpdateAction(PublicTransportDto transportDto) {
        Long transportId = transportDto.getPublicTransportId();

        if (transportId == null)
            throw new BusinessOperationRuntimeError("Transport Id is not specified");

        if (!transportRepository.existsById(transportId))
            throw new EntityNotFoundException("Transport was not found by id: " + transportId);
    }
}
