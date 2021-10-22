package com.example.demo.service;

import com.example.demo.dto.EventDTO;
import com.example.demo.factories.EventFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {

    @Autowired
    EventService eventService;
    @Autowired
    EventFactory eventFactory;

    @Test
    void generateReport() {

    }

    @Test
    void numberOfContrafts() {
        //given
        int expected = 1;

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setStartDate(LocalDate.ofEpochDay(2020-01-01));
        event1DTO.setPremium(200);
        event1DTO.setName("ContractCreatedEvent");
        eventFactory.detectEvent(event1DTO);

        EventDTO event2DTO = new EventDTO();
        event2DTO.setContractId("2");
        event2DTO.setStartDate(LocalDate.ofEpochDay(2020-02-01));
        event2DTO.setPremium(300);
        event2DTO.setName("ContractCreatedEvent");
        eventFactory.detectEvent(event2DTO);

        EventDTO event3DTO = new EventDTO();
        event3DTO.setContractId("1");
        event3DTO.setStartDate(LocalDate.ofEpochDay(2020-03-31));
        event3DTO.setName("ContractTerminatedEvent");
        eventFactory.detectEvent(event3DTO);

        //when
        int result = eventService.numberOfContrafts();

        //then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void expectedGrossWrittenPremium() {
        //given
        int expected = 1200;

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setStartDate(LocalDate.ofEpochDay(2020-01-01));
        event1DTO.setPremium(200);
        event1DTO.setName("ContractCreatedEvent");
        eventFactory.detectEvent(event1DTO);

        EventDTO event2DTO = new EventDTO();
        event2DTO.setContractId("2");
        event2DTO.setStartDate(LocalDate.ofEpochDay(2020-02-01));
        event2DTO.setPremium(300);
        event2DTO.setName("ContractCreatedEvent");
        eventFactory.detectEvent(event2DTO);

        EventDTO event3DTO = new EventDTO();
        event3DTO.setContractId("1");
        event3DTO.setStartDate(LocalDate.ofEpochDay(2020-03-31));
        event3DTO.setName("ContractTerminatedEvent");
        eventFactory.detectEvent(event3DTO);

        EventDTO event4DTO = new EventDTO();
        event4DTO.setContractId("2");
        event4DTO.setStartDate(LocalDate.ofEpochDay(2020-03-31));
        event4DTO.setName("ContractTerminatedEvent");
        eventFactory.detectEvent(event4DTO);

        //when
        int result = eventService.expectedGrossWrittenPremium();

        //then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void actualGrossWrittenPremium() {
        //given
        int expected = 1200;

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setStartDate(LocalDate.ofEpochDay(2020-01-01));
        event1DTO.setPremium(200);
        event1DTO.setName("ContractCreatedEvent");
        eventFactory.detectEvent(event1DTO);

        EventDTO event2DTO = new EventDTO();
        event2DTO.setContractId("2");
        event2DTO.setStartDate(LocalDate.ofEpochDay(2020-02-31));
        event2DTO.setPremium(300);
        event2DTO.setName("ContractCreatedEvent");
        eventFactory.detectEvent(event2DTO);

        EventDTO event3DTO = new EventDTO();
        event3DTO.setContractId("1");
        event3DTO.setStartDate(LocalDate.ofEpochDay(2020-03-31));
        event3DTO.setName("ContractTerminatedEvent");
        eventFactory.detectEvent(event3DTO);

        //when
        int result = eventService.actualGrossWrittenPremium();

        //then
        Assertions.assertEquals(expected,result);
    }
}