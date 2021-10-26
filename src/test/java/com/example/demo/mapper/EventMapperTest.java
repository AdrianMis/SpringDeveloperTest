package com.example.demo.mapper;

import com.example.demo.dto.EventDTO;
import com.example.demo.factories.EventFactory;
import com.example.demo.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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
        expected.setStartDate(LocalDate.ofEpochDay(2020-01-01));

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setStartDate(LocalDate.ofEpochDay(2020-01-01));
        event1DTO.setPremium(200);
        event1DTO.setName("ContractCreatedEvent");

        //when
        ContractCreatedEvent result = (ContractCreatedEvent) eventFactory.detectEvent(event1DTO);
        System.out.println(expected);
        System.out.println(result);
        //then
        Assertions.assertEquals(expected,result);

    }

    @Test
    void eventDTOToContractTerminatedEvent() {
        //given
        ContractTerminatedEvent expected = new ContractTerminatedEvent();
        expected.setContractId(1L);
        expected.setTerminationDate(LocalDate.ofEpochDay(2020-01-01));
        expected.setName("ContractTerminatedEvent");

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setName("ContractTerminatedEvent");
        event1DTO.setTerminationDate(LocalDate.ofEpochDay(2020-01-01));

        //when
        ContractTerminatedEvent result = (ContractTerminatedEvent) eventFactory.detectEvent(event1DTO);
        System.out.println(expected);
        System.out.println(result);
        //then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void eventDTOToPriceDecreasedEvent() {
        //given
        PriceDecreasedEvent expected = new PriceDecreasedEvent();
        expected.setContractId(1L);
        expected.setPremiumReduction(200);
        expected.setAtDate(LocalDate.ofEpochDay(2020-01-01));
        expected.setName("PriceDecreasedEvent");

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setName("PriceDecreasedEvent");

        //when
        PriceDecreasedEvent result = (PriceDecreasedEvent) eventFactory.detectEvent(event1DTO);
        System.out.println(expected);
        System.out.println(result);

        //then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void eventDTOToPriceIncreasedEvent() {
        //given
        PriceIncreasedEvent expected = new PriceIncreasedEvent();
        expected.setContractId(1L);
        expected.setPremiumIncrease(200);
        expected.setAtDate(LocalDate.ofEpochDay(2020-01-01));
        expected.setName("PriceIncreasedEvent");

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setName("PriceIncreasedEvent");

        //when
        PriceIncreasedEvent result = (PriceIncreasedEvent) eventFactory.detectEvent(event1DTO);
        System.out.println(expected);
        System.out.println(result);
        //then
        Assertions.assertEquals(expected,result);
    }
}