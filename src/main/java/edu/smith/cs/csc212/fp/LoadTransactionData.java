package edu.smith.cs.csc212.fp;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
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
							if(b.title.equals(title)&&b.call.equals(call)){
								b.addTransaction(patron);
							}
						}
					}
				}
				
			}
		}
		
		
		
		
		ArrayList<Book> sortedBooks = SortBooks.recursionMergeSort(bookList);
		int numZero=0;
		for(Book b: sortedBooks) {
			if(b.avgCheckOutWeighted<=0) {
				numZero++;
			}else {
				System.out.println(b.avgCheckOutWeighted);
				break;
			}
		}
		
		double percent = .15;
		int numToRemove;
		if(percent*sortedBooks.size()<numZero) {
			numToRemove=numZero;
		}else {
			numToRemove = (int) (percent*sortedBooks.size());
		}
		
		List<Book> toWeed = sortedBooks.subList(0, numToRemove);
		List<Book> toKeep = sortedBooks.subList(numToRemove, sortedBooks.size());
		
		ArrayList<Book> toRemove = new ArrayList<Book>();
		
		ArrayList<String> authorList = new ArrayList<String>();
		
		for(Book b: toKeep) {
			if(!authorList.contains(b.full)) {
				authorList.add(b.full);
			}
		}
		
		for(Book b: toWeed) {
			
			if(ClassicsAndLocal.classics().contains(b.full)||ClassicsAndLocal.local().contains(b.full)) {
				//System.out.println(b.title+ "classics or local "+b.full);
				toRemove.add(b);
			}else if(b.yearAcquired>2015) {
				toRemove.add(b);
			}else if(authorList.contains(b.full)) {
				toRemove.add(b);
			}else if(b.FiveYcheckOut>2||b.pastYearCheckOut>0) {
				toRemove.add(b);
			}
		}
		
		for(Book b: toRemove) {
			toWeed.remove(b);
		}
		
		int i = 0;
		System.out.println(toWeed.size());
		for(Book b: toWeed) {
			System.out.println(i);
			b.printBook();
			i++;
		}
		
		//what I want to return:
		//book, author, call
		//total checkouts, checkouts past 5 years
		
		
OutputWriter.saveToFile("weedme.csv", toWeed);		
		
		

		

}



}
