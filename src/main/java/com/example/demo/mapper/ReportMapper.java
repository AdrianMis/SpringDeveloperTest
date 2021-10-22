package com.example.demo.mapper;

import com.example.demo.dto.ReportDTO;
import com.example.demo.model.Report;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    Report reportDTOToReport(ReportDTO reportDTO);
    ReportDTO reportToReportDTO(Report report);
}
