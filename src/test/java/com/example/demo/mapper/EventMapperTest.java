package com.example.demo.mapper;

import com.example.demo.dto.EventDTO;
import com.example.demo.factories.boundary.EventFactory;
import com.example.demo.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class EventMapperTest {

    @Autowired
    EventFactory eventFactory;

    @Test
    void eventDTOToContractCreatedEvent() {

        //given
        ContractCreatedEvent expected = new ContractCreatedEvent();
        expected.setContractId(1L);
        expected.setPremium(200);
        expected.setStartDate(LocalDate.parse("2020-01-01"));
        expected.setName("ContractCreatedEvent");

        EventDTO event = new EventDTO();
        event.setContractId("1");
        event.setStartDate(LocalDate.parse("2020-01-01"));
        event.setPremium(200);
        event.setName("ContractCreatedEvent");

        //when
        ContractCreatedEvent result = (ContractCreatedEvent) eventFactory.detectEvent(event);

        //then
        Assertions.assertEquals(expected.getContractId(),result.getContractId());
        Assertions.assertEquals(expected.getPremium(),result.getPremium());
        Assertions.assertEquals(expected.getStartDate(),result.getStartDate());
        Assertions.assertEquals(expected.getName(),result.getName());
    }

    @Test
    void eventDTOToContractTerminatedEvent() {
        //given
        ContractTerminatedEvent expected = new ContractTerminatedEvent();
        expected.setContractId(1L);
        expected.setTerminationDate(LocalDate.parse("2020-01-01"));
        expected.setName("ContractTerminatedEvent");

        EventDTO event = new EventDTO();
        event.setContractId("1");
        event.setName("ContractTerminatedEvent");
        event.setTerminationDate(LocalDate.parse("2020-01-01"));

        //when
        ContractTerminatedEvent result = (ContractTerminatedEvent) eventFactory.detectEvent(event);

        //then
        Assertions.assertEquals(expected.getContractId(),result.getContractId());
        Assertions.assertEquals(expected.getTerminationDate(),result.getTerminationDate());
        Assertions.assertEquals(expected.getName(),result.getName());
    }

    @Test
    void eventDTOToPriceDecreasedEvent() {
        //given
        PriceDecreasedEvent expected = new PriceDecreasedEvent();
        expected.setContractId(1L);
        expected.setPremiumReduction(200);
        expected.setAtDate(LocalDate.parse("2020-01-01"));
        expected.setName("PriceDecreasedEvent");

        EventDTO event = new EventDTO();
        event.setContractId("1");
        event.setPremiumReduction(200);
        event.setAtDate(LocalDate.parse("2020-01-01"));
        event.setName("PriceDecreasedEvent");


        //when
        PriceDecreasedEvent result = (PriceDecreasedEvent) eventFactory.detectEvent(event);

        //then
        Assertions.assertEquals(result.getContractId(),expected.getContractId());
        Assertions.assertEquals(result.getPremiumReduction(),expected.getPremiumReduction());
        Assertions.assertEquals(result.getAtDate(),expected.getAtDate());
        Assertions.assertEquals(result.getName(),expected.getName());
    }

    @Test
    void eventDTOToPriceIncreasedEvent() {
        //given
        PriceIncreasedEvent expected = new PriceIncreasedEvent();
        expected.setContractId(1L);
        expected.setPremiumIncrease(200);
        expected.setAtDate(LocalDate.parse("2020-01-01"));
        expected.setName("PriceIncreasedEvent");

        EventDTO event = new EventDTO();
        event.setContractId("1");
        event.setPremiumIncrease(200);
        event.setAtDate(LocalDate.parse("2020-01-01"));
        event.setName("PriceIncreasedEvent");

        //when
        PriceIncreasedEvent result = (PriceIncreasedEvent) eventFactory.detectEvent(event);

        //then
        Assertions.assertEquals(expected.getContractId(),result.getContractId());
        Assertions.assertEquals(expected.getPremiumIncrease(),result.getPremiumIncrease());
        Assertions.assertEquals(expected.getAtDate(),result.getAtDate());
        Assertions.assertEquals(expected.getName(),result.getName());
    }
}