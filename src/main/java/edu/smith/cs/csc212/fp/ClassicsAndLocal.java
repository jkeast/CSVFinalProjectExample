package edu.smith.cs.csc212.fp;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ClassicsAndLocal {
	static ArrayList<String> classicsAuthors;
	static ArrayList<String> localAuthors;
	
	static ArrayList<String> classicsAuthorsCleaned;
	static ArrayList<String> localAuthorsCleaned;
	
	public static void load() throws IOException {
		classicsAuthorsCleaned = new ArrayList<String>();
		localAuthorsCleaned = new ArrayList<String>();
		
		
		// Fields-list:
		//Title, Author
		ArrayList<String> classicsAuthors = new ArrayList<String>();
		ArrayList<String> localAuthors = new ArrayList<String>();
		
		// Fields-list;
		//[Name]
		try (Reader in = new FileReader("local.csv")) {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord record : records) {
				String author = record.get("Author");
				localAuthors.add(author);
				
			}
		}
		
		try (Reader in = new FileReader("classics.csv")) {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord record : records) {
				String author = record.get("Author");
				classicsAuthors.add(author);
			}
		}
		
		for(String s: localAuthors) {
			//System.out.println(s);
			NameSplitter splitName = new NameSplitter(s,false);
			//String cleaned = splitName.fullCleaned;
			localAuthorsCleaned.add(splitName.fullCleaned);
		}
		
		for(String s: classicsAuthors) {
			NameSplitter splitName = new NameSplitter(s,false);
			classicsAuthorsCleaned.add(splitName.fullCleaned);
		}
		


	}
	
	public static void main(String[] args) throws IOException {
		load();
		
	}
	
	
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
