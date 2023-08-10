package bigramLanguageModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
// Credit: Dr.Greg Durrett (Created original outline for language model)
// Source: https://www.youtube.com/watch?v=7bf5EdQONTM

public class BigramMain {
  public static BigramLanguageModel lm;
  static Random r = new Random();
  
  private static boolean isScientificNotation(double value) {
      String stringValue = String.valueOf(value);
      return stringValue.toLowerCase().contains("e");
  }
  
  public static List<List<String>> readWikitext(String path, int maxLines) {
    System.out.println("Reading and training has started:  Using " + path);
    List<List<String>> lines = new ArrayList<List<String>>();
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(path));
      String line = reader.readLine();
      int numLines = 0;
      while (line != null && (maxLines < 0 || numLines < maxLines)) {
        if (line.trim().length() > 0) {
          List<String> thisLine = new ArrayList<>();
          thisLine.add(BigramLanguageModel.BEGIN_SYMBOL);
          String[] split = line.split(" ");
          for (String word : split) {
            if (word.trim().length() > 0) {
              thisLine.add(word);
            }
          }
          thisLine.add(BigramLanguageModel.END_SYMBOL);
          lines.add(thisLine);
          numLines += 1;
        }
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Training Complete: Read " + lines.size() + " lines");
    return lines;
  }

  /**
   * Checks the normalization of lm in a few different contexts
   * @param lm
   */
  public static void checkNormalization(BigramLanguageModel lm) {
	System.out.println("Testing Started-----------------------------------");
    checkNormalization(lm, "the");
    checkNormalization(lm, "asked");
    checkNormalization(lm, "did");
    System.out.println("Testing Complete-----------------------------------");
  }

  /**
   * Checks the normalization of the LM in the given prevWord context. Prints a warning message if the sum
   * of the probabilities of possible next words is significantly different from 1.
   * @param lm
   * @param prevWord
   */
  public static void checkNormalization(BigramLanguageModel lm, String prevWord) {
    double totalProb = 0.0;
    for (String word : lm.getVocabulary()) {
      totalProb += lm.getProbability(prevWord, word);
    }
    if (Math.abs(totalProb - 1.0) > 1e-3) {
      System.out.println("ERROR: normalization test failed: probabilities sum to " + totalProb + " rather than 1");
    } else {
      System.out.println("Valid! Sums to " + totalProb + " after context \"" + prevWord + "\"");
    }
  }
  

  /**
   * Prints the top 5 most likely next words after prevWord according to the lm
   * @param lm
   * @param prevWord
   */
  public static void evaluateLm(BigramLanguageModel lm, List<List<String>> data) {
    double totalLogProb = 0.0;
    int wordCounts = 0;
    Collection<String> vocab = lm.getVocabulary();
    int ignoredWords = 0;
    for (List<String> sent : data) {
      // Score everything from 1 until the end, ignoring BOS
      for (int i = 1; i < sent.size(); i++) {
        if (vocab.contains(sent.get(i))) {
          totalLogProb += -Math.log(lm.getProbability(sent.get(i - 1), sent.get(i)));
          wordCounts += 1;
        } else {
          ignoredWords += 1;
        }
      }
    }
    System.out.println("Perplexity: " + Math.exp(totalLogProb/wordCounts) + " with " + ignoredWords + " words ignored");
  }
  
  /**
   * Value printed closest to 1 is the word most likely to follow. Non-existing words will be labeled as "not-found".
   * @param lm
   */
  public static void exampleSet() {
	  /* 
		Original String - "innovative and" - Generated "innovative" "aye" "and" "ane".
		One selection for first word - innovative. 3 options for word two - aye and ane.
		Generates probability of each of the 3 options following the original word.
	  
	  System.out.println("This is the test results that I am looking for" + lm.getProbability("innovative", "aye"));
	  System.out.println("This is the test results that I am looking for" + lm.getProbability("innovative", "and"));
	  System.out.println("This is the test results that I am looking for" + lm.getProbability("innovative", "ane"));
	  System.out.println();
	  
	  // For checking word after.
	  System.out.println("This is the test results that I am looking for" + lm.getProbability("can", "you"));
	  System.out.println("This is the test results that I am looking for" + lm.getProbability("can", "hon"));
	  
	  System.out.println();
	  // For checking word prior.
	  System.out.println("This is the test results that I am looking for" + lm.getProbability("you", "can"));
	  System.out.println("This is the test results that I am looking for" + lm.getProbability("ppp", "can"));
	  
	  */
	  
	  /**
	   * Initial input options. Choosing and returning best option prior and after the word you.
	   * 
	   * (can bow) 
	   * (you) 
	   * (see sea sss sea ses ese)
	   */
	  
	  String[] leftAndMiddleWords = {"can", "can", "can", "bow", "bow", "bow"};
	  String[] middleAndRightWords = {"see", "sea", "sss", "sea", "ses", "ese"};

	  
	  double[] leftAndMiddleWord = new double[6];
	  double[] middleAndRightWord = new double[6];

	  leftAndMiddleWord[0] = lm.getProbability("can", "you");
	  middleAndRightWord[0] = lm.getProbability("you", "see");
	  
	  leftAndMiddleWord[1] = lm.getProbability("can", "you");
	  middleAndRightWord[1] = lm.getProbability("you", "sea");
	  
	  leftAndMiddleWord[2] = lm.getProbability("can", "you");
	  middleAndRightWord[2] = lm.getProbability("you", "sss");
	  
	  leftAndMiddleWord[3] = lm.getProbability("can", "bow");
	  middleAndRightWord[3] = lm.getProbability("bow", "sea");
	  
	  leftAndMiddleWord[4] = lm.getProbability("can", "bow");
	  middleAndRightWord[4] = lm.getProbability("bow", "ses");
	  
	  leftAndMiddleWord[5] = lm.getProbability("can", "bow");
	  middleAndRightWord[5] = lm.getProbability("bow", "ese");
	  
	  // Find best values 
	  double min1 = 100; // make absolute min
	  double min2 = 100;

	  int min1Index = 0;
	  int min2Index = 0;
	  for(int i = 0; i < leftAndMiddleWord.length; i++) {
		  // System.out.println(leftAndMiddleWord[i] + " " + min1);
		  if(leftAndMiddleWord[i] < min1 && leftAndMiddleWord[i] > .00001) {
			  min1Index = i;
			  min1 = leftAndMiddleWord[i];
		  }
		  
	  }
	  for(int i = 0; i < middleAndRightWord.length; i++) {
		  // System.out.println(middleAndRightWord[i] + " " + min2);
		  if(middleAndRightWord[i] < min2 && middleAndRightWord[i] > 0.00001) {
			  min2Index = i;
			  min2 = middleAndRightWord[i];
		  }
	  }

	  System.out.println("Result: " + leftAndMiddleWords[min1Index] + " you " + middleAndRightWords[min2Index]);
  }

  /**
   * Adds operation scores -> Find lowest score that has no scientific notation and return.
   */
  public static String getAfterWord(String base, ArrayList<String> options) {
	  double[] scores = new double[options.size()];
	  for(int i = 0; i < options.size(); i++) {
		  scores[i] = lm.getProbability(base, options.get(i));
		  //System.out.println(base + " " + options.get(i) + " " + scores[i]);
	  }
	  
	  double min = Double.MAX_VALUE;	  
	  int minIndex = 0;
	  
	  for(int i = 0; i < scores.length; i++) {
		  double score = scores[i];
		  // First condition compares last result, second condition rules out non-existing words. (Invalid scores)
          //if (score < min && score < 0.01 && !isScientificNotation(score)) {
          if (removeNine(score) && score < min && !isScientificNotation(score)) {
              min = score;
              minIndex = i;
          }
		  
	  }
	  return options.get(minIndex);
  }
  
  /**
   * Adds operation scores -> Find lowest score that has no scientific notation and return.
   */
  public static String getPriorWord(String base, ArrayList<String> options) {
	  double[] scores = new double[options.size()];
	  for(int i = 0; i < options.size(); i++) {
		  scores[i] = lm.getProbability(base, options.get(i));
	  }
	  
	  double min = Double.MAX_VALUE;
	  int minIndex = 0;
	  
	  for(int i = 0; i < scores.length; i++) {
		  double score = scores[i];
		  // First condition compares last result, second condition rules out non-existing words. (Invalid scores)
          //if (removeNine(score) && score < min && score < 0.1 && !isScientificNotation(score)) {
          if (removeNine(score) && score < min && !isScientificNotation(score)) {
              min = score;
              minIndex = i;
          } else {
          }
	  }
	  return options.get(minIndex);
  }
  
  public static boolean removeNine(double score) {
	    double value = score;
	    String valueStr = Double.toString(value);
	    if (valueStr.length() >= 6) { // Check for length 6, since index is 0-based
	        char digit5 = valueStr.charAt(5);

	        if (digit5 == '9') {
	            //System.out.println("has nine");
	            return false;
	        }
	    }
	    //System.out.println("no nine");
	    return true;
	}

  
  // Following two classes used in in LinkingMatchesAndBigram for comparing best results.
  public static double getPriorWordScore(String option, String base) {
	  return lm.getProbability(option, base);
  }
  public static double getAfterWordScore(String base, String option) {
	  return lm.getProbability(base, option);
  }
		
  /**
   * @param base center word.
   * @param options - index 0 holds left side word options, index 1 hold right side word options.
   * @return 
   */
  public static String getCenterWord(String base, ArrayList<ArrayList<String>> options) {
	  String result = "";
	  result += getPriorWord(base, options.get(0));
	  result += " " + base + " ";
	  result += getAfterWord(base, options.get(1));
	  return result;
  }
  
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