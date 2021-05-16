package university.lab.transport.service.gateway.imlp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import university.lab.arrival.dto.CalculatedArrivalTimeDto;
import university.lab.transport.dto.ArrivalTimeDto;
import university.lab.transport.exception.BusinessOperationRuntimeError;
import university.lab.transport.service.gateway.ArrivalTimeGateway;

import static university.lab.transport.entity.type.Constants.ArrivalGateway;

@RequiredArgsConstructor

@Service
public class ArrivalTimeGatewayImpl implements ArrivalTimeGateway {
    @Override
    public CalculatedArrivalTimeDto calculateArrivalTime(ArrivalTimeDto arrivalTimeDto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ArrivalTimeDto> request = new HttpEntity<>(arrivalTimeDto);
        String url = ArrivalGateway.ARRIVAL_DOMAIN + ArrivalGateway.ArrivalTime.CALCULATE.getEndPoint();

        ResponseEntity<CalculatedArrivalTimeDto> responseEntity =
                restTemplate.exchange(url, HttpMethod.POST, request, CalculatedArrivalTimeDto.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new BusinessOperationRuntimeError("Check your Request again, please!");
        }
    }
}
