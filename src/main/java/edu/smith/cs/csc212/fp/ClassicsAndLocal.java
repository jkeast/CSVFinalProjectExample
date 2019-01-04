package edu.smith.cs.csc212.fp;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ClassicsAndLocal {
	/**
	 * list of all authors of children's classics
	 * list obtained from https://en.wikipedia.org/wiki/List_of_children's_classic_books
	 */
	static ArrayList<String> classicsAuthors;
	
	/**
	 * list of all local children's authors
	 * list from https://forbeslibrary.org/readers/local-authors/
	 */
	static ArrayList<String> localAuthors;
	
	/**
	 * list of classics authors with cleaned names
	 */
	static ArrayList<String> classicsAuthorsCleaned;
	
	/**
	 * list of local authors with cleaned names
	 */
	static ArrayList<String> localAuthorsCleaned;
	
	/**
	 * reads files of classic and local authors into respective lists
	 * runs namesplitter on names in both lists to clean them
	 * @throws IOException
	 */
	public static void load() throws IOException {
		classicsAuthors = new ArrayList<String>();
		localAuthors = new ArrayList<String>();
		
		classicsAuthorsCleaned = new ArrayList<String>();
		localAuthorsCleaned = new ArrayList<String>();
		
		// Fields-list;
		//Number, Author
		try (Reader in = new FileReader("local.csv")) {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord record : records) {
				String author = record.get("Author");
				localAuthors.add(author);
			}
		}
		
		// Fields-list:
		//Title, Author
		try (Reader in = new FileReader("classics.csv")) {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord record : records) {
				String author = record.get("Author");
				classicsAuthors.add(author);
			}
		}
		
		for(String s: localAuthors) {
			NameSplitter splitName = new NameSplitter(s,false);
			localAuthorsCleaned.add(splitName.fullCleaned);
		}
		
		for(String s: classicsAuthors) {
			NameSplitter splitName = new NameSplitter(s,false);
			classicsAuthorsCleaned.add(splitName.fullCleaned);
		}
	}
	
	/**
	 * runs load
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		load();	
	}
	
	/**
	 * @return list of classic authors with cleaned names
	 */
	public static ArrayList<String> classics() {
		
		if (classicsAuthorsCleaned == null || classicsAuthorsCleaned.isEmpty()) {
			try {
				load();
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}

		return classicsAuthorsCleaned;

	}
	
	/**
	 * @return list of local authors with cleaned names
	 */
	public static ArrayList<String> local() {
		
		if (localAuthorsCleaned == null || localAuthorsCleaned.isEmpty()) {
			try {
				load();
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}

		return localAuthorsCleaned;
	}

}
