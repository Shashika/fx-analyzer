package com.shashika.tests;

import com.shashika.analyzer.CSVReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

/**
 * Created by shashika on 10/16/17.
 */
public class CSVReaderTest {

    private static String rootPath;
    private static String csvFile;
    private static CSVReader csvReader;

    @BeforeClass
    public static void initialize() {

        rootPath = new File("").getAbsolutePath();
        csvFile = rootPath+ "/src/main/resources/files/test.csv";
        csvReader = new CSVReader(csvFile, "test.csv");
    }

    @Test
    @Category(CSVReader.class)
    public void testCSVreader() {
        csvReader.read(false);
    }

    @AfterClass
    public static void shutdown(){
        csvReader = null;
    }
}
