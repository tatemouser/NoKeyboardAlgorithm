package testing;

import java.util.Scanner;

import main.WordToNum;

public class findBigramAccuracy {
	private static String[] currentSet;
	private static Scanner stdin = new Scanner(System.in);
	/**
	 *  10 sentences are converted to numbers, saving time of manual typing numbers for corresponding letters. 
	 */
	public String[] createSamples() {
		String[] sentenceSet = {"cars going fast are more likely to crash",
						"is eating out a bad way to save money",
						"students are meant to be tested",
						"cats like to eat fish for lunch", 
						"how do you say hello world",
						"the most random sentence",
						"running is so much fun",
						"the earth is very big",
						"what is your name", 
						"the truth is", 
						};
		assignSet(sentenceSet, sentenceSet.length); 
		for(int i = 0; i < sentenceSet.length; i++) {
			WordToNum words = new WordToNum();
			sentenceSet[i] = words.convert(sentenceSet[i]); 
		}
		return sentenceSet;
	}
	
	public void assignSet(String[] sentenceSet, int size) {
		currentSet = new String[size];
		for(int i = 0; i < sentenceSet.length; i++) {
			currentSet[i] = sentenceSet[i];
		}
	}
	
	// Letter accuracy and word accuracy
	public void compareAndPrint(String[] newSet) {
		int wordAccuracy = 0;	
		int letterAccuracy = 0;
		int wordCnt = 0;
		int letterCnt = 0;
		
		//TODO: Compare outputs to correct outputs (% accuracy / manual comparison)
		//TODO: Removes duplicates, Temporary fix to duplication of words not found
		for(int i = 0; i < newSet.length; i++) {
	        String[] oriSentence = currentSet[i].split(" ");
	        String[] newSentence = newSet[i].split(" ");
	        // TODO: Check missing lengths and short a's look to compare same length words
	        for(int j = 0; j < oriSentence.length; j++) {
	        	if(oriSentence[j].equals(newSentence[j])) {
	        		wordAccuracy++;
	        	}
	        	for(int k = 0; k < oriSentence[j].length(); k++) {
	        		if(oriSentence[j].charAt(k) == newSentence[j].charAt(k)) {
	        			letterAccuracy++;
	        		}
	        		letterCnt++;
	        	}
	        	wordCnt++;
	        }
		}
		int wordScore = (int) ((double) wordAccuracy / wordCnt * 100);
		int letterScore = (int) ((double) letterAccuracy / letterCnt * 100);

		System.out.println(String.format("Word Count: %4d\tCorrect Word Count: %4d\tWord Accuracy: %3d%%", 
											wordCnt, wordAccuracy, wordScore));
		System.out.println(String.format("Letter Count: %4d\tCorrect Letter Count: %4d\tLetter Accuracy: %3d%%", 
											letterCnt, letterAccuracy, letterScore));
		
		System.out.println("Enter: \"Show\" to see word sets compared.");
	    System.out.println("Enter: \"Skip\"");
		String show = stdin.next();
		if(show.equalsIgnoreCase("Show")) {
			for(int i = 0; i < currentSet.length; i++) {
				System.out.println(currentSet[i]);
				System.out.println(newSet[i]);
			}
		}
		//TODO: add .equalsIgnoreCase to all options.
	}
}
