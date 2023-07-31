package main;

import java.util.HashMap;
import java.util.Map;

public class Conversion {
	private  HashMap<Character, Integer> vals; 
	
	public void convert(String words) {
		create();
		String wordVals = "";
		for(int i = 0; i < words.length(); i++) {
			wordVals += vals.get(words.charAt(i));
		}
		
		System.out.println(wordVals);
	}
	
	/* Adds number to each letter in a hashmap, referenenced in README.md.
	 * Stacks array represents how many letters equal each number up to 10. ***1 is q/a/z so the first number in stacks is 3***
	 * Once it loops through the amount of letters for a number it increments to the next set using the toTen index.
	 */
	public void create() {
		vals = new HashMap<>(26);
		vals.put(' ', 0);
		
		char[] letters = {'q','a','z','w','s','x','e','d','c','r','f','v','t','g','b','y','h','n','u','j','m','i','k','o','l','p'};
		int[] stacks = {3, 3, 3, 6, 6, 2, 2, 1};

		int index = 0;
		int toTen = 1;

		for(int stack: stacks) {
			for(int i = 0; i < stack; i++) {
				
				if(toTen == 5) toTen += 2;
				vals.put(letters[index], toTen);
				index++;
			}
			toTen++;
		}
	}
}
