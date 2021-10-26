package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.PriceDecreasedEventRepository;
import com.example.demo.repository.PriceIncreasedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final PriceIncreasedEventRepository priceIncreasedEventRepository;
    private final PriceDecreasedEventRepository priceDecreasedEventRepository;

    @Override
    public void save(Event event) {
        this.eventRepository.save(event);
    }

    private List<Event> findAllContractCreatedEvent() {
        return this.eventRepository.findAllByName("ContractCreatedEvent");
    }

    private Long countContractCreatedEvent() {
        return this.eventRepository.countAllByName("ContractCreatedEvent");
    }

    private Long countContractTerminatedEvent() {
        return this.eventRepository.countAllByName("ContractTerminatedEvent");
    }

    public int countingActiveContractsInSpecifyMonth(int month) {
        return getActiveContractIds(month).size();
    }

    public int calculateAgwpInSpecifyMonth(int month) {
        List<Long> activeContractIds = getActiveContractIds(month);
        int agwp = 0;
        for (Long contractId : activeContractIds)
            agwp +=
                    getPremiumByContractId(contractId)
                            + totalIncreasedPremiumInMonth(contractId, month)
                            - totalDecreasedPremiumInMonth(contractId, month);

        return agwp;
    }


    public int calculateEgwpInSpecifyMonth(int month, int agwp) {
        List<Long> activeContractIds = getActiveContractIds(month);
        int egwp = 0;
        for (Long contractId : activeContractIds)
            egwp +=
                    getPremiumByContractId(contractId)
                            + totalIncreasedPremiumInMonth(contractId, month)
                            - totalDecreasedPremiumInMonth(contractId, month);

        egwp = egwp * (13 - month);
        return egwp + agwp;
    }

    private Integer getPremiumByContractId(Long contractId) {
        return eventRepository.findPremiumByContractId(contractId);
    }

    private List<Long> getActiveContractIds(int month){
        List<Long> idsExistInSpecifyMonth = eventRepository.getContractIdsExistInSpecifyMonth(month);
        return eventRepository.getOnlyActiveContractInSpecifyMonthFromList(month, idsExistInSpecifyMonth);
    }
    public Long totalIncreasedPremiumInMonth(Long id, int month) {
        Long sum = priceIncreasedEventRepository.calculateSumByContractIdAndMonth(id, month);
        return sum != null ? sum : 0;
    }

    public Long totalDecreasedPremiumInMonth(Long id, int month) {
        Long sum = priceDecreasedEventRepository.calculateSumByContractIdAndMonth(id, month);
        return sum != null ? sum : 0;
    }
}
