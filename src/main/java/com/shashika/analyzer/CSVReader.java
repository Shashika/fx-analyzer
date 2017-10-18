package com.shashika.analyzer;

import com.shashika.common.Constants;
import com.shashika.model.DataFile;
import com.shashika.model.Deal;
import com.shashika.model.InvalidDeal;
import com.shashika.model.ValidDeal;
import com.shashika.service.DataService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shashika on 10/16/17.
 */
public class CSVReader extends Thread{

    private final static Logger LOGGER = Logger.getLogger(CSVReader.class.getName());

    private String filePath;
    private String fileName;
    private DataService dataService;
    private ArrayList<Deal> dealArrayList;
    private HashMap<String, Integer> currencyCountMap;

    public CSVReader(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.dataService = new DataService();
        this.dealArrayList = new ArrayList<Deal>();
        this.currencyCountMap = new HashMap<String, Integer>();
    }

    public void read(boolean isDataConnection){

        BufferedReader br = null;
        String line = "";
        DataFile dataFile = new DataFile(fileName);

        try {

            br = new BufferedReader(new FileReader(filePath));

            if(isDataConnection) {
                int id = dataService.saveFile(dataFile);
                dataFile.setId(id);
            }

            while ((line = br.readLine()) != null) {

                String[] transaction = line.split(Constants.CSV_SEPARATOR);

                Deal deal = getValidDeal(transaction, dataFile);

                if(deal != null){
                    dealArrayList.add(deal);
                }
            }

            if(isDataConnection){
                dataService.saveSummary(currencyCountMap);
                dataService.bulkSaveDeals(dealArrayList);
            }

            LOGGER.log(Level.INFO, "CSV Reading completed");

        } catch (FileNotFoundException e) {

            LOGGER.log(Level.SEVERE, "Uploaded file not found. "+ e);

        } catch (IOException e) {

            LOGGER.log(Level.SEVERE, "Cannot access the file. "+ e);

        } finally {

            if (br != null) {
                try {
                    br.close();
                    LOGGER.log(Level.INFO, "Buffered reader closed successfully");

                } catch (IOException e) {

                    LOGGER.log(Level.SEVERE, "Cannot close buffered reader. " + e);
                }
            }
        }

    }

    private Deal getValidDeal(String[] data, DataFile dataFile){

        Deal deal;

        if(!isNumber(data[0]) || !isNumber(data[3]) || !isDouble(data[4])){
            deal = null;
        }
        else {

            if(Double.parseDouble(data[4]) >= 0 && Arrays.asList(Constants.ISO_CODES).contains(data[1])
                                                        && Arrays.asList(Constants.ISO_CODES).contains(data[2])){
                deal = new ValidDeal();
            }
            else{
                deal = new InvalidDeal();
            }
            deal.setFileId(dataFile);
            deal.setFromIso(data[1]);
            deal.setToIso(data[2]);
            deal.setTimestamp(Long.parseLong(data[3]));
            deal.setAmount(Double.parseDouble(data[4]));

            currencyCountMap.put(data[1], currencyCountMap.containsKey(data[1]) ? currencyCountMap.get(data[1]) + 1 : 1);
        }

        return deal;
    }


    private boolean isNumber(String data){

        boolean isNumber;

        try {
            Long.parseLong(data);
            isNumber = true;
        } catch (NumberFormatException ex) {
            isNumber = false;
        }

        return isNumber;
    }

    private boolean isDouble(String data){

        boolean isDouble;

        try {
            Double.parseDouble(data);
            isDouble = true;
        } catch (NumberFormatException ex) {
            isDouble = false;
        }

        return isDouble;
    }



}
