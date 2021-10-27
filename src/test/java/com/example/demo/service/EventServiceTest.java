package com.example.demo.service;

import com.example.demo.dto.EventDTO;
import com.example.demo.factories.boundary.EventFactory;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.internal.EventServiceImpl;
import com.example.demo.service.internal.ReportServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
class EventServiceTest {

    @Autowired
    EventServiceImpl eventService;
    @Autowired
    EventFactory eventFactory;
    @Autowired
    ReportServiceImpl reportService;
    @Autowired
    EventRepository eventRepository;

    @Test
    void generateReport() {
        //given
        int[][] expected = new int[3][12];

        expected[0][0] = 1;
        expected[0][1] = 2;
        expected[0][2] = 2;
        expected[0][3] = 2;
        expected[0][4] = 2;
        expected[0][5] = 2;
        expected[0][6] = 2;
        expected[0][7] = 1;
        expected[0][8] = 1;
        expected[0][9] = 1;
        expected[0][10] = 1;
        expected[0][11] = 0;

        expected[1][0] = 100;
        expected[1][1] = 300;
        expected[1][2] = 600;
        expected[1][3] = 900;
        expected[1][4] = 1100;
        expected[1][5] = 1300;
        expected[1][6] = 1500;
        expected[1][7] = 1600;
        expected[1][8] = 1700;
        expected[1][9] = 1800;
        expected[1][10] = 1900;
        expected[1][11] = 1900;

        expected[2][0] = 1200;
        expected[2][1] = 2300;
        expected[2][2] = 3300;
        expected[2][3] = 3300;
        expected[2][4] = 2500;
        expected[2][5] = 2500;
        expected[2][6] = 2500;
        expected[2][7] = 2000;
        expected[2][8] = 2000;
        expected[2][9] = 2000;
        expected[2][10] = 2000;
        expected[2][11] = 1900;

        eventRepository.deleteAll();

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setStartDate(LocalDate.parse("2020-01-01"));
        event1DTO.setPremium(100);
        event1DTO.setName("ContractCreatedEvent");
        eventRepository.save(eventFactory.detectEvent(event1DTO));

        EventDTO event2DTO = new EventDTO();
        event2DTO.setContractId("2");
        event2DTO.setStartDate(LocalDate.parse("2020-02-01"));
        event2DTO.setPremium(100);
        event2DTO.setName("ContractCreatedEvent");
        eventRepository.save(eventFactory.detectEvent(event2DTO));

        EventDTO event3DTO = new EventDTO();
        event3DTO.setContractId("1");
        event3DTO.setTerminationDate(LocalDate.parse("2020-07-30"));
        event3DTO.setName("ContractTerminatedEvent");
        eventRepository.save(eventFactory.detectEvent(event3DTO));

        EventDTO event4DTO = new EventDTO();
        event4DTO.setContractId("2");
        event4DTO.setTerminationDate(LocalDate.parse("2020-11-30"));
        event4DTO.setName("ContractTerminatedEvent");
        eventRepository.save(eventFactory.detectEvent(event4DTO));

        EventDTO event5DTO = new EventDTO();
        event5DTO.setContractId("1");
        event5DTO.setAtDate(LocalDate.parse("2020-03-01"));
        event5DTO.setName("PriceIncreasedEvent");
        event5DTO.setPremiumIncrease(100);
        eventRepository.save(eventFactory.detectEvent(event5DTO));

        EventDTO event6DTO = new EventDTO();
        event6DTO.setContractId("1");
        event6DTO.setAtDate(LocalDate.parse("2020-05-30"));
        event6DTO.setName("PriceDecreasedEvent");
        event6DTO.setPremiumReduction(100);
        eventRepository.save(eventFactory.detectEvent(event6DTO));

        //when
        Integer[][] result = reportService.getTableOfAllData();

        //then
        IntStream.range(0, 12).forEachOrdered(i -> {
            Assertions.assertEquals(expected[0][i], result[0][i]);
            Assertions.assertEquals(expected[1][i], result[1][i]);
            Assertions.assertEquals(expected[2][i], result[2][i]);
        });
    }

    @Test
    void correctNumberOfContracts() {
        //given
        Integer expected = 1;

        eventRepository.deleteAll();

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setStartDate(LocalDate.parse("2020-01-01"));
        event1DTO.setPremium(200);
        event1DTO.setName("ContractCreatedEvent");
        eventRepository.save(eventFactory.detectEvent(event1DTO));

        EventDTO event2DTO = new EventDTO();
        event2DTO.setContractId("2");
        event2DTO.setStartDate(LocalDate.parse("2020-01-01"));
        event2DTO.setPremium(300);
        event2DTO.setName("ContractCreatedEvent");
        eventRepository.save(eventFactory.detectEvent(event2DTO));

        EventDTO event3DTO = new EventDTO();
        event3DTO.setContractId("1");
        event3DTO.setTerminationDate(LocalDate.parse("2020-03-01"));
        event3DTO.setName("ContractTerminatedEvent");
        eventRepository.save(eventFactory.detectEvent(event3DTO));

        EventDTO event4DTO = new EventDTO();
        event4DTO.setContractId("2");
        event4DTO.setTerminationDate(LocalDate.parse("2020-06-01"));
        event4DTO.setName("ContractTerminatedEvent");
        eventRepository.save(eventFactory.detectEvent(event4DTO));

        //when
        Integer result = eventService.countingActiveContractsInSpecifyMonth(4);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void Exception0NumberOfContracts() {
        //given
        Integer expected = 0;

        eventRepository.deleteAll();

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setStartDate(LocalDate.parse("2020-01-01"));
        event1DTO.setPremium(200);
        event1DTO.setName("ContractCreatedEvent");
        eventRepository.save(eventFactory.detectEvent(event1DTO));

        EventDTO event2DTO = new EventDTO();
        event2DTO.setContractId("2");
        event2DTO.setTerminationDate(LocalDate.parse("2020-01-01"));
        event2DTO.setName("ContractTerminatedEvent");
        eventRepository.save(eventFactory.detectEvent(event2DTO));

        EventDTO event3DTO = new EventDTO();
        event3DTO.setContractId("1");
        event3DTO.setTerminationDate(LocalDate.parse("2020-01-01"));
        event3DTO.setName("ContractTerminatedEvent");
        eventRepository.save(eventFactory.detectEvent(event3DTO));

        //when
        Integer result = eventService.countingActiveContractsInSpecifyMonth(12);
        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void correctEGWP() {
        //given
        int expected = 6000;

        eventRepository.deleteAll();

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setStartDate(LocalDate.parse("2020-01-01"));
        event1DTO.setPremium(200);
        event1DTO.setName("ContractCreatedEvent");
        eventRepository.save(eventFactory.detectEvent(event1DTO));

        EventDTO event2DTO = new EventDTO();
        event2DTO.setContractId("2");
        event2DTO.setStartDate(LocalDate.parse("2020-01-01"));
        event2DTO.setPremium(300);
        event2DTO.setName("ContractCreatedEvent");
        eventRepository.save(eventFactory.detectEvent(event2DTO));

        EventDTO event3DTO = new EventDTO();
        event3DTO.setContractId("1");
        event3DTO.setTerminationDate(LocalDate.parse("2020-01-31"));
        event3DTO.setName("ContractTerminatedEvent");
        eventRepository.save(eventFactory.detectEvent(event3DTO));

        EventDTO event4DTO = new EventDTO();
        event4DTO.setContractId("2");
        event4DTO.setTerminationDate(LocalDate.parse("2020-01-31"));
        event4DTO.setName("ContractTerminatedEvent");
        eventRepository.save(eventFactory.detectEvent(event4DTO));

        //when
        int result = eventService.calculateEgwpInSpecifyMonth(1, 0);
        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void correctAGWP() {
        //given
        int expected = 500;

        eventRepository.deleteAll();

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setStartDate(LocalDate.parse("2020-01-01"));
        event1DTO.setPremium(200);
        event1DTO.setName("ContractCreatedEvent");
        eventRepository.save(eventFactory.detectEvent(event1DTO));

        EventDTO event2DTO = new EventDTO();
        event2DTO.setContractId("2");
        event2DTO.setStartDate(LocalDate.parse("2020-01-01"));
        event2DTO.setPremium(300);
        event2DTO.setName("ContractCreatedEvent");
        eventRepository.save(eventFactory.detectEvent(event2DTO));

        EventDTO event3DTO = new EventDTO();
        event3DTO.setContractId("1");
        event3DTO.setTerminationDate(LocalDate.parse("2020-01-31"));
        event3DTO.setName("ContractTerminatedEvent");
        eventRepository.save(eventFactory.detectEvent(event3DTO));

//        EventDTO event4DTO = new EventDTO();
//        event4DTO.setContractId("2");
//        event4DTO.setTerminationDate(LocalDate.parse("2020-01-31"));
//        event4DTO.setName("ContractTerminatedEvent");
//        eventRepository.save(eventFactory.detectEvent(event4DTO));

        //when
        int result = eventService.calculateAgwpInSpecifyMonth(1);
        //then
        Assertions.assertEquals(expected, result);
    }
}