package edu.smith.cs.csc212.fp;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


public class LoadTransactionData {
	
	// Fields-list:
	// generated, TransType, Date, Time, Title,
	// LCCN, ISBN, BibType, PubYear, CopyBarcode,
	// CallNumber, CircType, PatronBarcode, PatronType
	// TransModifier, DistrictID, TransAmount, TransBalance,
	// ISSN
	
	SortBooks sorted;
	
	
public static void main(String[] args) throws IOException {
	
	HashMap<String, String> titleMap = LoadLibraryInventory.getTitleMap();
	ArrayList<Book> bookList = LoadLibraryInventory.getBookList();
	
	//SortBooks sorted = new SortBooks();
	ArrayList<Book> sortedBooks = new ArrayList<Book>();
		
		//bookList = new ArrayList<Book>();
	//HashMap<String, String> titleMap = LoadLibraryInventory.getTitleMap();
	//ArrayList<Book> bookList = LoadLibraryInventory.getBookList();
	
	//System.out.println(titleMap);
	//System.out.println(bookList);
	
		
		try (Reader in = new FileReader("transactions.csv")) {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord record : records) {
				String transaction = record.get("TransType");
				String title = record.get("Title");
				String call = record.get("CallNumber");
				String patron = record.get("PatronType");
				
				if(transaction.equals("Checked out")) {

					if(titleMap.containsKey(title)&&titleMap.get(title).equals(call)) {
						for(Book b:bookList) {
							if(b.getTitle().equals(title)&&b.getCall().equals(call)){
								b.addTransaction(patron);
							}
						}
					}
				}
				
			}
		}
		
		
		sortedBooks = SortBooks.recursionMergeSort(bookList);
		
		for(Book b: sortedBooks.subList(0, 100)) {
			b.printBook();
		}
		
		
		//sortedBooks.get(0).printBook();
		

}


}
