package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Report {
    private List<Integer> numberOfContracts;

    private List<Integer> AGWP;

    private List<Integer> EGWP;
}
