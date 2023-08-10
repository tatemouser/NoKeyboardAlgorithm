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
		// printLetterToNumber(); // Show what number each letter cooresponds to.
		
		// Word to number for testing only. Program would normally bypass this step and take in numbers.
		WordToNum words = new WordToNum();
		String input = words.convert("i want to"); 
		
		
		// Convert input -> Get combinations -> Verify combinations with dictionary -> Save results 
		findAndAddMatches(input);
		// Print Results.
		printWordMatches();
		
		//BigramMain start = new BigramMain();
		//BigramMain.exampleSet();
		// Run matches through bigram model to get final string.
		ArrayList<String> temp1 = new ArrayList<>();
		ArrayList<String> temp2 = new ArrayList<>();
		temp1.add("tt");
		temp1.add("it");
		temp1.add("tt");
		temp2.add("could");
		temp2.add("coooo");
		temp2.add("colll");
		wordMatches.clear();
		wordMatches.add(temp1);
		wordMatches.add(temp2);
		LinkingMatchesAndBigram findMatches = new LinkingMatchesAndBigram(wordMatches);
		String result = findMatches.getResult();
		System.out.println(result);
		//customBigramSet temp = new customBigramSet();
		//temp.run();
		
		// System.out.println(result);
	}
}
