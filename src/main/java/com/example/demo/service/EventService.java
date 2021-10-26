package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EventService {

    Long numberOfContracts();

    int expectedGrossWrittenPremium();

    int actualGrossWrittenPremium();

    int totalDecreasedPremiumInMonth(ContractCreatedEvent crContract, int month);

    int totalIncreasedPremiumInMonth(ContractCreatedEvent crContract, int month);

    boolean isWorking(ContractCreatedEvent crContract, int month);

    int egwpInMonth(int month,int agwp);

    int agwpInMonth(int month);

    int numberOfContractsInMonth(int month);

    void save(Event event);

    List<Event> findAllContractCreatedEvent();

    List<Event> findAllContractTerminatedEvent();

    Long countContractCreatedEvent();

    Long countContractTerminatedEvent();

    List<Event> findAllTerminatedContractByContractIdAndName(Long id);

    List<Event> findAllPriceDecreasedByContractIdAndName(Long id);

    List<Event> findAllPriceIncreasedByContractIdAndName(Long id);

}
