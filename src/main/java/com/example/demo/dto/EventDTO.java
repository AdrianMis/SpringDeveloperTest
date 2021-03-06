package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDTO {
    private Long id;

    private String contractId;

    private String name;

    private int premium;

    private int premiumReduction;

    private int premiumIncrease;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate atDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate terminationDate;

}
