package main;

import java.util.ArrayList;
import java.util.Scanner;

import bigramLanguageModel.*;
import sorting.Trie;
import testing.*;

// TODO: Account for capitalization.
// TODO: Create UI display

public class Index {
	private static Trie wordBank;
	private static String[] wordCombinations;
	private static ArrayList<ArrayList<String>> wordMatches = new ArrayList<>();
	private static Scanner stdin = new Scanner(System.in);
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
		// TODO: Buggy when multiply detected
		if(wordCombinations.length > 0 && temp.size() == 0) {
			wordCombinations[0] += "*NOT FOUND*";
			temp.add(wordCombinations[0]);
			wordMatches.add(temp);
		}
		
		wordMatches.add(temp);
	}
	
	// Provides nothing to program / Dev visual
	public static void printLetterToNumber() {
		// Referencing the README keyboard image, each letter is converted and added to a string which is printed.
		WordToNum words = new WordToNum();
		System.out.println("qaz = " + words.convert("qaz"));
		System.out.println("wsx = " + words.convert("wsx"));
		System.out.println("edc = " + words.convert("edc"));
		System.out.println("rfvtgb = " + words.convert("rfvtgb"));
		System.out.println("yhnujm = " + words.convert("yhnujm"));
		System.out.println("ik = " + words.convert("ik"));
		System.out.println("ol = " + words.convert("ol"));
		System.out.println("p = " + words.convert("p"));

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
	
	public static void NormalMode() {		
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
	}

	//TODO: Move other test from normal mode to testing mode.
	public static void TestingMode() {
		// TODO: Build Custome Set / accuracy reading
		int mode = -1;
		while(mode!=1 && mode!=2 && mode!=3) {
			System.out.println("Enter 1-3");
			System.out.println("1: Build Custom Set:");
			System.out.println("2: Show Conversion Visual:");
			System.out.println("3: Check Accuracy of Language Model:");
			mode = stdin.nextInt();
		}
		if(mode == 1) {
			// Skip conversion, manual inputs for testing
			customBigramSet temp = new customBigramSet(); 
			temp.run();
		}
		if(mode == 2) {
			// Shows conversion from letters to numbers
			printLetterToNumber(); 
		}
		if(mode == 3) {
			
		}
		/*
		// Initialize model and testing set
		LinkingMatchesAndBigram findMatches = new LinkingMatchesAndBigram(wordMatches);
		findBigramAccuracy runner = new findBigramAccuracy();
		// Variables to be used
		String[] correctVals = runner.createSamples();
		int setSize = correctVals.length;
		String[] newVals = new String[setSize];
		// Variable Assignment
		for(int i = 0; i < setSize; i++) {
			System.out.println(correctVals[i]);
		}
		for(int i = 0; i < setSize; i++) {
			findAndAddMatches(correctVals[i]);
			String res = findMatches.getResult();
			newVals[i] = res;
			wordMatches.clear();
		}
		for(int i = 0; i < setSize; i++) {
			System.out.println(newVals[i]);
		}*/
	}
	
	public static void main(String[] args) {
		String mode = "";
		
		while(!mode.equals("Y") && !mode.equals("N")) {
		System.out.println("Type \"Y\" to enter normal mode");
		System.out.println("Type \"N\" to enter testing mode");
		mode = stdin.next();
		}
		
		if(mode.equals("Y")) {
			NormalMode();
		} else TestingMode();
	}
}
