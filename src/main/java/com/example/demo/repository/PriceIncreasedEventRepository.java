package com.example.demo.repository;

import com.example.demo.model.PriceIncreasedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceIncreasedEventRepository extends JpaRepository<PriceIncreasedEvent, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM price_increased_event WHERE contract_id=:contractId")
    @Modifying
    PriceIncreasedEvent findPriceIncreasedEventByContractId(@Param("contractId") Long contractId);
}
