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
	
	/**
	 * list of all fiction books sorted by average number of checkouts
	 */
	SortBooks sorted;
	
/**
 * loads in transaction data and counts number of checkouts for all individual fiction books
 * 
 * then makes calls on which books are good candidates to weed
 * @param args
 * @throws IOException
 */
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
				
				//only looks at check out info
				if(transaction.equals("Checked out")) {

					//looks to see if book is in our list of fiction books
					//if so, adds to check out number for that book
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
		
		
		
		//sorts books by number of average checkouts
		ArrayList<Book> sortedBooks = SortBooks.recursionMergeSort(bookList);
		
		//finds number of books with no check outs
		int numZero=0;
		for(Book b: sortedBooks) {
			//goes less than zero to count for negatively weighted books
			if(b.avgCheckOutWeighted<=0) {
				numZero++;
			}else {
				System.out.println(b.avgCheckOutWeighted);
				break;
			}
		}
		
		//percent of books to be weeded
		double percent = .15;
		
		//number of books to suggest to weed
		int numToRemove;
		
		//if books with zero checkouts exceeds percentage to weed
		//will suggest all books with zero checkouts (and ignore percentage)
		if(percent*sortedBooks.size()<numZero) {
			numToRemove=numZero;
		}else {
			numToRemove = (int) (percent*sortedBooks.size());
		}
		
		//splits list of sorted books into list to week and list to keep
		List<Book> toWeed = sortedBooks.subList(0, numToRemove);
		List<Book> toKeep = sortedBooks.subList(numToRemove, sortedBooks.size());
		
		ArrayList<Book> toRemove = new ArrayList<Book>();
		
		ArrayList<String> authorList = new ArrayList<String>();
		
		//list of all authors whose books are in keep list
		for(Book b: toKeep) {
			if(!authorList.contains(b.full)) {
				authorList.add(b.full);
			}
		}
		
		for(Book b: toWeed) {
			
			//makes sure we don't weed book by classic or local author
			if(ClassicsAndLocal.classics().contains(b.full)||ClassicsAndLocal.local().contains(b.full)) {
				toRemove.add(b);
			//makes sure we don't weed a new book
			}else if(b.yearAcquired>2015) {
				toRemove.add(b);
			//makes sure we don't remove book when keeping others by same author
				//preventative measure so don't remove part of a series while leaving other part
			}else if(authorList.contains(b.full)) {
				toRemove.add(b);
			//makes sure we don't remove book with recent circulation
			}else if(b.FiveYcheckOut>2||b.pastYearCheckOut>0) {
				toRemove.add(b);
			}
		}
		
		for(Book b: toRemove) {
			toWeed.remove(b);
		}
		
		//writes all remaining in toWeed list to csv file
		OutputWriter.saveToFile("weedme.csv", toWeed);		

}



}
