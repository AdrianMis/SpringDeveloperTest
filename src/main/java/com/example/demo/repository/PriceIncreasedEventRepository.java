package com.example.demo.repository;

import com.example.demo.model.PriceIncreasedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PriceIncreasedEventRepository extends JpaRepository<PriceIncreasedEvent, Long> {

    @Query(value = "select * from price_increased_event  where name = :name and contractId = :contractId and month(atDate) = :atDate_month", nativeQuery = true)
    List<PriceIncreasedEventRepository> findAllByNameAndContractIdAndAtDate_Month(String name, Long contractId, Integer atDate_month);

}
