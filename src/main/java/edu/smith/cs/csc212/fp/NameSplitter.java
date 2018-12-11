package edu.smith.cs.csc212.fp;


public class NameSplitter {
	String fullName;
	String last;
	String first;
	String firstCleaned;
	String fullCleaned;
	Boolean orderLast;

	
	public NameSplitter(String fullName, Boolean orderLast) {
		this.fullName=fullName;
		
		if(orderLast) {
			String[] parts = fullName.split(", ");
			this.last = parts[0];
		
			if(parts.length>1) {
				this.first = parts[1];
			}else {
				first = null;
			}
		}else {
			String[] parts = fullName.split(" ");
			if(parts.length==1) {
				this.first=null;
				this.last=parts[0];
			}else if(parts.length==2) {
				this.first = parts[0];
				this.last = parts[1];
			}else {
				int lastIndex;
				if(parts[parts.length-1].equals("Jr.")||parts[parts.length-1].equals("Sr.")) {
					lastIndex=parts.length-2;
					this.last = parts[parts.length-2];
				}else {
					lastIndex=parts.length-1;
				}
				this.last = parts[lastIndex];
				this.first = "";
				
				for(int i=0; i<lastIndex; i++) {
					this.first+=parts[i];
					if(i<lastIndex-1) {
						this.first+=" ";
					}
				}
			}
			
		}
		
		if(first != null) {
			String[] splitByPunct = first.split("\\p{Punct}");
			firstCleaned = splitByPunct[0];
		}else {
			firstCleaned = null;
		}
		
		fullCleaned = firstCleaned + " " + last;

		
	}
/*
	public String getLast() {
		//if name has comma in it, get string until comma
		//if no comma NEED TO FIGURE OUT --want to go from end??

		return last;
	}
	
	public String getFirst() {
		//if name has comma in it, get string after comma until first punctuation
		//if no comma, go until there is space OR Punctuation
		
		if(first != null) {
			String[] splitByPunct = first.split("\\p{Punct}");
			String cleaned = splitByPunct[0];
		
		
			return cleaned;
		}else {
			return null;
		}
	}*/
	
	

}
