package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository  extends JpaRepository<Event, Long> {
//    @Query(nativeQuery = true, value = "SELECT termination_date FROM contract_terminated_event WHERE contract_id=:contractId")

    //LocalDate findTerminationDateByContractId(Long contractId);

    List<Event> findAllByContractIdAndName(Long contractId, String name);

    List<Event> findAllByName(@Param("name") String name);

    Long countAllByName(String name);

    @Query(value = "SELECT contract_created_event.premium " +
            "FROM contract_created_event " +
            "INNER JOIN event ON contract_created_event.id = event.id " +
            "WHERE event.contract_id = :contractId",
            nativeQuery = true)
    Integer findPremiumByContractId(Long contractId);

    @Query(value = "SELECT event.contract_id " +
            "FROM contract_created_event " +
            "INNER JOIN event ON contract_created_event.id = event.id " +
            "WHERE EXTRACT(MONTH FROM start_date) <= :month",
            nativeQuery = true)
    List<Long> getContractIdsExistInSpecifyMonth(Integer month);


    @Query(value = "SELECT event.contract_id " +
            "FROM contract_terminated_event " +
            "INNER JOIN event ON contract_terminated_event.id = event.id " +
            "WHERE EXTRACT(MONTH FROM termination_date) >= :month " +
            "AND event.contract_id IN :ids",
            nativeQuery = true)
    List<Long> getOnlyActiveContractInSpecifyMonthFromList(Integer month, List<Long> ids);


/*    @Query(nativeQuery = true, value = "SELECT * FROM price_increased_event WHERE contract_id=:contractId")
    PriceIncreasedEvent findPriceIncreasedEventByContractId(@Param("contractId") Long contractId);

    @Query(nativeQuery = true, value = "SELECT * FROM contract_terminated_event WHERE name=:name")
    ContractTerminatedEvent findAllContractTerminated(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * FROM price_increased_event WHERE name=:name")
    PriceIncreasedEvent findAllPriceIncreased(@Param("name") String name);*/

//    @Query(nativeQuery = true, value = "SELECT * FROM event WHERE name=:name")


//
//    @Query(nativeQuery = true, value = "SELECT * FROM price_decreased_event WHERE name=:name")
//    PriceDecreasedEvent findAllPriceDecreased(@Param("name") String name);
}
