package com.example.demo.service.boundary;

import com.example.demo.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    void save(Event event);

    void save(List<Event> events);

    int calculateEgwpInSpecifyMonth(int month, int agwp);

    int calculateAgwpInSpecifyMonth(int month);

    int countingActiveContractsInSpecifyMonth(int month);
}
