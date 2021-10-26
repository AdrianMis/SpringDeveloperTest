package com.example.demo.service.internal;

import com.example.demo.dto.Report;
import com.example.demo.service.boundary.EventService;
import com.example.demo.service.boundary.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final EventService eventService;

    @Override
    public Report generateReport() {
        Integer[][] data = getTableOfAllData();

        return Report.builder()
                .numberOfContracts(List.of(data[0]))
                .AGWP(List.of(data[1]))
                .EGWP(List.of(data[2]))
                .build();
    }

    public Integer[][] getTableOfAllData() {
        Integer[][] allData = new Integer[3][12];

        IntStream.range(1, 13).forEachOrdered(i -> {
            allData[0][i - 1] = eventService.countingActiveContractsInSpecifyMonth(i);

            if (i == 1)
                allData[1][i - 1] = eventService.calculateAgwpInSpecifyMonth(i);
            else
                allData[1][i - 1] = allData[1][i - 2] + eventService.calculateAgwpInSpecifyMonth(i);

            if (i == 1)
                allData[2][i - 1] = eventService.calculateEgwpInSpecifyMonth(i, 0);
            else
                allData[2][i - 1] = eventService.calculateEgwpInSpecifyMonth(i, allData[1][i - 2]);
        });
        return allData;
    }
}
