package com.example.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contract_created_event")
public class ContractCreatedEvent extends Event{
    @Column(name = "premium")
    private Integer premium;

    @Column(name = "start_date")
    private LocalDate startDate;
}
