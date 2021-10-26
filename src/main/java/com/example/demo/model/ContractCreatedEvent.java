package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "contract_created_event")
public class ContractCreatedEvent extends Event {
    @Column(name = "premium")
    private Integer premium;

    @Column(name = "start_date")
    private LocalDate startDate;
}
