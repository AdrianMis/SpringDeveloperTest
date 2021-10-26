package com.example.demo.service;

import com.example.demo.dto.ReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final EventService eventService;

    @Override
    public ReportDTO generateReport()  {
        ReportDTO reportDTO = new ReportDTO();
        List<Integer> listOfContracts = new ArrayList<Integer>();
        List<Integer> listOfAGWP = new ArrayList<Integer>();
        List<Integer> listOfEGWP = new ArrayList<Integer>();

        int[][] data = getTableOfAllData();

        for (int i = 0;i<12;i++)
        {
            listOfContracts.add(data[0][i]);
            listOfAGWP.add(data[1][i]);
            listOfEGWP.add(data[2][i]);
        }

        reportDTO.setNumberOfContracts(listOfContracts);
        reportDTO.setAGWP(listOfAGWP);
        reportDTO.setEGWP(listOfEGWP);
        return reportDTO;
    }

    public int[][] getTableOfAllData(){
        int[][] allData = new int[3][12];
        for (int i = 1;i<13;i++)
        {
            allData[0][i-1] = eventService.countingActiveContractsInSpecifyMonth(i);

            if (i == 1) allData[1][i-1] = eventService.calculateAgwpInSpecifyMonth(i);
            else allData[1][i-1] = allData[1][i-2] + eventService.calculateAgwpInSpecifyMonth(i);

            if (i == 1)allData[2][i-1] = eventService.calculateEgwpInSpecifyMonth(i,0);
            else allData[2][i-1] = eventService.calculateEgwpInSpecifyMonth(i,allData[1][i-2]);
        }

        return allData;
    }
}
