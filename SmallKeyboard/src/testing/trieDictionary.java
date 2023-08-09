package testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import sorting.Trie;

public class trieDictionary {
	private Trie tree = new Trie();
	private int cnt = 0;
	
	public void createDictionaryTrie() {
	
	    try (BufferedReader reader = new BufferedReader(new FileReader("dictionary"))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	cnt++;
	            String word = line.trim();
	            tree.insert(word);
	        }
	    } catch (IOException e) {
	    	System.out.println("Could not find dictionary.");
	        e.printStackTrace();
	    }
	}
	
	public Trie getTree() {
		return tree;
	}
	
	public int getCnt() {
		return cnt;
	}
}