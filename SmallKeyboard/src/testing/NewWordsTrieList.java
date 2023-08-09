package testing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.*;
import sorting.Trie;
import sorting.TrieNode;

public class NewWordsTrieList {	
	/**
	 * Takes all word possibilites created with digits and writes over csv file for analyzing results.
	 * 
     * @param trie The Trie data structure containing the words and character nodes.
     * @param node The current TrieNode being processed during traversal.
     * @param current The current word being constructed during traversal.
     * @param filename The name of the CSV file to which the words will be written.
	 */
    public static void writeToCSV(Trie trie, TrieNode node, String current, String filename) {
        if (node.isEndOfWord) {
            try (FileWriter writer = new FileWriter(filename, true)) {
                writer.append(current);
                writer.append('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (char ch : node.children.keySet()) {
            writeToCSV(trie, node.children.get(ch), current + ch, filename);
        }
    }
    
    /**
     * Takes all word possibilites created with digits and adds to array.
     * 
     * @param trie The Trie data structure containing the words and character nodes.
     * @param node The current TrieNode being processed during traversal.
     * @param current The current word being constructed during traversal.
     * @return String[] holds trie words, used for comparing with dictionary
     */
    public static String[] writeToArray(Trie trie, TrieNode node, String current) {
        List<String> wordsList = new ArrayList<>(); 

        if (node.isEndOfWord) wordsList.add(current); 

        for (char ch : node.children.keySet()) {
            String[] childWords = writeToArray(trie, node.children.get(ch), current + ch); 
            // Add words from the child node to the current list
            for (String word : childWords) {
                wordsList.add(word);
            }
        }

        return wordsList.toArray(new String[0]);
    }
}
