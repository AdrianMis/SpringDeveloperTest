package com.example.demo.repository;

import com.example.demo.model.ContractCreatedEvent;
import com.example.demo.model.PriceIncreasedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractCreatedEventRepository  extends JpaRepository<ContractCreatedEvent, Long> {
}
