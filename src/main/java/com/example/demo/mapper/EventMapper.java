package com.example.demo.mapper;

import com.example.demo.dto.EventDTO;
import com.example.demo.model.ContractCreatedEvent;
import com.example.demo.model.ContractTerminatedEvent;
import com.example.demo.model.PriceDecreasedEvent;
import com.example.demo.model.PriceIncreasedEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    ContractCreatedEvent eventDTOToContractCreatedEvent(EventDTO eventDTO);

    ContractTerminatedEvent eventDTOToContractTerminatedEvent(EventDTO eventDTO);

    PriceDecreasedEvent eventDTOToPriceDecreasedEvent(EventDTO eventDTO);

    PriceIncreasedEvent eventDTOToPriceIncreasedEvent(EventDTO eventDTO);

}
