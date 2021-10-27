package com.example.demo.service.internal;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.boundary.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void save(List<Event> events) {
        eventRepository.saveAll(events);
    }

    @Override
    public int countingActiveContractsInSpecifyMonth(int month) {
        return getActiveContractIds(month).size();
    }

    @Override
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

    @Override
    public int calculateEgwpInSpecifyMonth(int month, int agwp) {
        List<Long> activeContractIds = getActiveContractIds(month);
        int egwp = 0;
        for (Long contractId : activeContractIds)
            egwp +=
                    getPremiumByContractId(contractId)
                            + totalIncreasedPremiumInMonth(contractId, month)
                            - totalDecreasedPremiumInMonth(contractId, month);
        return egwp * (13 - month) + agwp;
    }

    private Integer getPremiumByContractId(Long contractId) {
        return eventRepository.findPremiumByContractId(contractId);
    }

    private List<Long> getActiveContractIds(int month) {
        List<Long> idsExistInSpecifyMonth = eventRepository.getContractIdsExistInSpecifyMonth(month);
        List<Long> activeInSpecifyMonth = eventRepository.getOnlyActiveContractInSpecifyMonthFromList(month, idsExistInSpecifyMonth);
        List<Long> notTerminated = eventRepository.getNotTerminatedContractInSpecifyMonth(month);
        activeInSpecifyMonth.addAll(notTerminated);
        return activeInSpecifyMonth;
    }

    private Long totalIncreasedPremiumInMonth(Long id, int month) {
        Long sum = eventRepository.calculateSumByContractIdAndMonthIncreased(id, month);
        return sum != null ? sum : 0;
    }

    private Long totalDecreasedPremiumInMonth(Long id, int month) {
        Long sum = eventRepository.calculateSumByContractIdAndMonthDecreased(id, month);
        return sum != null ? sum : 0;
    }
}
