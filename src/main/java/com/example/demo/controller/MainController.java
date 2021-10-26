package com.example.demo.controller;

import com.example.demo.dto.EventDTO;
import com.example.demo.dto.Report;
import com.example.demo.factories.boundary.EventFactory;
import com.example.demo.service.boundary.EventService;
import com.example.demo.service.boundary.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<EventDTO>> addEvents(@RequestBody List<EventDTO> eventsDTO) {
        eventService.save(eventsDTO.stream().map(eventFactory::detectEvent).collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/generateReport")
    public ResponseEntity<Report> generateReport() {
        Report response = reportService.generateReport();
        return ResponseEntity.ok().body(response);
    }
}
