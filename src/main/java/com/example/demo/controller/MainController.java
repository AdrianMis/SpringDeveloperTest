package com.example.demo.controller;

import com.example.demo.dto.EventDTO;
import com.example.demo.dto.ReportDTO;
import com.example.demo.factories.EventFactory;
import com.example.demo.model.ContractCreatedEvent;
import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import com.example.demo.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {

    private final EventService eventService;
    private final EventFactory eventFactory;
    private final ReportService reportService;

    @PostMapping(value = "/addEvent")
    public ResponseEntity<EventDTO> addEvent(@RequestBody EventDTO eventDTO) {
        eventService.save(eventFactory.detectEvent(eventDTO));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/addEvents")
    public ResponseEntity<List<EventDTO>> addEvents(@RequestBody List<EventDTO> listOfEventDTO) {
        for(EventDTO event : listOfEventDTO) {eventService.save(eventFactory.detectEvent(event));}

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @GetMapping(value = "/numberOfContracts")
//    public ResponseEntity<Long> numberOfContracts() {
//        Long result = eventService.numberOfContracts();
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
//
//    @GetMapping(value = "/egwp")
//    public ResponseEntity<Integer> expectedGrossWrittenPremium() {
//        Integer result = eventService.expectedGrossWrittenPremium();
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
//
//    @GetMapping(value = "/agwp")
//    public ResponseEntity<Integer> actualGrossWrittenPremium() {
//        Integer result = eventService.actualGrossWrittenPremium();
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

    @PostMapping(value = "/generateReport")
    public ResponseEntity<ReportDTO> generateReport() {
        ReportDTO reportDTO = reportService.generateReport();
        return ResponseEntity.ok().body(reportDTO);
    }

//    @PostMapping(value = "/generateReport")
//    public ResponseEntity<Resource> generateReport() throws IOException {
//        Resource resource = eventService.generateCSVReport();
//        return ResponseEntity.ok().body(resource);
//    }

//    @GetMapping(value = "/getReport/{id}")
//    public ResponseEntity<Resource> getReport(@PathVariable(name = "id") Long id) {
//
//        Optional<Report> optionalReport = reportRepository.findById(id);
//        if (!optionalReport.isPresent()) {
//            return ResponseEntity.badRequest().build();
//        }
//        Report report = optionalReport.get();
//        Resource resource = new ByteArrayResource(report.getContent());
//        return ResponseEntity.ok().body(resource);
//    }

}
