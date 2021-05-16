package university.lab.transport.service;

import university.lab.transport.dto.PublicTransportDto;

import java.util.List;

public interface PublicTransportCrudService {
    PublicTransportDto saveTransportByDto(PublicTransportDto transportDto);

    List<PublicTransportDto> fetchAllPublicTransportDtos();

    PublicTransportDto fetchTransportDtoById(Long transportId);

    PublicTransportDto updateTransportByDto(PublicTransportDto transportDto);

    void removeById(Long transportId);
}
