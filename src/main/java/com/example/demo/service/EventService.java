package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EventService {

    void save(Event event);

    int calculateEgwpInSpecifyMonth(int month, int agwp);

    int calculateAgwpInSpecifyMonth(int month);

    int countingActiveContractsInSpecifyMonth(int month);
}
