package university.lab.transport.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import university.lab.transport.dto.TransportLocationDto;
import university.lab.transport.service.TransportLocationByGpsTrackerService;

import java.time.Instant;
import java.util.Random;

@RequiredArgsConstructor

@Service
public class TransportLocationByGpsTrackerServiceImpl implements TransportLocationByGpsTrackerService {
    @Override
    public TransportLocationDto getLocationByTransportId(Long transportId) {

        return TransportLocationDto.builder()
                .x(random(10, 100))
                .y(random(10, 100))
                .lastSignalTime(Instant.now().toString())
                .build();
    }

    private static Double random(int start, int end) {
        if (start > end || end - start <= 0) {
            throw new IllegalArgumentException();
        }

        start *= 100;
        end *= 100;

        return (double) (new Random().nextInt(end - start) + start) / 100;
    }
}
