package com.example.demo.repository;

import com.example.demo.model.ContractTerminatedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ContractTerminatedEventRepository  extends JpaRepository<ContractTerminatedEvent, Long> {
    @Query(nativeQuery = true, value = "SELECT termination_date FROM contract_terminated_event WHERE contract_id=:contractId")
    @Modifying
    LocalDate findDateOfContractTerminatedEventByContractId(@Param("contractId") Long contractId);
}
