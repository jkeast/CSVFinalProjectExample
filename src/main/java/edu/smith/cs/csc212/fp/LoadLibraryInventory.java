package edu.smith.cs.csc212.fp;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class LoadLibraryInventory {
	// Fields-list:
	// generated,CopyBarcode,CallNumber,Title,Author,
	// LCCN,ISBN,MaterialType,PublicationYear,Status,
	// DateLastAccountedFor,DateAcquired,CirculationType,
	// TotalCirculations,CirculationsThisYear,CirculationsLastYear,
	// CirculationsThisMonth,CirculationsToday,SiteShortName,DueDate,
	// DateCheckedOut,Sublocation,PatronType,PurchasePrice,
	// CopyNumber,Vendor,ISSN,DescEnum1
	
	public static void main(String[] args) throws IOException {
		try (Reader in = new FileReader("inventory.csv")) {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord record : records) {
				String title = record.get("Title");
				String author = record.get("Author");
				if (author.isEmpty()) {
					System.out.println(title + " by Unknown Author.");
				} else {
					System.out.println(title + " by " + author);
				}
			}
		}
	}
}
