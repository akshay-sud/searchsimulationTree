package pa2;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter the website you wish to search: ");	// http://arstechnica.com/
		String website = scanner.nextLine();
		System.out.println("Please enter the keyword you wish to search for: ");
		String keyword = scanner.nextLine();
		
		Spider spider = new Spider(); 		// creates new instance of spider class (web crawler)
        spider.search(website, keyword);	// searches given site for instances of keyword
        
        Node[] array = new Node[30];	// creates initial array of 30 nodes
		bst tree = new bst();	// creates the bst
        
        List<String> urlReturn = spider.pagesToVisit;	// list of URLs in string form
        
        boolean exit = false;
        while (!exit) {
        	Scanner reader = new Scanner(System.in);
    	    System.out.println("");
    	    System.out.println("");
        	System.out.println("1. BST Menu");
        	System.out.println("2. Quicksort Menu");
        	System.out.println("3. Bucket Sort Menu");
        	System.out.println("4. Gradient Descent");
        	System.out.println("5. Exit this menu");
        	System.out.println("Please enter the menu option you want: ");
        	int menu1 = reader.nextInt();
        	
        	if (menu1 == 1) {	// bst menu
        		boolean submenu = false;
        		while (!submenu) {
	        		Scanner sc = new Scanner(System.in);
	        	    System.out.println("");
	        	    System.out.println("");
	            	System.out.println("Welcome to the BST Menu");
	            	System.out.println("1. Build BST");
	            	System.out.println("2. Print BST in reverse InOrder");
	            	System.out.println("3. Add a URL to BST");
	            	System.out.println("4. Delete a URL from BST");
	            	System.out.println("5. Search BST for a URL by score");
	            	System.out.println("6. Exit this menu");
	            	System.out.println("Please enter the menu option you want: ");
	            	int menu2 = sc.nextInt();
	            	if (menu2 == 1) {
	            		for (int i = 0; i < 30; i++) {	// fills array of nodes with new nodes made from the URLs 
	                    	pageRank temporary = new pageRank(urlReturn.get(i));
	                    	int useless = temporary.generateRank();
	                    	Node temp = new Node(temporary);
	                    	array[i] = temp;
	                    }
	            		for (int i = 0; i < 30; i++) {	// fills bst with nodes
	                    	tree.insert(array[i]);
	                    }
	            	}
	            	else if (menu2 == 2) {
	            		tree.sortedWalk(tree.root);	// prints tree in reverse order
	            	    tree.resetIndex();	// needed after every sortedWalk call
	            	}
	            	else if (menu2 == 3) {
	            		treeInsert(tree);
	            	}
	            	else if (menu2 == 4) {
	            		treeDelete(tree);
	            	}
	            	else if (menu2 == 5) {
	            		treeSearch(tree);
	            	}
	            	else {
	            		submenu = true;
	            	}
        		}
        	}	// end of bst menu
        	else if (menu1 == 2) {	// quicksort menu
        		pageRank [] a = new pageRank[30];	// creates unsorted array of URLs
        	    for (int i = 0; i < 30; i++) {
        	        pageRank temporary = new pageRank(urlReturn.get(i));
        	        int useless = temporary.generateRank();
        	        a[i] = temporary;
        	    }
        		
        		boolean submenu = false;
        		while (!submenu) {
	        		Scanner sc = new Scanner(System.in);
	        	    System.out.println("");
	        	    System.out.println("");
	            	System.out.println("1. Print array");
	            	System.out.println("2. Quicksort array");
	            	System.out.println("3. Exit this menu");
	            	System.out.println("Please enter the menu option you want: ");
	            	int menu2 = sc.nextInt();
	            	
	            	if (menu2 == 1) {
	            		int index = 1;
	            		for (int i = 29; i >= 0; i--) {	// prints quicksorted array in reverse 
        		        	System.out.println(index + ". " + "pageRank: " + a[i].rank + "   score: " + a[i].bribeRank + "    " + "URL: " + a[i].url);
        		        	index++;
        		        }
	            	}
	            	if (menu2 == 2) {
	            		quickSort(a, 0, 29);
	            	}
	            	else {
	            		submenu = true;
	            	}
        		}
        	}	// end of quicksort menu
        	else if (menu1 == 3) {	// bucketsort menu
        		pageRank [] a = new pageRank[30];	// creates unsorted array of URLs
        	    for (int i = 0; i < 30; i++) {
        	        pageRank temporary = new pageRank(urlReturn.get(i));
        	        int useless = temporary.generateRank();
        	        a[i] = temporary;
        	    }
        	    
        		boolean submenu = false;
        		while (!submenu) {
	        		Scanner sc = new Scanner(System.in);
	        	    System.out.println("");
	        	    System.out.println("");
	            	System.out.println("1. Print array");
	            	System.out.println("2. Bucket Sort");
	            	System.out.println("3. Exit this Menu");
	            	System.out.println("Please enter the menu option you want: ");
	            	int menu2 = sc.nextInt();
	            	
	            	if (menu2 == 1) {
	            		for (int i = 0; i < 30; i++) {
        		        	System.out.println((i + 1) + ". " + "score: " + a[i].rank + "   " + "URL: " + a[i].url);
        		        }	
	            	}
	            	else if (menu2 == 2) {
	            		int longest = stripURL(a);
	                    appendURL(a, longest);
	                    bucketSort(a, longest);
	            	}
	            	else {
	            		submenu = true;
	            	}
        		}
        	}	// end of bucketsort menu
        	else if (menu1 == 4) {	// gradient descent
        		batchGradientDescent(urlReturn);
        	}
        	else {	// exit
        		exit = true;
        	}
        } 
	}	// end main
	// gradient descent algorithm. see documentation for explanation
	public static void batchGradientDescent(List<String> list) {
		pageRank [] a = new pageRank[750];	// creates unsorted array of 750 URLs as training set
	    for (int i = 0; i < 750; i++) {
	        pageRank temporary = new pageRank(list.get(i));
	        int useless = temporary.generateRank();
	        a[i] = temporary;
	    }
	    
	    Random r = new Random();
	    double theta0 = 1.0 + (1000.0 - 1.0) * r.nextDouble();
	    double theta1 = 0.0 + (24.0) * r.nextDouble();
	    double temp0, temp1;
	    
	    double alpha = 1.0;
	    double change = 3.0;
	    double precisionVal = 3.0;
	    		
	    while (change > precisionVal) {
	    	double t1 = 0;
	    	double t2 = 0;
	    	
	    	// summation of expected values of y - actual values of y
	    	for (int i = 0; i < a.length; i++) {
	    		t1 = t1 + ((theta0 + (theta1 * (a[i].time))) - a[i].clicks);
	    	}
	    	
	    	temp0 = theta0 - (alpha * t1 * (0.00133333333));	// calculating the next b
	    
	    	for (int i = 0; i < a.length; i++) {
	    		t2 = t2 + (a[i].clicks * ((theta0 + (theta1 * (a[i].time))) - a[i].clicks));
	    	}
	    	
	    	temp1 = theta1 - (alpha * t2 * (0.00133333333));	// calculating the next m

	    	// determines when the algorithm isnt really changing too much anymore and needs to be stopped
	    	change = Math.abs(theta1 - temp1);
	    	
	    	// need to be updated at the same time for next iteration
	    	theta0 = temp0;
	    	theta1 = temp1;
	    }
	    
	    System.out.println("Success! The gradient descent algorithm has given us an equation that relates");
	    System.out.println("The time a webpage was published to the number of clicks its gotten");
	    System.out.println("The equation is: y = " + theta1 + "x + " + theta0);
	    System.out.println("In this equation, the number of clicks is y and the time published is x");
	    
	}	// end gradient descent
	// tree search
	public static void treeSearch(bst btree) {
		Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter the score of the URL you wish to search for: ");
	    int yuh = scanner.nextInt();
	    Node yaboy = btree.search(btree.root, yuh);
	    System.out.println("The URL is: " + yaboy.data.url);
	}
	// insert node to tree
	public static void treeInsert(bst btree) {
		Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the URL you want to add to the tree: ");
        String newURL = scanner.nextLine();
        pageRank temp = new pageRank(newURL);
        int useless = temp.generateRank();
        Node temporary = new Node(temp);
        btree.insert(temporary);
	}
	// delete node from tree
	public static void treeDelete(bst btree) {
		Scanner yeet = new Scanner(System.in);
        System.out.println("Enter the score of the URL you wish to delete: ");
        int gang = yeet.nextInt();
        btree.delete(btree.search(btree.root, gang));
	}
	// modified quicksort for array of pageRank
	public static void quickSort(pageRank[] A, int p, int r) {
		if (p < r) {
			int q = partition(A, p, r);
			quickSort(A, p, q - 1);
			quickSort(A, q + 1, r);
		}
	}
	// modified partition for array of pageRank
	public static int partition(pageRank[] A, int p, int r) {
		int x = A[r].rank;
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (A[j].rank <= x) {
				i++;
				pageRank temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		pageRank temp = A[i+1];
		A[i+1] = A[r];
		A[r] = temp;

		return i + 1;
	}
	// bucket sort for array of pageRank
	// makes 26 buckets, one for each letter of alphabet from a-z
	// first cuts off the "https://" from each url to sort by domain name
	public static void bucketSort(pageRank[] A, int longest) {
		
		pageRank[][] array = new pageRank[26][30];	// create array of pageRank arrays, 26 total for each letter
		
		int bucket = 0;	// keeps track of which bucket we're in
		for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {	// this loop goes through buckets 0 to 25 and checks if the first
			for (int i = 0; i < A.length; i++) {					// letter of each url belongs in the bucket or not
				char[] xx = A[i].url.toCharArray();
				if (xx[0] == alphabet) {
					array[bucket][i] = A[i];
				}
			}
			bucket++;
		}
		
		 System.out.println("");
	     System.out.println("");
	     System.out.println("");
	     
	     // appends empty spaces to end of each url to make uniform url length
	     for (int i = 0; i < 26; i++) {
	    	 for (int j = 0; j < 30; j++) {
	    		 if (array[i][j] == null) {
	    			 pageRank temp = new pageRank("");
	    			 array[i][j] = temp;
	    		 }
	    	 }
	    	 appendURL(array[i], longest);
	     }
		
	     // sorts the 26 buckets, sorts bucket if first element in bucket isnt null
		for (int i = longest - 1; i > -1; i--) {
			for (int j = 0; j < array.length; j++) {
				if (array[j][0] != null) {
					insertionSort(array[j], i);
				}
			}
		}
		
		// prints out domains sorted by alphabetical order
		System.out.println("The array of URLs sorted by site name: ");
	    for (int i = 0; i < 26; i++) {
	    	for (int j = 0; j < 30; j++) {
	    		if (array[i][j].url.charAt(0) != ' ') {
	    			System.out.println(array[i][j].url);
	    		}
	    	}
	    }

		 // refills A with sorted values in order
	    // when printed by rank, doesn't make a whole lot of sense
	    // its better to just take the print used before instead of print again
		int index = 0;
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 30; j++) {
				if (array[i][j].url.charAt(0) != ' ') {
					A[index] = array[i][j];
					index++;
				}
			}
		}
		
	}
	// modified insertion sort to sort each bucket in bucketsort
	public static void insertionSort(pageRank[] A, int index) {
		pageRank key;
		for (int j = 1; j < A.length; j++) {
			key = A[j];
			int i = j - 1;
			while ((i > -1) && (A[i].url.charAt(index) > key.url.charAt(index))) {
				A[i + 1] = A[i];
				i = i - 1;
			}
			A[i + 1] = key;
		}
	}
	// stripURL function
	public static int stripURL(pageRank[] A) {	// returns length of longest string
		int longest = 0;
		for (int i = 0; i < A.length; i++) { // strips "https://" from each url
			int index = A[i].url.indexOf("//") + 2;
		    A[i].url = A[i].url.substring(index);
			if (A[i].url.length() > longest) {
				longest = A[i].url.length();	// gets longest url length
			}
		}
		return longest;
	}
	// appendURL function
	public static void appendURL(pageRank[] A, int longest) {	// appends empty spaces to urls shorter than longest url
		for (int i = 0; i < A.length; i++) {
			if (A[i].url.length() < longest) {
				int l = longest - A[i].url.length();
				for (int j = 0; j < l; j++) {
					A[i].url = A[i].url + " ";
				}
			}
		}
	}
}
