package edu.smith.cs.csc212.fp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class LoadLibraryInventory {
	
	static ArrayList<Book> bookList;
	static HashMap<String, String> titleMap;
	// Fields-list:
	// generated,CopyBarcode,CallNumber,Title,Author,
	// LCCN,ISBN,MaterialType,PublicationYear,Status,
	// DateLastAccountedFor,DateAcquired,CirculationType,
	// TotalCirculations,CirculationsThisYear,CirculationsLastYear,
	// CirculationsThisMonth,CirculationsToday,SiteShortName,DueDate,
	// DateCheckedOut,Sublocation,PatronType,PurchasePrice,
	// CopyNumber,Vendor,ISSN,DescEnum1
	
	public static void load() throws IOException {
		bookList = new ArrayList<Book>();
		titleMap = new HashMap<String, String>();
		
		
		try (Reader in = new FileReader("inventory.csv")) {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord record : records) {
				String title = record.get("Title");
				String author = record.get("Author");
				String call = record.get("CallNumber");
				String acquired = record.get("DateAcquired");
				String totalCheckOut = record.get("TotalCirculations");
				Integer pastYearCheckOut = Integer.parseInt(record.get("CirculationsThisYear"))+Integer.parseInt(record.get("CirculationsLastYear"));
				
				if(call.startsWith("FIC")||call.startsWith("741.5")){
					
					if(titleMap.containsKey(title)&&titleMap.get(title).equals(call)) {
						for(Book b:bookList) {
							if(b.title.equals(title)&&b.call.equals(call)){
								b.addCopy();
							}
						}
					}else {	
					
					Book fic = new Book(title, author, call, acquired, totalCheckOut, pastYearCheckOut);
					bookList.add(fic);
					titleMap.put(title, call);
					}

				}

			}
		}

	}
	
	public static void main(String[] args) throws IOException {
		load();
		
	}
	
	public static HashMap<String, String> getTitleMap(){
		if (titleMap == null || titleMap.isEmpty()) {
			try {
				load();
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}
		return titleMap;
	}
	
	public static ArrayList<Book> getBookList(){
		if (bookList == null || bookList.isEmpty()) {
			try {
				load();
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}
		return bookList;
	}
}
