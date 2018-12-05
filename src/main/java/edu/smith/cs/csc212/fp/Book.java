package edu.smith.cs.csc212.fp;

public class Book {
	String author;
	String title;
	String call;
	int copyNumber;
	int checkOut;
	int avgCheckOut;
	int avgCheckOutWeighted;

	public Book(String title, String author, String call) {
		this.title=title;
		this.author=author;
		this.call=call;
		this.copyNumber=1;
		this.checkOut = 0;
		
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
	
	
	public void printCopies() {
		if(copyNumber>1) {
			System.out.println(title + " by " + author + " has " + copyNumber + " copies");
		}
	}
	
	public void printBook() {
		System.out.println(title + " by " + author + " has " + copyNumber + " copies");
		System.out.println("It has " + checkOut + "total checkouts.");
	}
	
}
