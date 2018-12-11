package edu.smith.cs.csc212.fp;

public class Book {
	String author;
	String title;
	String call;
	String last;
	String first;
	String full;
	int yearAcquired;
	int totCheckOut;
	int copyNumber;
	int FiveYcheckOut;
	int avgCheckOut;
	int avgCheckOutWeighted;
	int pastYearCheckOut;
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
		
		//this.last = splitName.getLast();
		//this.first = splitName.getFirst();
		
		this.full=splitName.fullCleaned;
		
		String[] dateAcq = acquired.split("/");
		
		this.yearAcquired = Integer.parseInt(dateAcq[2]);
	}
	
	
	
	
	public void addCopy(){
		copyNumber+=1;
		
		avgCheckOut = totCheckOut/copyNumber;
		avgCheckOutWeighted = avgCheckOut-1;
	}
	
	public void addTransaction(String patronType) {
		if(patronType.equals("SCCS Faculty/Staff")) {
			FiveYcheckOut+=3;
		}else {
			FiveYcheckOut++;
		}
		

	}

	public void printBook() {
		System.out.println(title + " by " + full + " has " + copyNumber + " copies");
		System.out.println("It has " + totCheckOut + " total checkouts.");
	}
	

	
}
