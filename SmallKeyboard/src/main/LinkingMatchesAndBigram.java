package main;

import java.util.ArrayList;

import bigramLanguageModel.*;

public class LinkingMatchesAndBigram {
	private static ArrayList<ArrayList<String>> wordMatches = new ArrayList<>();
	private static ArrayList<Double> baseWordScores = new ArrayList<>();
	private static boolean beenNormalized = false;

	public LinkingMatchesAndBigram(ArrayList<ArrayList<String>> passed) {
		LinkingMatchesAndBigram.wordMatches = passed;
	}
	
	/**
	 * Iterate first word combinations -> Find best 2nd word to match with EACH first word -> Compare all best matches.
	 * @return Best word match by comparing 2nd to 1st word combination.
	 */
	public String firstWord() {
		if(wordMatches.size() > 1) {
			// Gets one best match for each base word being the first word.
			for(String baseWord: wordMatches.get(0)) {
				// Finds best option for first word from second word set.
				String option = BigramMain.getPriorWord(baseWord, wordMatches.get(1));
				// Stores best pick for each first word.
				baseWordScores.add(BigramMain.getPriorWordScore(baseWord, option));
			}
		}
		// Compare top scores. Finds score that is smallest value / No repeating 9's / Standard notation 
		double min = 0;
		int bestScoreIndex = 0;
		for(int i = 0; i < baseWordScores.size(); i++) {	
			// Interpret bigram score results for most accurate return.
			if(noRepeatingNine(baseWordScores.get(i)) && baseWordScores.get(i) > min && !isScientificNotation(baseWordScores.get(i))) {
				bestScoreIndex = i;
			}
		}
		baseWordScores.clear();
		return wordMatches.get(0).get(bestScoreIndex);
	}
	

	/**
	 * Iterate word combinations -> Find best word from previous set to match with EACH word in solveForIndex set -> Compare all best matches.
	 * 
	 * @param solveForIndex - Index of original words - Will return one word from this set.
	 * @return Best word match by comparing previous set to original word set.
	 */
	public String nextWord(int solveForIndex) {
		// Holds index of main set scores found from previous set best scores.
		ArrayList<Integer> optionBestIndex = new ArrayList<>();
		// Gets one best match for each base word being the solveForIndex word.
		for(String baseWord: wordMatches.get(solveForIndex-1)) {
			// Finds best option for first word from previous word set.
			String option = BigramMain.getAfterWord(baseWord, wordMatches.get(solveForIndex));
			optionBestIndex.add(wordMatches.get(solveForIndex).indexOf(option));
			// Stores best pick.
			baseWordScores.add(BigramMain.getAfterWordScore(baseWord, option));
		}
		// Compare top scores. Finds score that is smallest value / No repeating 9's / Standard notation 
		double min = Double.MAX_VALUE;
		int bestScoreIndex = 0;
		for(int i = 0; i < baseWordScores.size(); i++) {
			// Interpret bigram score results for most accurate return.
			if(noRepeatingNine(baseWordScores.get(i)) && baseWordScores.get(i) < min && !isScientificNotation(baseWordScores.get(i))) {
				min = baseWordScores.get(i);
				bestScoreIndex = i;
			}
		}
		
		baseWordScores.clear();
		return wordMatches.get(solveForIndex).get(optionBestIndex.get(bestScoreIndex));
	}
	
	
	
	// Condition for interpretting birgram word combination scores. Often decimals with repeating 9's are invalid scores.
	public static boolean noRepeatingNine(double score) { 
	    double value = score;
	    String valueStr = Double.toString(value);
	    if (valueStr.length() >= 8) { 
	        char digit5 = valueStr.charAt(5);
	        char digit6 = valueStr.charAt(6);
	        char digit7 = valueStr.charAt(7);
	        
	        if (digit5 == '9' && digit6 == '9' && digit7 == '9') return false;
	    }
	    return true;
	}

	// Condition for interpretting birgram word combination scores. Ignores numbers with very long decimals.
	private static boolean isScientificNotation(double score) {
		String stringValue = String.valueOf(score);
	    return stringValue.toLowerCase().contains("e");
	 }
	
	
	
	
	// **Visual and example for finding best sequence of words in customBigramSet class.**
	public String getResult() {
		// Initialize and train model with data set. Must be done 1 time, no more or less.
		if(beenNormalized == false) {
			BigramMain bigramMain = new BigramMain();
			beenNormalized = true;
		}
		
		String finalResult = "";
		
		// Each index of wordMatches holds a words combinations. Only 1 iteration is needed to get final sentence.
		for(int i = 0; i < wordMatches.size(); i++) {
			// Find first word using 2nd set of combinations
			 if(i == 0) {
				finalResult += firstWord() + " ";
			 // Find other words by comparing current set to the prior set.
			 } else {
				int solveForIndex = i;
				finalResult += nextWord(solveForIndex) + " ";
			}
		}
		
		// BigramMain.printWordsNotFound(); // Words ignored by checking with dictionary.
		return finalResult;
	}
}
