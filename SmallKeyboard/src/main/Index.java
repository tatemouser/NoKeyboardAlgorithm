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
	
	public static void printWordMatches() {
		// Each row printed are the possible combinations available.
		for(ArrayList<String> temp: wordMatches) {
			String str = "";
			for(int i = 0; i < temp.size(); i++) {
				str += temp.get(i) + " ";
			}
			System.out.println(str);
		}
	}
	
	public static void findAndAddMatches(String input) {
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
	
	public static void main(String[] args) {
		// printLetterToNumber();
		// Converting from a word to number for the sake of testing. Typically the program would bypass this step and take in numbers.
		WordToNum words = new WordToNum();

		String input = words.convert("i want to"); 
		
		findAndAddMatches(input);
		printWordMatches();
		
		
		/*
		// Initialize and train model with data set.
		BigramMain bigramMain = new BigramMain();
		//BigramMain.getAfterWord();
		//BigramMain.getPriorWord();
		//BigramMain.getCenterWord();
		
		ArrayList<Double> totalSetMatchScoring = new ArrayList<>();
		String result = "";
		for(int i = 0; i < wordMatches.size(); i++) {
			// Search Right Only (first set)
			if(i == 0) {
				ArrayList<String> baseWord = wordMatches.get(0); // Assuming 1 option. 
				ArrayList<String> options = wordMatches.get(1);
				result += BigramMain.getAfterWord(baseWord.get(0), options) + " ";
				
			// Search Left Only (last set)
			} else if(i == wordMatches.size()-1) {
				ArrayList<String> baseWord = wordMatches.get(2); // Assuming 1 option. 
				ArrayList<String> options = wordMatches.get(1);
				result += BigramMain.getPriorWord(baseWord.get(0), options) + " ";
				
			// Cross Search 
			} else {
				ArrayList<String> baseWord = wordMatches.get(1); // Assuming 1 option.
				
				ArrayList<String> leftOptions = wordMatches.get(0);
				ArrayList<String> rightOptions = wordMatches.get(2);
				ArrayList<ArrayList<String>> options = new ArrayList<>();
				options.add(leftOptions);
				options.add(rightOptions);
				
				result += BigramMain.getCenterWord(baseWord.get(0), options);
			}
			
		}*/
		// BigramMain.printWordsNotFound();
		
		
	}
}
