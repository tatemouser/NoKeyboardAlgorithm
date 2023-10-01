package testing;

import main.WordToNum;

public class findBigramAccuracy {
	/**
	 *  10 sentences are converted to numbers, saving time of manual typing numbers for corresponding letters. 
	 */
	public String[] createSamples() {
		String[] sentenceSet = {"cars going fast are more likely to crash",
								"is eating out a bad way to save money",
								"cats like to sleep all the time", 
								"programs are meant to be tested",
								"how do you say hello world",
								"the most random sentence",
								"running is so much fun",
								"the earth is spinning",
								"what is your name", 
								"the truth is", 
								};
		for(int i = 0; i < sentenceSet.length; i++) {
			WordToNum words = new WordToNum();
			sentenceSet[i] = words.convert(sentenceSet[i]); 
		}
		return sentenceSet;
	}
	
	public void runSamples() {
		
	}
	
	public void runDiagnostics() {
		// TODO: Run inputs through application
		// TODO: Compare outputs to correct outputs (% accuracy / manual comparison)
	}
}
