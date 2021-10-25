package com.example.demo.service;

import com.example.demo.dto.ReportDTO;
import com.example.demo.model.ContractCreatedEvent;
import com.example.demo.model.ContractTerminatedEvent;
import com.example.demo.model.PriceDecreasedEvent;
import com.example.demo.model.PriceIncreasedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EventService {

    int numberOfContrafts();

    int expectedGrossWrittenPremium();

    int actualGrossWrittenPremium();

    int priceDecreased(ContractCreatedEvent crContract, int month);

    int priceIncreased(ContractCreatedEvent crContract, int month);

    boolean isWorking(ContractCreatedEvent crContract, int month);

    int egwpInMonth(int month,int agwp);

    int agwpInMonth(int month);

    int numberOfContraftsInMonth(int month);

    void save(ContractCreatedEvent contractCreatedEvent);

    void save(ContractTerminatedEvent contractTerminatedEvent);

    void save(PriceDecreasedEvent priceDecreasedEvent);

    void save(PriceIncreasedEvent priceIncreasedEvent);

    List<ContractCreatedEvent> findAllContractCreatedEvent();

    List<ContractTerminatedEvent> findAllContractTerminatedEvent();

    List<PriceDecreasedEvent> findAllPriceDecreasedEvent();

    List<PriceIncreasedEvent> findAllPriceIncreasedEvent();

    PriceIncreasedEvent getPriceIncreasedEventById(Long id);

    PriceDecreasedEvent getPriceDecreasedEventById(Long id);

    ContractCreatedEvent getContractCreatedEventById(Long id);

    LocalDate getDateOfContractTerminatedEventById(Long id);

}
