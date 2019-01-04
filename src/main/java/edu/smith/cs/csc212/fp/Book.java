package edu.smith.cs.csc212.fp;

public class Book {
	/**
	 * full name of author, written in last,first format
	 */
	String author;
	
	/**
	 * title of book
	 */
	String title;
	
	/**
	 * call number
	 */
	String call;
	
	/**
	 * last name of author
	 */
	String last;
	
	/**
	 * first name of author (may also include middle)
	 */
	String first;
	
	/**
	 * full name of author, in first last format
	 */
	String full;
	
	/**
	 * year book was acquired by library
	 */
	int yearAcquired;
	
	/**
	 * total number of check outs since acquired
	 */
	int totCheckOut;
	
	/**
	 * how many copies of book there are in collection
	 */
	int copyNumber;
	
	/**
	 * total number of check outs in the past five years
	 */
	int FiveYcheckOut;
	
	/**
	 * average total checkout over number of copies
	 */
	int avgCheckOut;
	
	/**
	 * average total checkout weighted 
	 * so books with multiple copies have less weight
	 */
	int avgCheckOutWeighted;
	
	/**
	 * number of check outs in past year
	 */
	int pastYearCheckOut;
	
	/**
	 * iteration of NameSplitter class to get author first and last names
	 */
	NameSplitter splitName;
	

	public Book(String title, String author, String call, String acquired, String totalCheckOut, int pastYearCheckOut) {
		this.title=title;
		this.author=author;
		this.call=call;
		this.totCheckOut=Integer.parseInt(totalCheckOut);
		this.copyNumber=1;
		this.FiveYcheckOut = 0;
		this.avgCheckOut = totCheckOut;
		this.avgCheckOutWeighted = totCheckOut;
		this.pastYearCheckOut = pastYearCheckOut;
		
		NameSplitter splitName = new NameSplitter(author,true);

		this.full=splitName.fullCleaned;
		
		String[] dateAcq = acquired.split("/");
		
		this.yearAcquired = Integer.parseInt(dateAcq[2]);
	}
	
	
	
	/**
	 * adds copy of book when another is found
	 */
	public void addCopy(){
		copyNumber+=1;
		
		avgCheckOut = totCheckOut/copyNumber;
		avgCheckOutWeighted = avgCheckOut-1;
	}
	
	/**
	 * adds to checkout count
	 * weights teacher checkouts
	 * @param patronType-type of patron who checked out book
	 * ex: campus school class year, faculty/staff, Smith College student etc
	 */
	public void addTransaction(String patronType) {
		if(patronType.equals("SCCS Faculty/Staff")) {
			FiveYcheckOut+=3;
		}else {
			FiveYcheckOut++;
		}
		

	}	

	
}
