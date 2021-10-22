package com.example.demo.service;

import com.example.demo.dto.ReportDTO;
import com.example.demo.model.ContractCreatedEvent;
import com.example.demo.model.ContractTerminatedEvent;
import com.example.demo.model.PriceDecreasedEvent;
import com.example.demo.model.PriceIncreasedEvent;
import com.example.demo.repository.ContractCreatedEventRepository;
import com.example.demo.repository.ContractTerminatedEventRepository;
import com.example.demo.repository.PriceDecreasedEventRepository;
import com.example.demo.repository.PriceIncreasedEventRepository;
import com.example.demo.service.EventService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final ContractCreatedEventRepository contractCreatedEventRepository;
    private final ContractTerminatedEventRepository contractTerminatedEventRepository;
    private final PriceDecreasedEventRepository priceDecreasedEventRepository;
    private final PriceIncreasedEventRepository priceIncreasedEventRepository;

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

    @Override
    public int numberOfContrafts(){
        Iterable<ContractCreatedEvent> allCreatedContract = contractCreatedEventRepository.findAll();
        Iterable<ContractTerminatedEvent> allTerminatedContract = contractTerminatedEventRepository.findAll();
        int counterOfCreatedContract = 0;
        for (Object i : allCreatedContract) {
            counterOfCreatedContract++;
        }

        int counterOfTerminatedContract = 0;
        for (Object i : allTerminatedContract) {
            counterOfTerminatedContract++;
        }
        int numberOfContrafts = counterOfCreatedContract - counterOfTerminatedContract;
        if (numberOfContrafts < 0)return 0;
        return numberOfContrafts;
    }

    @Override
    public int expectedGrossWrittenPremium(){
        int month = LocalDate.now().getMonthValue();
        return  egwpInMonth(month,agwpInMonth(month));
    }

    @Override
    public int actualGrossWrittenPremium(){
        int month = LocalDate.now().getMonthValue();
        return agwpInMonth(month);
    }

    public int numberOfContraftsInMonth(int month){
        Iterable<ContractCreatedEvent> allCreatedContract = contractCreatedEventRepository.findAll();
        int numberOfContrafts = 0;

        while ( allCreatedContract.iterator().hasNext()){
            ContractCreatedEvent crContract = allCreatedContract.iterator().next();
            if (isWorking(crContract,month)) numberOfContrafts++;
        }

        return numberOfContrafts;
    }

    public int agwpInMonth(int month){
        Iterable<ContractCreatedEvent> allCreatedContract = contractCreatedEventRepository.findAll();
        int agwpInMonth = 0;

        while ( allCreatedContract.iterator().hasNext()){
            ContractCreatedEvent crContract = allCreatedContract.iterator().next();
            if (isWorking(crContract,month)) {
                agwpInMonth += crContract.getPremium() + priceIncreased(crContract,month) - priceDecreased(crContract,month);}
        }

        return agwpInMonth;
    }

    public int egwpInMonth(int month,int agwp){

        Iterable<ContractCreatedEvent> allCreatedContract = contractCreatedEventRepository.findAll();
        int egwpInMonth = 0;

        while ( allCreatedContract.iterator().hasNext()){
            ContractCreatedEvent crContract = allCreatedContract.iterator().next();
            if (isWorking(crContract,month)) {
                egwpInMonth = crContract.getPremium();
            }
        }
        egwpInMonth = egwpInMonth * (13 - month);

        return egwpInMonth + agwp;
    }

    public boolean isWorking(ContractCreatedEvent crContract, int month) {
        int createdMonth = crContract.getStartDate().getMonth().getValue();
        Integer terminatedMonth = contractTerminatedEventRepository.findDateOfContractTerminatedEventByContractId(crContract.getContractId()).getMonth().getValue();

        if ((month >= createdMonth)) {
            if (terminatedMonth !=null) {
                if (month < terminatedMonth) {return true;}
            }else {return true;}
        }

        return false;
    }

    public int priceIncreased(ContractCreatedEvent crContract, int month) {
        PriceIncreasedEvent priceIncreasedEvent = priceIncreasedEventRepository.findPriceIncreasedEventByContractId(crContract.getContractId());
        Integer atDate = priceIncreasedEvent.getAtDate().getMonthValue();
        Integer premium = priceIncreasedEvent.getPremiumIncrease();

        if (atDate !=null) {
            if (month > atDate) {return premium;}
        }
        return 0;
    }

    public int priceDecreased(ContractCreatedEvent crContract, int month) {
        PriceDecreasedEvent priceDecreasedEvent = priceDecreasedEventRepository.findPriceDecreasedEventByContractId(crContract.getContractId());
        Integer atDate = priceDecreasedEvent.getAtDate().getMonthValue();
        Integer premium = priceDecreasedEvent.getPremiumReduction();

        if (atDate !=null) {
            if (month > atDate) {return premium;}
        }
        return 0;
    }

    public int[][] getTableOfAllData(){
        int[][] allData = new int[3][12];

        for (int i = 0;i<11;i++)
        {
            allData[0][i] = numberOfContraftsInMonth(i);

            allData[1][i] = agwpInMonth(i);
            for (int j=0;j<i;j++) {allData[1][i] = allData[1][j];}

            if (i == 0)allData[2][i] = egwpInMonth(i,0);
            else allData[2][i] = egwpInMonth(i,allData[1][i-1]);
        }

        return allData;
    }

//    public Resource generateCSVReport() throws IOException {
//        File file = new File("Report.csv");
//        try {
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);
//            PrintWriter pw = new PrintWriter(bw);
//
//            int[][] data = getTableOfAllData();
//            List<String> columnsNames = Arrays.asList("","Jan", "Feb", "Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
//            List<String> rowsNames = Arrays.asList("Number of Contracts","AGWP", "EGWP");
//            Boolean first = true;
//            for (String column : columnsNames) {
//                if (first == true) {
//                    first = false;
//                    pw.print(column);
//                } else {pw.print("," + column);}
//            }
//            pw.println();
//
//            for (int i = 0; i < data.length; i++) {
//                pw.print(rowsNames.indexOf(i));
//                for (int j = 0; j < data[i].length; j++) {
//                    pw.print("," + data[i][j]);
//                }pw.println();
//            }
//            pw.flush();
//            pw.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        byte[] fileContent = Files.readAllBytes(file.toPath());
//        Resource resource = new ByteArrayResource(fileContent);
//        return resource;
//    }

}
