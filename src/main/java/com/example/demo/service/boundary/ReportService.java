package com.example.demo.service.boundary;

import com.example.demo.dto.Report;
import org.springframework.stereotype.Service;

@Service
public interface ReportService {

    Report generateReport();
}
