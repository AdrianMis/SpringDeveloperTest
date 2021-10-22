package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contract_terminated_event")
public class ContractTerminatedEvent extends Event {

    @Column(name = "termination_date")
    private LocalDate terminationDate;

}

