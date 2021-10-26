package com.example.demo.repository;

import com.example.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT contract_created_event.premium " +
            "FROM contract_created_event " +
            "INNER JOIN event ON contract_created_event.id = event.id " +
            "WHERE event.contract_id = :contractId",
            nativeQuery = true)
    Integer findPremiumByContractId(Long contractId);

    @Query(value = "SELECT DISTINCT event.contract_id " +
            "FROM contract_created_event " +
            "INNER JOIN event ON contract_created_event.id = event.id " +
            "WHERE EXTRACT(MONTH FROM start_date) <= :month",
            nativeQuery = true)
    List<Long> getContractIdsExistInSpecifyMonth(Integer month);

    @Query(value = "SELECT DISTINCT event.contract_id " +
            "FROM contract_terminated_event " +
            "INNER JOIN event ON contract_terminated_event.id = event.id " +
            "WHERE EXTRACT(MONTH FROM termination_date) >= :month " +
            "AND event.contract_id IN :ids",
            nativeQuery = true)
    List<Long> getOnlyActiveContractInSpecifyMonthFromList(Integer month, List<Long> ids);

    @Query(value = "SELECT SUM(premium_increase) " +
            "FROM price_increased_event " +
            "INNER JOIN event ON price_increased_event.id = event.id " +
            "WHERE contract_id = :contractId " +
            "AND EXTRACT(MONTH FROM at_date) <= :month",
            nativeQuery = true)
    Long calculateSumByContractIdAndMonthIncreased(Long contractId, Integer month);

    @Query(value = "SELECT SUM(premium_reduction) " +
            "FROM price_decreased_event " +
            "INNER JOIN event ON price_decreased_event.id = event.id " +
            "WHERE contract_id = :contractId " +
            "AND EXTRACT(MONTH FROM at_date) <= :month",
            nativeQuery = true)
    Long calculateSumByContractIdAndMonthDecreased(Long contractId, Integer month);
}
