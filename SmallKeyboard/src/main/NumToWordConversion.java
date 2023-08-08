package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class converts numeric sequences into corresponding words based on a character table
 * and stores them in a trie data structure for efficient searching and retrieval.
 * It also includes a predefined word bank for testing and demonstration purposes.
 */
public class NumToWordConversion {
    private Map<Integer, List<Character>> table = new HashMap<>();
	private String[] wordBank;
	private Trie tree = new Trie();
	public NumToWordConversion() {
		this.wordBank = new String[] {"hello"};
	}
	
	/**
	 * Runs the program to generate and search words based on the given number.
	 * 
	 * @param number The number used to generate word variations. (hello = 73999)
	 */
	public void run(String number) {
		fillTable();
        // generateStrings(tree, "", number, 0);
        int wordsAdded = generateStrings(tree, "", number, 0);
        System.out.println("Total words added: " + wordsAdded);
        
        System.out.println(tree.search("nello"));
        System.out.println(tree.search("helll"));
	}
	
	public Trie getTree() {
		return tree;
	}

	/**
	 * Recursively generates all possible word variations and adds to table trie by 
	 * associating characters from the digit-to-character table with the digits in 
	 * the given number. This method explores character combinations for each digit, 
	 * building upon the current word, and inserts the generated words into the trie.
	 *
	 * @param trie The trie data structure to insert generated words into.
	 * @param current The current word being constructed.
	 * @param number The original number representing digit sequences.
	 * @param index The index of the current digit being processed in the number.
	 */
    private int generateStrings(Trie trie, String current, String number, int index) {
    	// If all digits are scanned, end recursion and add to trie.
        if (index == number.length()) {
            trie.insert(current);
            return 1;
        }

        int digit = Integer.parseInt(String.valueOf(number.charAt(index)));
        // Get characters associated with current digits (1 would be q/a/z)
        List<Character> characters = table.get(digit);

        int wordsAdded = 0;
        
        if (characters != null) {
            for (char ch : characters) {
            	// Recursively generate variations of words by appending a character, then incrementing digit and word count.
                wordsAdded += generateStrings(trie, current + ch, number, index + 1);
            }
        }
        
        return wordsAdded;
    }
	
	private void fillTable() {
		table.put(1, new ArrayList<>(Arrays.asList('q', 'a', 'z')));
		table.put(2, new ArrayList<>(Arrays.asList('w', 's', 'x')));
		table.put(3, new ArrayList<>(Arrays.asList('e', 'd', 'c')));
		table.put(4, new ArrayList<>(Arrays.asList('r', 'f', 'v','t','g','b')));
		table.put(7, new ArrayList<>(Arrays.asList('y', 'h', 'n','u','j','m')));
		table.put(8, new ArrayList<>(Arrays.asList('i', 'k')));
		table.put(9, new ArrayList<>(Arrays.asList('o', 'l')));
		table.put(10, new ArrayList<>(Arrays.asList('p')));
	}
}
