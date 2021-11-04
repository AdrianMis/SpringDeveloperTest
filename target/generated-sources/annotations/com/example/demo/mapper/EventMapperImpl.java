package com.example.demo.mapper;

import com.example.demo.dto.EventDTO;
import com.example.demo.model.ContractCreatedEvent;
import com.example.demo.model.ContractTerminatedEvent;
import com.example.demo.model.PriceDecreasedEvent;
import com.example.demo.model.PriceIncreasedEvent;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-26T15:21:28+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public ContractCreatedEvent eventDTOToContractCreatedEvent(EventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        ContractCreatedEvent contractCreatedEvent = new ContractCreatedEvent();

        contractCreatedEvent.setId( eventDTO.getId() );
        if ( eventDTO.getContractId() != null ) {
            contractCreatedEvent.setContractId( Long.parseLong( eventDTO.getContractId() ) );
        }
        contractCreatedEvent.setName( eventDTO.getName() );
        contractCreatedEvent.setPremium( eventDTO.getPremium() );
        contractCreatedEvent.setStartDate( eventDTO.getStartDate() );

        return contractCreatedEvent;
    }

    @Override
    public ContractTerminatedEvent eventDTOToContractTerminatedEvent(EventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        ContractTerminatedEvent contractTerminatedEvent = new ContractTerminatedEvent();

        contractTerminatedEvent.setId( eventDTO.getId() );
        if ( eventDTO.getContractId() != null ) {
            contractTerminatedEvent.setContractId( Long.parseLong( eventDTO.getContractId() ) );
        }
        contractTerminatedEvent.setName( eventDTO.getName() );
        contractTerminatedEvent.setTerminationDate( eventDTO.getTerminationDate() );

        return contractTerminatedEvent;
    }

    @Override
    public PriceDecreasedEvent eventDTOToPriceDecreasedEvent(EventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        PriceDecreasedEvent priceDecreasedEvent = new PriceDecreasedEvent();

        priceDecreasedEvent.setId( eventDTO.getId() );
        if ( eventDTO.getContractId() != null ) {
            priceDecreasedEvent.setContractId( Long.parseLong( eventDTO.getContractId() ) );
        }
        priceDecreasedEvent.setName( eventDTO.getName() );
        priceDecreasedEvent.setPremiumReduction( eventDTO.getPremiumReduction() );
        priceDecreasedEvent.setAtDate( eventDTO.getAtDate() );

        return priceDecreasedEvent;
    }

    @Override
    public PriceIncreasedEvent eventDTOToPriceIncreasedEvent(EventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        PriceIncreasedEvent priceIncreasedEvent = new PriceIncreasedEvent();

        priceIncreasedEvent.setId( eventDTO.getId() );
        if ( eventDTO.getContractId() != null ) {
            priceIncreasedEvent.setContractId( Long.parseLong( eventDTO.getContractId() ) );
        }
        priceIncreasedEvent.setName( eventDTO.getName() );
        priceIncreasedEvent.setPremiumIncrease( eventDTO.getPremiumIncrease() );
        priceIncreasedEvent.setAtDate( eventDTO.getAtDate() );

        return priceIncreasedEvent;
    }
}
