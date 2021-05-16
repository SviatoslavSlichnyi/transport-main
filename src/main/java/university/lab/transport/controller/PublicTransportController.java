package university.lab.transport.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import university.lab.transport.dto.PublicTransportDto;
import university.lab.transport.service.PublicTransportCrudService;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/public-transport")
public class PublicTransportController {

     private final PublicTransportCrudService transportCrudService;

     @GetMapping("/get/{transportId}")
     public PublicTransportDto fetchTransportById(@PathVariable Long transportId) {
          return transportCrudService.fetchTransportDtoById(transportId);
     }

     @GetMapping("/get/all")
     public List<PublicTransportDto> fetchAllTransport() {
          return transportCrudService.fetchAllPublicTransportDtos();
     }

     @PostMapping("/save")
     public PublicTransportDto saveTransport(@RequestBody PublicTransportDto transportDto) {
          return transportCrudService.saveTransportByDto(transportDto);
     }

     @PutMapping("/update")
     public PublicTransportDto updateTransport(@RequestBody PublicTransportDto transportDto) {
          return transportCrudService.updateTransportByDto(transportDto);
     }

     @DeleteMapping("/remove/{transportId}")
     public void removeTransport(@PathVariable Long transportId) {
          transportCrudService.removeById(transportId);
     }
}
