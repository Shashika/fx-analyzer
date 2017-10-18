package com.shashika.creater;

import com.shashika.common.Constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by shashika on 10/16/17.
 */
public class CSVCreator {

    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, Constants.CSV_SEPARATOR, ' ');
    }

    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;
    }

    public static void writeLine(Writer w, List<String> values, String separators, char customQuote) throws IOException {

        boolean first = true;

        if (separators.equals(" ")) {
            separators = Constants.CSV_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());

    }

    public static void main(String[] args) throws IOException {

        String rootPath = new File("").getAbsolutePath();
        Random random = new Random();

        double min = -10000;
        double max = 10000;

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileName = Long.toString(timestamp.getTime());

        String csvFile = rootPath+ "/src/main/resources/files/"+ fileName +".csv";
        FileWriter writer = new FileWriter(csvFile);

        int dealID = random.nextInt(100000);
        long timeStamp = timestamp.getTime();

        for(int i = 0; i < 100000; i++){

            String fromISOCode  = Constants.ISO_CODES[random.nextInt(Constants.ISO_CODES.length)];
            String toISOCode    = Constants.ISO_CODES[random.nextInt(Constants.ISO_CODES.length)];
            double amount = Math.random() * ( max - min ) + min;
            String amountStr = new DecimalFormat(Constants.CURRENCY_FORMAT).format(amount);

            CSVCreator.writeLine(writer, Arrays.asList(Integer.toString(dealID), fromISOCode, toISOCode, Long.toString(timeStamp), amountStr));

            dealID++;
            timeStamp+=100;
        }

        writer.flush();
        writer.close();
    }
}
