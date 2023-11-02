package bigramLanguageModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BigramMain {
	public static BigramLanguageModel lm; // Static instance of the BigramLanguageModel class for language modeling
	static Random r = new Random(); // Static instance of the Random class for generating random numbers
	public static ArrayList<Double> scoresForGUI = new ArrayList<Double>();
	
	// Reads and processes wikitext data from a file
	public static List<List<String>> readWikitext(String path, int maxLines) {
	    System.out.println("Reading and training has started: Using " + path);
	    List<List<String>> lines = new ArrayList<List<String>>(); // List to store lines of tokenized words
	    
	    BufferedReader reader;
	    try {
	        reader = new BufferedReader(new FileReader(path)); // Open a reader for the specified file
	        String line = reader.readLine(); // Read the first line from the file
	        int numLines = 0; // Count of processed lines
	        
	        // Read lines from the file until reaching the maximum limit (maxLines) or end of file
	        while (line != null && (maxLines < 0 || numLines < maxLines)) {
	            if (line.trim().length() > 0) { // Check if the line is not empty
	                List<String> thisLine = new ArrayList<>(); // List to store tokens in the current line
	                thisLine.add(BigramLanguageModel.BEGIN_SYMBOL); // Add a special BEGIN_SYMBOL at the beginning (symbol- </S>)

	                // Split the line into words based on spaces and process each word
	                String[] split = line.split(" ");
	                for (String word : split) {
	                    if (word.trim().length() > 0) { // Check if the word is not empty
	                        thisLine.add(word); // Add the word to the current line's tokens
	                    }
	                }

	                thisLine.add(BigramLanguageModel.END_SYMBOL); // Add a special END_SYMBOL at the end (symbol- </S>)
	                lines.add(thisLine); // Add the processed line to the list
	                numLines += 1; // Increment the line count
	            }
	            line = reader.readLine(); // Read the next line from the file
	        }
	        reader.close(); // Close the reader after reading all lines
	    } catch (IOException e) {
	        e.printStackTrace(); // Print the stack trace in case of an IOException
	    }
	    
	    System.out.println("Training Complete: Read " + lines.size() + " lines");
	    return lines; // Return the list of tokenized lines from the wikitext
	}
   
	//Checks the normalization of lm in a few different contexts. 
	public static void checkNormalization(BigramLanguageModel lm) {
		System.out.println("--------------------------------------------------");
		System.out.println("Testing Started");

		checkNormalization(lm, "the");
		checkNormalization(lm, "asked");
		checkNormalization(lm, "did");
		System.out.println("Testing Complete");
		System.out.println("--------------------------------------------------");
	}

  /**
   * Checks the normalization of the LM in the given prevWord context. Prints a warning message if the sum
   * of the probabilities of possible next words is significantly different from 1.
   * @param lm
   * @param prevWord
   */
	public static void checkNormalization(BigramLanguageModel lm, String prevWord) {
		double totalProb = 0.0;
	    // Iterate through the vocabulary of the language model.
		for (String word : lm.getVocabulary()) {
			// Calculate the probability for the current word given the context.
			totalProb += lm.getProbability(prevWord, word);
		}    
		// Compare the sum of probabilities to 1.0 with a tolerance threshold
		if (Math.abs(totalProb - 1.0) > 1e-3) {
			System.out.println("ERROR: normalization test failed: probabilities sum to " + totalProb + " rather than 1");
		} else {
			System.out.println("Valid! Sums to " + totalProb + " after context \"" + prevWord + "\"");
		}
	}
  
  
	
	

   /**
	* Get probabilities of base word to all options -> Choose best score.
	* @param base - Original set.
	* @param options - Set following original.
	* @return Score with no repeating 9's / Standard notation / smallest value.
	*/
	public static String getAfterWord(String base, ArrayList<String> options) {
		double[] scores = new double[options.size()];
		for(int i = 0; i < options.size(); i++) {
			scores[i] = lm.getProbability(base, options.get(i));
		}
	  
		double min = Double.MAX_VALUE;	  
		int minIndex = 0;
	  
		// Find best score.
		for(int i = 0; i < scores.length; i++) {
			double score = scores[i];
			if (removeNine(score) && score < min && !isScientificNotation(score)) {
				min = score;
				minIndex = i;
			}
		}
		return options.get(minIndex);
	}
  
   /**
	* Get probabilities of base word to all options -> Choose best score.
	* @param base - Original set.
	* @param options - Set prior to original.
	* @return Score with no repeating 9's / Standard notation / smallest value.
	*/
	public static String getPriorWord(String base, ArrayList<String> options) {
		double[] scores = new double[options.size()];
		for(int i = 0; i < options.size(); i++) {
			scores[i] = lm.getProbability(base, options.get(i));
		}
	  
		double min = Double.MAX_VALUE;
		int minIndex = 0;
	  
		// Find best score.
		for(int i = 0; i < scores.length; i++) {
			double score = scores[i];
			if (removeNine(score) && score < min && !isScientificNotation(score)) {
				min = score;
				minIndex = i;
			}
		}
		return options.get(minIndex);
	}
  
	
	// Condition for interpretting birgram word combination scores. Often decimals with repeating 9's are invalid scores.
	public static boolean removeNine(double score) {
		double value = score;
	    String valueStr = Double.toString(value);
	    if (valueStr.length() >= 6) { 
	        char digit5 = valueStr.charAt(5);
	        char digit6 = valueStr.charAt(6);
	        char digit7 = valueStr.charAt(7);

	        if (digit5 == '9' && digit6 == '9' && digit7 == '9') {
	            return false;
	        }
	    }
	    return true;
	}
	
	// Condition for interpretting birgram word combination scores. Ignores numbers with very long decimals.
	private static boolean isScientificNotation(double value) {
		String stringValue = String.valueOf(value);
		return stringValue.toLowerCase().contains("e");
	}

  
	
	
	
	// Following two classes used in in LinkingMatchesAndBigram for comparing best results.
	public static double getPriorWordScore(String option, String base) {
		Double val = lm.getProbability(option, base);
		scoresForGUI.add(val);
		System.out.println(1 + " " + option + " " + base);
		return val;
	  //return lm.getProbability(option, base);
	}
	public static double getAfterWordScore(String base, String option) {
		Double val = lm.getProbability(base, option);
		scoresForGUI.add(val);
		//return lm.getProbability(base, option);
		System.out.println(2 + " " + base + " " +option);
		return val;
	}
	
	// Used in LinkingMatchesAndBigram class for testing.
	public static void printWordsNotFound() {
		String wordsNotFound = lm.getWordsNotFound();
		System.out.println("Words not found in the training data: " + wordsNotFound);
	}

	
	
  
  // Initialize and train model then check accuracy.
  public BigramMain(){
	  
	  List<List<String>> trainLines = readWikitext("resources/wiki.train.tokens", -1);
	  // List<List<String>> validLines = readWikitext("resources/wiki.valid.tokens", -1);
	  lm = BigramLanguageModel.estimate(trainLines);
	  checkNormalization(lm);
  }
}