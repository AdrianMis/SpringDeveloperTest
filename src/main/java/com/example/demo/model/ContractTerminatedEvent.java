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
@Table(name = "contract_terminated_event")
public class ContractTerminatedEvent extends Event {

    @Column(name = "termination_date")
    private LocalDate terminationDate;

}

