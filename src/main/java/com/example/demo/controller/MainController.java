package com.example.demo.controller;

import com.example.demo.dto.EventDTO;
import com.example.demo.dto.ReportDTO;
import com.example.demo.factories.EventFactory;
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

    //private final ReportRepository reportRepository;
    private final EventService eventService;
    private final EventFactory eventFactory;
    private final ReportService reportService;

    @GetMapping
    public String home()  {
        return "return xd";
    }

    @PostMapping(value = "/addEvent")
    public ResponseEntity<EventDTO> addEvent(@RequestBody EventDTO eventDTO) {
        System.out.println("1231");
        eventFactory.detectEvent(eventDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/addEvents")
    public ResponseEntity<List<EventDTO>> addEvents(@RequestBody List<EventDTO> listOfEventDTO) {
        while ( listOfEventDTO.iterator().hasNext()){
            EventDTO eventDTO = listOfEventDTO.iterator().next();
            eventFactory.detectEvent(eventDTO);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/numberOfContrafts")
    public ResponseEntity<Integer> numberOfContrafts() {
        Integer result = eventService.numberOfContrafts();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/egwp")
    public ResponseEntity<Integer> expectedGrossWrittenPremium() {
        Integer result = eventService.expectedGrossWrittenPremium();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/agwp")
    public ResponseEntity<Integer> actualGrossWrittenPremium() {
        Integer result = eventService.actualGrossWrittenPremium();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(value = "/generateReport")
    public ResponseEntity<ReportDTO> generateReport() throws IOException {
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
