package com.example.demo.service;

import com.example.demo.dto.ReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private EventService eventService;

    @Override
    public ReportDTO generateReport()  {
        ReportDTO reportDTO = new ReportDTO();
        List<Integer> listOfContracts = null;
        List<Integer> listOfAGWP = null;
        List<Integer> listOfEGWP = null;

        int[][] data = getTableOfAllData();

        for (int i = 0;i<11;i++)
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

        for (int i = 0;i<11;i++)
        {
            allData[0][i] = eventService.numberOfContraftsInMonth(i);

            allData[1][i] = eventService.agwpInMonth(i);
            for (int j=0;j<i;j++) {allData[1][i] = allData[1][j];}

            if (i == 0)allData[2][i] = eventService.egwpInMonth(i,0);
            else allData[2][i] = eventService.egwpInMonth(i,allData[1][i-1]);
        }

        return allData;
    }
}
