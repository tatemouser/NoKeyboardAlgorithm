package main;

import java.util.ArrayList;

import bigramLanguageModel.*;
import sorting.Trie;
import testing.*;
// To-Do: Account for capitalization.
public class Index {
	private static Trie wordBank;
	private static String[] wordCombinations;
	private static ArrayList<ArrayList<String>> wordMatches = new ArrayList<>();

	public static void main(String[] args) {
		// printLetterToNumber();
		// Converting from a word to number for the sake of testing. Typically the program would bypass this step and take in numbers.
		WordToNum words = new WordToNum();
		BigramMain bigramMain = new BigramMain();
		
		// String input = words.convert("innovative and");
		//String input = words.convert("absolutely great"); 
		String input = words.convert("i want to"); 

		String currentWord = "";
		// Iterate input and add results to final output.
		for(int i = 0; i < input.length(); i++) {
		    char c = input.charAt(i);
		    
		    if (c != '0') currentWord += c;
		    
		    if (c == '0' || i == input.length() - 1) {
		        getWord(currentWord);
		        currentWord = "";
		    }
		}	
		
		// Each row printed are the possible combinations available.
		for(ArrayList<String> temp: wordMatches) {
			String str = "";
			for(int i = 0; i < temp.size(); i++) {
				str += temp.get(i) + " ";
			}
			System.out.println(str);
		}
		
		bigramMain.run();

	}
	
	
	
	// Creates trie of possible words -> Puts trie in array -> Creates dictionary -> Calls findMatches to compare dictionary with array 
	public static void getWord(String input) {
		NumToWord number = new NumToWord();
		number.run(input); 

		// NewWordsTrieList.writeToCSV(number.getTree(), number.getTree().getRoot(), "", "resources/trieWordData.csv");
		wordCombinations = NewWordsTrieList.writeToArray(number.getTree(), number.getTree().getRoot(),"");
		// System.out.println(wordCombinations.length);
		
		trieDictionary dict = new trieDictionary();
		dict.createDictionaryTrie();
		wordBank = dict.getTree();
		// System.out.println(dict.getCnt());
				
		findMatches();
	}
	
	// Compares array of combinations to dictionary. The wordMatches array is used to hold the results.
	public static void findMatches() {
		ArrayList<String> temp = new ArrayList<>();
		for(String word: wordCombinations) {
			if(wordBank.search(word))  {
				temp.add(word);
			}
		}
		if(wordCombinations.length > 0 && temp.size() == 0) {
			wordCombinations[0] += "\t *WARNING: No word found in dictionary*";
			temp.add(wordCombinations[0]);
			wordMatches.add(temp);
		}
		wordMatches.add(temp);
	}
	
	// Provides nothing to program / Dev visualizer
	public static void printLetterToNumber() {
		// Referencing the README keyboard image, each letter is converted and added to a string which is printed.
		WordToNum words = new WordToNum();
		words.convert("qaz"); 
		words.convert("wsx"); 
		words.convert("edc"); 
		words.convert("rfvtgb"); 
		words.convert("yhnujm"); 
		words.convert("ik"); 
		words.convert("ol"); 
		words.convert("p");
	}
}
