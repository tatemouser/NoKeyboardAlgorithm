package main;

import testing.*;

public class Index {
	public static void main(String[] args) {
		// Testing
	/*	WordToNumConversion words = new WordToNumConversion();
		words.convert("qaz"); 
		words.convert("wsx"); 
		words.convert("edc"); 
		words.convert("rfvtgb"); 
		words.convert("yhnujm"); 
		words.convert("ik"); 
		words.convert("ol"); 
		words.convert("p"); */
		
		WordToNumConversion words = new WordToNumConversion();
		NumToWordConversion number = new NumToWordConversion();
		
		number.run("73999"); // hello is the correct case in the tree
		
		TrieToCSV.writeToCSV(number.getTree(), number.getTree().getRoot(), "", "trieWordData.csv");
		
	/*	hello
		73999
		Level 1: 6 options
		Level 2: 3 options for each of the 6 options from Level 1 = 6 * 3 = 18 options
		Level 3: 3 options for each of the 18 options from Level 2 = 18 * 3 = 54 options
		Level 4: 3 options for each of the 54 options from Level 3 = 54 * 3 = 162 options
		Level 5: 3 options for each of the 162 options from Level 4 = 162 * 3 = 486 options
		1 of the 486 will be hello with the correct letters. */
	}
}
