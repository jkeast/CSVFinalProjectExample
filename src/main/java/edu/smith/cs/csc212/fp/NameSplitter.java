package edu.smith.cs.csc212.fp;


public class NameSplitter {
	/**
	 * name of author as was input
	 */
	String fullName;
	
	/**
	 * split last name of author
	 */
	String last;
	
	/**
	 * split first name of author
	 */
	String first;
	
	/**
	 * split first name of author cleaned to not include years, punctuation, etc
	 */
	String firstCleaned;
	
	/**
	 * combined cleaned first and last name
	 */
	String fullCleaned;
	
	/**
	 * true if author name is written as last, first
	 * false if author name is written as first last
	 */
	Boolean orderLast;

	/**
	 * splits full name into first and last
	 * 
	 * @param fullName- full name of author
	 * @param orderLast- true if name written as last, first
	 */
	public NameSplitter(String fullName, Boolean orderLast) {
		this.fullName=fullName;
		
		//if orderLast, assigns string before comma to last name
		//and string after comma to first
		if(orderLast) {
			String[] parts = fullName.split(", ");
			this.last = parts[0];
		
			if(parts.length>1) {
				this.first = parts[1];
			}else {
				first = null;
			}
		//if else, splits by space and assigns last part to last name
			//all else to first
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
		
		//cleans first name by removing anything after punctuation
		//this will remove years as well as many inconsistancies for the same author
		//although will not capture all names correctly (ie E.B. White becomes E White)
		//should make name of an author largely consistent for all iterations
		if(first != null) {
			String[] splitByPunct = first.split("\\p{Punct}");
			firstCleaned = splitByPunct[0];
		}else {
			firstCleaned = null;
		}
		
		//rewrites full name with split names
		//so will be consistent for all iterations regardless of source
		fullCleaned = firstCleaned + " " + last;

		
	}

}
