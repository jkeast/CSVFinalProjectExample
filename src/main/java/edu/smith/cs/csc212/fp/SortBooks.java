package edu.smith.cs.csc212.fp;

import java.util.ArrayList;
import java.util.List;



public class SortBooks {
	
	public static ArrayList<Book> sortedMergeSort(ArrayList<Book> list1, ArrayList<Book> list2){
		ArrayList<Book> sorted = new ArrayList<Book>();
		
		//runs until both lists are empty
		while((list1.size()>0) || (list2.size()>0)) {

			//if one list empty, add rest of other list to back of sorted
			if(list1.size()==0) {
				for(Book b: list2) {
					sorted.add(b);
				}
				list2 = new ArrayList<Book>();
			}else if (list2.size()==0) {
				for(Book b: list1) {
					sorted.add(b);

				}
				list1 = new ArrayList<Book>();
				
			//looks at first items in both lists
			//and adds smallest to sorted list
			//(removing from original list)
				//repeat until one list entirely empty
			}else {

				if(list1.get(0).avgCheckOutWeighted<= list2.get(0).avgCheckOutWeighted) {		

					sorted.add(list1.get(0));
					list1.remove(0);

				}else {
					sorted.add(list2.get(0));
					list2.remove(0);
				}
			}


	}
		return sorted;
	}
	
	public static List<Book> merge(List<Book> list){
		
		if(list.size()==1) {
			return list;
		}
		
		//breaks list down until can't anymore
		ArrayList<Book> list1 = new ArrayList<>(merge(list.subList(0,list.size()/2)));
		ArrayList<Book> list2 = new ArrayList<>(merge(list.subList(list.size()/2, list.size())));
		
		//merge sorts broken up lists
		list = sortedMergeSort(list1, list2);
		
		return(list);
	}



		/**
		 * 
		 * @param list- list to be sorted
		 * @return sorted list
		 */
	public static ArrayList<Book> recursionMergeSort(ArrayList<Book> list){
		List<Book> sorted = new ArrayList<Book>();

		sorted = merge(list);
			
		return (ArrayList<Book>) sorted;
			
	}


	}
