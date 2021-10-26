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
@Table(name = "price_increased_event")
public class PriceIncreasedEvent extends Event {

    @Column(name = "premium_increase")
    private Integer premiumIncrease;

    @Column(name = "at_date")
    private LocalDate atDate;
}
