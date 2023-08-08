package testing;

import java.io.FileWriter;
import java.io.IOException;

import main.*;

public class TrieToCSV {	
	/**
	 * Prints all possibilites created with digits to csv file for research use.
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
}
