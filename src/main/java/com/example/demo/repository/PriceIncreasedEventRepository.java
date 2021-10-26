package com.example.demo.repository;

import com.example.demo.model.PriceIncreasedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceIncreasedEventRepository extends JpaRepository<PriceIncreasedEvent, Long> {

    @Query(value = "SELECT SUM(premium_increase) " +
            "FROM price_increased_event " +
            "INNER JOIN event ON price_increased_event.id = event.id " +
            "WHERE contract_id = :contractId " +
            "AND EXTRACT(MONTH FROM at_date) = :month",
            nativeQuery = true)
    Long calculateSumByContractIdAndMonth(Long contractId, Integer month);

}