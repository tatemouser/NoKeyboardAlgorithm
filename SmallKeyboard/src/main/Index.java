package main;

import java.util.ArrayList;

import sorting.Trie;
import testing.*;

public class Index {
	private static Trie wordBank;
	private static String[] wordCombinations;
	private static ArrayList<String> wordMatches = new ArrayList<String>();
	
	public static void main(String[] args) {
		// printLetterToNumber();
		WordToNum words = new WordToNum();
		NumToWord number = new NumToWord();
		
		number.run("73999"); // = "Hello"
		// NewWordsTrieList.writeToCSV(number.getTree(), number.getTree().getRoot(), "", "trieWordData.csv");
		wordCombinations = NewWordsTrieList.writeToArray(number.getTree(), number.getTree().getRoot(),"");
		// System.out.println(wordCombinations.length);
		
		trieDictionary dict = new trieDictionary();
		dict.createDictionaryTrie();
		wordBank = dict.getTree();
		// System.out.println(dict.getCnt());
		
		
		findMatches();
		
		for(String foundWord: wordMatches) {
			System.out.println(foundWord);
		}
		
	}
	
	
	public static void findMatches() {
		for(String word: wordCombinations) {
			if(wordBank.search(word)) wordMatches.add(word);
		}
	}
	
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
