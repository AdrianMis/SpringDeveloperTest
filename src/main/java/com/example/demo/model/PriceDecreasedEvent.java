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
@Table(name = "price_decreased_event")
public class PriceDecreasedEvent extends Event{
    @Column(name = "premium_reduction")
    private Integer premiumReduction;

    @Column(name = "at_date")
    private LocalDate atDate;

}
