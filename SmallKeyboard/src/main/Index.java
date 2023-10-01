package main;

import java.util.ArrayList;

import bigramLanguageModel.*;
import sorting.Trie;
import testing.*;

// TODO: Account for capitalization.
// TODO: Create UI display

public class Index {
	private static Trie wordBank;
	private static String[] wordCombinations;
	private static ArrayList<ArrayList<String>> wordMatches = new ArrayList<>();
	
	/** 
	 * Splits word by 0's (representing spaces) -> Locate then send word to getWord() to find its combinations.
	 * @param input - numbers inputted 
	 */
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
	
	/**
	 * Create trie of possible words -> Adds all trie elements to array -> Initialize a dictionary trie -> Passes on array to findMatches(); 
	 * @param input - Single word found in findAndAddMatches()
	 */
	public static void getWord(String input) {
		NumToWord number = new NumToWord();
		number.run(input); 

		// NewWordsTrieList.writeToCSV(number.getTree(), number.getTree().getRoot(), "", "resources/trieWordData.csv");
		wordCombinations = NewWordsTrieList.writeToArray(number.getTree(), number.getTree().getRoot(),"");
		
		trieDictionary dict = new trieDictionary();
		dict.createDictionaryTrie();
		wordBank = dict.getTree();
		// System.out.println(dict.getCnt());
				
		findMatches();
	}
	
	// Iterates array -> Stores matches in temp array -> Add array of valid combinations to wordMatches.
	public static void findMatches() {
		ArrayList<String> temp = new ArrayList<>();
		for(String word: wordCombinations) 
			if(wordBank.search(word)) temp.add(word);
		
		// Auto-Fill if no real words found.
		if(wordCombinations.length > 0 && temp.size() == 0) {
			wordCombinations[0] += "\t *WARNING: No word found in dictionary*";
			temp.add(wordCombinations[0]);
			wordMatches.add(temp);
		}
		
		wordMatches.add(temp);
	}
	
	// Provides nothing to program / Dev visual
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
	
	// Each row printed represents combinations for word number.
	public static void printWordMatches() {
		int cnt = 1;
		for(ArrayList<String> temp: wordMatches) {
			String str = "Word " + cnt + " Options: ";
			for(int i = 0; i < temp.size(); i++) {
				str += temp.get(i) + " ";
			}
			System.out.println(str);
			cnt++;
		}
		System.out.println("-------------------------------------------------");
	}
	
	
	
	public static void main(String[] args) {
		// printLetterToNumber(); // Dev visual
		
		// Word to number for testing only. Program would normally bypass this step and take in numbers. ("Hello" starts program with 73999)
		WordToNum words = new WordToNum();
		String input = words.convert("how do you say hello world");
		// String input = words.convert("some issues have not been fixed so be careful using large sentences like this");
		// String input = words.convert("the first thing i would do to correct this would be to create a larger dictionary");
		
		// Convert input -> Get word combinations -> Verify combinations with dictionary -> Save matched results to "wordMatches" ArrayList.
		findAndAddMatches(input);
		
		// Iterate wordMatches -> Test combinations with bigram -> Store best match scores -> Combine and save sentence.
		LinkingMatchesAndBigram findMatches = new LinkingMatchesAndBigram(wordMatches);
		String result = findMatches.getResult();
		
		System.out.println("Input: " + input);
		printWordMatches();
		
		System.out.println("Best Result: " + result);
		
		
		//customBigramSet temp = new customBigramSet(); // Skip conversion, manual inputs for testing
		//temp.run();
		
		// Testing bigram predictions with manual input and output checking.
		// TODO:
		//findModelAccuracy(); 		
	}

	public static void findModelAccuracy() {
		findBigramAccuracy runner = new findBigramAccuracy();
		//runner.runDiagnostics();
		String[] temp = runner.createSamples();
		for(int i = 0; i < temp.length; i++) {
			System.out.println(temp[i]);
		}
	}
}
