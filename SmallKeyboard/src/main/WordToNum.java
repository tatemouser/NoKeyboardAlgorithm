package main;

import java.util.HashMap;
import java.util.Map;

public class WordToNum {
	private HashMap<Character, Integer> table; 
	private String wordVals = "";
	
	public String convert(String words) {
		create();
		
		// Append number associated with letter. (a=1, w=2, e=3)
		for(int i = 0; i < words.length(); i++) wordVals += table.get(words.charAt(i));
		
		// Print table of keys and values.
		// for (Map.Entry<Character, Integer> entry : vals.entrySet()) System.out.println(entry.getKey() + " " + entry.getValue());
		
		//System.out.println(wordVals); 
		return wordVals;
	}
	
	
	/** 
	 * Adds number to each letter in a hashmap, referenenced in README.md.
	 * Stacks array represents how many letters equal each number up to 10. ***1 is q/a/z so the first number in stacks is 3***
	 * Once it loops through the amount of letters for a number it increments to the next set using the toTen index.
	 */
	public void create() {
		table = new HashMap<>(26);
		table.put(' ', 0);
		
		char[] letters = {'q','a','z','w','s','x','e','d','c','r','f','v','t','g','b','y','h','n','u','j','m','i','k','o','l','p'};
		int[] stacks = {3, 3, 3, 6, 6, 2, 2, 1};

		int index = 0;
		int toTen = 1;

		for(int stack: stacks) {
			for(int i = 0; i < stack; i++) {
				
				if(toTen == 5) toTen += 2;
				table.put(letters[index], toTen);
				index++;
			}
			toTen++;
		}
	}
}
