package edu.smith.cs.csc212.fp;

public class Book {
	String author;
	String title;
	String call;
	String last;
	String first;
	String yearAcquired;
	int copyNumber;
	int checkOut;
	int avgCheckOut;
	int avgCheckOutWeighted;
	NameSplitter splitName;

	public Book(String title, String author, String call, String acquired) {
		this.title=title;
		this.author=author;
		this.call=call;
		this.copyNumber=1;
		this.checkOut = 0;
		
		NameSplitter splitName = new NameSplitter(author);
		
		this.last = splitName.getLast();
		this.first = splitName.getFirst();
		
		String[] dateAcq = acquired.split("/");
		
		this.yearAcquired = dateAcq[2];
	}
	
	
	public String getTitle(){
		return title;
	}
	
	public String getCall(){
		return call;
	}
	
	public void addCopy(){
		copyNumber+=1;
	}
	
	public void addTransaction(String patronType) {
		if(patronType.equals("SCCS Faculty/Staff")) {
			checkOut+=3;
		}else {
			checkOut++;
		}
	}
	
	public Integer getCheckOut() {
		return checkOut;
	}
	
	public Integer getYearAcq() {
		return Integer.parseInt(yearAcquired);
	}
	

	public void printBook() {
		System.out.println(title + " by " + author + " has " + copyNumber + " copies");
		System.out.println("It has " + checkOut + " total checkouts.");
	}
	
	public void printLast() {
		System.out.println(first);
		System.out.println(last);
		System.out.println(author);
	}
	
}
