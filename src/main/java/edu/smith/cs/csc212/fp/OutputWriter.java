package edu.smith.cs.csc212.fp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

//got code for this class from callicoder
//https://www.callicoder.com/java-read-write-csv-file-apache-commons-csv/
//and geeksforgeeks
//https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/

public class OutputWriter {
    public static void saveToFile(String where, List<Book> data) throws IOException {
    	//try-with-resources
        try (
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(where));
//b.title, b.author, b.call, String.valueOf(b.copyNumber), 
				//String.valueOf(b.totCheckOut), String.valueOf(b.FiveYcheckOut)}
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("Title", "Author", "CallNumber", "NumberOfCopies",
                    		"TotalCheckouts", "NumberCheckoutsPast5Years"));
        ) {
        	for(Book b:data) {
    			csvPrinter.printRecord(b.title, b.author, b.call, b.copyNumber, 
    					b.totCheckOut, b.FiveYcheckOut);
    		}          
        }
    }
}



