package com.shashika.tests;

import com.shashika.creater.CSVCreator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by shashika on 10/16/17.
 */
public class CSVCreatorTest {

    private static String rootPath;
    private static String csvFile;
    private static FileWriter writer = null;

    @BeforeClass
    public static void initialize() throws IOException {

        rootPath = new File("").getAbsolutePath();
        csvFile = rootPath+ "/src/main/resources/files/test.csv";
        writer = new FileWriter(csvFile);
    }

    @Test
    @Category(CSVCreator.class)
    public void testCSVwrite() throws IOException {

        CSVCreator.writeLine(writer, Arrays.asList(Integer.toString(1256), "USD", "SGD", Long.toString(1508206936119l), "0"));
        System.out.println("Write csv line");
    }

    @AfterClass
    public static void shutdown(){
        writer = null;
    }

}
