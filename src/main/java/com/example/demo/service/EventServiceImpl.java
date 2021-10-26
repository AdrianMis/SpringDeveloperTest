package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public int numberOfContracts(){
        List<Event> allCreatedContract = findAllContractCreatedEvent();
        List<Event> allTerminatedContract =  findAllContractTerminatedEvent();
        int counterOfCreatedContract = allCreatedContract.size();
        int counterOfTerminatedContract = allTerminatedContract.size();

        int numberOfContracts = counterOfCreatedContract - counterOfTerminatedContract;
        if (numberOfContracts < 0)
            return 0;

        return numberOfContracts;
    }

    @Override
    public int expectedGrossWrittenPremium(){
        int month = LocalDate.now().getMonthValue();
        return  egwpInMonth(month,agwpInMonth(month));
    }

    @Override
    public int actualGrossWrittenPremium(){
        int month = LocalDate.now().getMonthValue();
        return agwpInMonth(month);
    }

    @Override
    public void save(Event event) {
        this.eventRepository.save(event);
    }

    @Override
    public List<Event> findAllContractCreatedEvent() {
        return this.eventRepository.findAllByName("ContractCreatedEvent");
    }

    @Override
    public List<Event> findAllContractTerminatedEvent() {
        return this.eventRepository.findAllByName("ContractTerminatedEvent");
    }

    @Override
    public List<Event> findAllTerminatedContractByContractIdAndName(Long id){
        return this.eventRepository.findAllByContractIdAndName(id,"ContractTerminatedEvent");
    }

    @Override
    public List<Event> findAllPriceIncreasedByContractIdAndName(Long id){
        return eventRepository.findAllByContractIdAndName(id,"PriceIncreasedEvent");
    }

    @Override
    public List<Event> findAllPriceDecreasedByContractIdAndName(Long id){
        return eventRepository.findAllByContractIdAndName(id,"PriceDecreasedEvent");
    }

    public int numberOfContractsInMonth(int month){
        List<Event> allCreatedContract = findAllContractCreatedEvent();
        int numberOfContracts = 0;

        for (Event event : allCreatedContract) {
            if (isWorking((ContractCreatedEvent) event, month)) numberOfContracts++;
        }
        return numberOfContracts;
    }

    public int agwpInMonth(int month){
        List<Event> allCreatedContract = findAllContractCreatedEvent();
        int agwpInMonth = 0;
        for (Event event : allCreatedContract) {
            if (isWorking((ContractCreatedEvent) event, month)) {
                agwpInMonth += ((ContractCreatedEvent) event).getPremium() + totalIncreasedPremiumInMonth((ContractCreatedEvent) event, month) - totalDecreasedPremiumInMonth((ContractCreatedEvent) event, month);
            }
        }
        return agwpInMonth;
    }

    public int egwpInMonth(int month,int agwp){
        List<Event> allCreatedContract = findAllContractCreatedEvent();
        int egwpInMonth = 0;
        for (Event event : allCreatedContract) {
            if (isWorking((ContractCreatedEvent) event,month)) {
                egwpInMonth += ((ContractCreatedEvent) event).getPremium() + totalIncreasedPremiumInMonth((ContractCreatedEvent) event, month) - totalDecreasedPremiumInMonth((ContractCreatedEvent) event, month);
            }
        }

        egwpInMonth = egwpInMonth * (13 - month);
        return egwpInMonth + agwp;
    }

    public boolean isWorking(ContractCreatedEvent crContract, int month) {
        int createdMonth = crContract.getStartDate().getMonth().getValue();
        List<Event> listOfTerminatedContract = findAllTerminatedContractByContractIdAndName(crContract.getContractId());
        Integer terminatedMonth;

        for (Event event : listOfTerminatedContract) {
            terminatedMonth = ((ContractTerminatedEvent) event).getTerminationDate().getMonth().getValue();
            if ((month >= createdMonth) && (month > terminatedMonth)) return false;
        }
        return true;
    }

    public int totalIncreasedPremiumInMonth(ContractCreatedEvent crContract, int month) {
        List<Event> priceIncreasedEvent = findAllPriceIncreasedByContractIdAndName(crContract.getContractId());
        int totalPremium = 0;
        for(Event event : priceIncreasedEvent)
        {
            Integer atDate = ((PriceIncreasedEvent) event).getAtDate().getMonthValue() - 1;
            int premium = ((PriceIncreasedEvent) event).getPremiumIncrease();
            if (atDate !=null) {
                if (month > atDate) { totalPremium = totalPremium+ premium ;}
            }
        }
        return totalPremium;
    }

    public int totalDecreasedPremiumInMonth(ContractCreatedEvent crContract, int month) {
        List<Event> priceDecreasedEvent = findAllPriceDecreasedByContractIdAndName(crContract.getContractId());
        int totalPremium = 0;
        for(Event event : priceDecreasedEvent)
        {
            Integer atDate = ((PriceDecreasedEvent) event).getAtDate().getMonthValue() - 1;
            int premium = ((PriceDecreasedEvent) event).getPremiumReduction();
            if (atDate !=null) {
                if (month > atDate) { totalPremium = totalPremium+ premium ;}
            }
        }
        return 0;
    }


}
