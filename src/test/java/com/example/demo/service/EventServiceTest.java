package com.example.demo.service;

import com.example.demo.controller.MainController;
import com.example.demo.dto.EventDTO;
import com.example.demo.factories.EventFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceTest {

    @Autowired
    EventServiceImpl eventService;
    @Autowired
    EventFactory eventFactory;
    @Autowired
    ReportServiceImpl reportService;


    @Test
    void generateReport() {
        //given
        int[][] expected = new int[3][12];

        expected[0][0] = 1;
        expected[0][1] = 2;
        expected[0][2] = 2;
        expected[0][3] = 1;
        expected[0][4] = 1;
        expected[0][5] = 1;
        expected[0][6] = 1;
        expected[0][7] = 1;
        expected[0][8] = 1;
        expected[0][9] = 1;
        expected[0][10] = 1;
        expected[0][11] = 1;

        expected[1][0] = 200;
        expected[1][1] = 700;
        expected[1][2] = 1200;
        expected[1][3] = 1500;
        expected[1][4] = 1800;
        expected[1][5] = 2100;
        expected[1][6] = 2400;
        expected[1][7] = 2700;
        expected[1][8] = 3000;
        expected[1][9] = 3300;
        expected[1][10] = 3600;
        expected[1][11] = 3900;

        expected[2][0] = 2400;
        expected[2][1] = 5700;
        expected[2][2] = 5700;
        expected[2][3] = 3900;
        expected[2][4] = 3900;
        expected[2][5] = 3900;
        expected[2][6] = 3900;
        expected[2][7] = 3900;
        expected[2][8] = 3900;
        expected[2][9] = 3900;
        expected[2][10] = 3900;
        expected[2][11] = 3900;

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
        //int[][] result = reportService.getTableOfAllData();

        //then
        //Assertions.assertEquals(expected,result);
    }

    @Test
    void correctNumberOfContracts() {
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
        Long result = eventService.numberOfContracts();

        //then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void Exception0NumberOfContracts() {
        //given
        int expected = 0;

        EventDTO event1DTO = new EventDTO();
        event1DTO.setContractId("1");
        event1DTO.setStartDate(LocalDate.ofEpochDay(2020-01-01));
        event1DTO.setPremium(200);
        event1DTO.setName("ContractCreatedEvent");
        eventFactory.detectEvent(event1DTO);

        EventDTO event2DTO = new EventDTO();
        event2DTO.setContractId("2");
        event2DTO.setStartDate(LocalDate.ofEpochDay(2020-02-01));
        event2DTO.setName("ContractTerminatedEvent");
        eventFactory.detectEvent(event2DTO);

        EventDTO event3DTO = new EventDTO();
        event3DTO.setContractId("1");
        event3DTO.setStartDate(LocalDate.ofEpochDay(2020-03-31));
        event3DTO.setName("ContractTerminatedEvent");
        eventFactory.detectEvent(event3DTO);

        //when
        Long result = eventService.numberOfContracts();

        //then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void correctEGWP() {
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
    void correctAGWP() {
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