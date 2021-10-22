package com.example.demo.repository;

import com.example.demo.model.PriceDecreasedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PriceDecreasedEventRepository  extends JpaRepository<PriceDecreasedEvent, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM price_decreased_event WHERE contract_id=:contractId")
    @Modifying
    PriceDecreasedEvent findPriceDecreasedEventByContractId(@Param("contractId") Long contractId);
}
