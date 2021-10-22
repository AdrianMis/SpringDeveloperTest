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
@Table(name = "price_increased_event")
public class PriceIncreasedEvent extends Event{

    @Column(name = "premium_increase")
    private Integer premiumIncrease;

    @Column(name = "at_date")
    private LocalDate atDate;
}
