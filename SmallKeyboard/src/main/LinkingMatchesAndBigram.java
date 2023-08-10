package main;

import java.util.ArrayList;

import bigramLanguageModel.*;

public class LinkingMatchesAndBigram {
	private static ArrayList<ArrayList<String>> wordMatches = new ArrayList<>();
	private static ArrayList<Double> baseWordScores = new ArrayList<>();
	private static ArrayList<String> options = new ArrayList<>();

	public LinkingMatchesAndBigram(ArrayList<ArrayList<String>> passed) {
		this.wordMatches = passed;
	}

	  private static boolean isScientificNotation(double value) {
	      String stringValue = String.valueOf(value);
	      return stringValue.toLowerCase().contains("e");
	  }
	
	public String firstWord() {
		if(wordMatches.size() > 1) {
			// If first word has multiple options, this picks a word then checks with the next options
			for(String baseWord: wordMatches.get(0)) {
				// Finds best option for first word from second word set.
				String option = BigramMain.getAfterWord(baseWord, wordMatches.get(1));
				// Stores best pick score
				baseWordScores.add(BigramMain.getAfterWordScore(baseWord, option));
			}
		}

		// Compare top scores to find smallest.
		double min = Double.MAX_VALUE;
		int bestScoreIndex = 0;
		for(int i = 0; i < baseWordScores.size(); i++) {	
			if(baseWordScores.get(i) < min && baseWordScores.get(i) < 0.01 && !isScientificNotation(baseWordScores.get(i))) {
				bestScoreIndex = i;
			}
		}
		baseWordScores.clear();
		return wordMatches.get(0).get(bestScoreIndex);
	}
	
	public String lastWord() {
		int size = wordMatches.size();
		// If last word has multiple options, this picks a word then checks with the next options
		for(String baseWord: wordMatches.get(size-2)) {
			// Finds best option for last word from second to last word set.
			String option = BigramMain.getPriorWord(baseWord, wordMatches.get(size-1));
			// Stores best pick score
			baseWordScores.add(BigramMain.getPriorWordScore(option, baseWord));
		}
		

		// Compare top scores to find smallest.
		double min = Double.MAX_VALUE;
		int bestScoreIndex = 0;
		for(int i = 0; i < baseWordScores.size(); i++) {	
			if(baseWordScores.get(i) < min && baseWordScores.get(i) < 0.01 && !isScientificNotation(baseWordScores.get(i))) {
				bestScoreIndex = i;
			}
		}
		baseWordScores.clear();
		return wordMatches.get(size-1).get(bestScoreIndex);
	}
	
	public String middleWord() {
		return null;
	}
	
	
	
	public String getResult() {
		// Initialize and train model with data set.
		BigramMain bigramMain = new BigramMain();
		//BigramMain.getAfterWord();
		//BigramMain.getPriorWord();
		//BigramMain.getCenterWord();
		
		/** EXAMPLE SET: 3 and 3 for two words.
		 *  thast tool 	
		 *  hatas bool	
		 *  thats cool  
		 *  
		 *  thast-tool				 	hatas-tool-bestScore-12  	thats-tool
		 *  thast-bool- 			 	hatas-bool				 	thats-bool
		 *  thast-cool- bestScore-10 	hatas-cool				 	thats-cool-bestScore-20
		 *  
		 *  Selects "thats cool" since its the best of the 3 scores.
		 */
		
		// Should add 9 scores to finalMatchingScores and 9 word combos to finalWordCombos
		// Takes highest score, uses index to get best word combo. Adds single word result.
		ArrayList<Double> finalMatchingScores = new ArrayList<>();
		ArrayList<String> finalWordCombos = new ArrayList<>();
		
		String finalResult = "";
		String result = ""; //delete this.
		
		for(int i = 0; i < wordMatches.size(); i++) {
			// Search Right Only (first set)
			 if(i == 0) {
				finalResult += firstWord() + " ";
				
			 // Search Left Only (last set)
			 } else if(i == wordMatches.size()-1) {
				finalResult += lastWord();
			}
			
		}
		
		// BigramMain.printWordsNotFound();
		return finalResult;
	}
}
