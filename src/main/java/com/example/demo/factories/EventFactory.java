package com.example.demo.factories;

import com.example.demo.dto.EventDTO;
import com.example.demo.mapper.EventMapper;
import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventFactory {

    private final EventMapper eventMapper;
    private final EventService eventService;

    public final static String CONTRACT_CREATED_EVENT = "ContractCreatedEvent";
    public final static String CONTRACT_TERMINATED_EVENT = "ContractTerminatedEvent";
    public final static String PRICE_DECREASED_EVENT = "PriceDecreasedEvent";
    public final static String PRICE_INCREASED_EVENT = "PriceIncreasedEvent";

    public Event detectEvent(EventDTO eventDTO) {
        switch (eventDTO.getName()) {
            case CONTRACT_CREATED_EVENT:
                return eventMapper.eventDTOToContractCreatedEvent(eventDTO);
            case CONTRACT_TERMINATED_EVENT:
                return eventMapper.eventDTOToContractTerminatedEvent(eventDTO);
            case PRICE_DECREASED_EVENT:
                return eventMapper.eventDTOToPriceDecreasedEvent(eventDTO);
            case PRICE_INCREASED_EVENT:
                return eventMapper.eventDTOToPriceIncreasedEvent(eventDTO);
        }
        return null;

    }

}
