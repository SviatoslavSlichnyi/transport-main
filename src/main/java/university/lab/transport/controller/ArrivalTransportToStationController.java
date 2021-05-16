package university.lab.transport.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import university.lab.transport.dto.ArrivalTimeDto;
import university.lab.transport.service.ArrivalService;

import javax.websocket.server.PathParam;

@RequiredArgsConstructor

@RestController
@RequestMapping("/arrival-transport-to-station")
public class ArrivalTransportToStationController {

    private final ArrivalService arrivalService;

    @GetMapping("/determine")
    public ArrivalTimeDto calculateTime(@PathParam("transportId") Long transportId,
                                        @PathParam("stationId") Long stationId) {
        return arrivalService.determineTransportArrivalTimeToStationByTransportId(stationId, transportId);
    }
}
