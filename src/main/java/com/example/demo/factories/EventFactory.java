package com.example.demo.factories;

import com.example.demo.dto.EventDTO;
import com.example.demo.mapper.EventMapper;
import com.example.demo.service.EventService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
//@AllArgsConstructor
//@Data
public class EventFactory {

    private static EventMapper eventMapper;
    private static EventService eventService;

    public final static String CONTRACT_CREATED_EVENT = "ContractCreatedEvent";
    public final static String CONTRACT_TERMINATED_EVENT = "ContractTerminatedEvent";
    public final static String PRICE_DECREASED_EVENT = "PriceDecreasedEvent";
    public final static String PRICE_INCREASED_EVENT = "PriceIncreasedEvent";

    public static void detectEvent(EventDTO eventDTO) {
        switch (eventDTO.getName()) {
            case CONTRACT_CREATED_EVENT:
                eventService.save(eventMapper.eventDTOToContractCreatedEvent(eventDTO));
            case CONTRACT_TERMINATED_EVENT:
                eventService.save(eventMapper.eventDTOToContractTerminatedEvent(eventDTO));
            case PRICE_DECREASED_EVENT:
                eventService.save(eventMapper.eventDTOToPriceDecreasedEvent(eventDTO));
            case PRICE_INCREASED_EVENT:
                eventService.save(eventMapper.eventDTOToPriceIncreasedEvent(eventDTO));
        }

    }

//    public static void setEventMapper(EventMapper eventMapper) {
//        EventFactory.eventMapper = eventMapper;
//    }
//
//    public static void setEventService(EventService eventService) {
//        EventFactory.eventService = eventService;
//    }
}
