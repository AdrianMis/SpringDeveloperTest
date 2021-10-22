package com.example.demo.service;

import com.example.demo.dto.ReportDTO;

public interface EventService {

    ReportDTO generateReport();

    int numberOfContrafts();

    int expectedGrossWrittenPremium();

    int actualGrossWrittenPremium();

}
