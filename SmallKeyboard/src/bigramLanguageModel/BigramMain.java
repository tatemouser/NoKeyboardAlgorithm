package bigramLanguageModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
// replit https://www.youtube.com/watch?v=7bf5EdQONTM
public class BigramMain {

  static Random r = new Random();

  public static List<List<String>> readWikitext(String path, int maxLines) {
    System.out.println("Started reading from file " + path);
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
    System.out.println("Read " + lines.size() + " lines");
    return lines;
  }

  /**
   * Checks the normalization of lm in a few different contexts
   * @param lm
   */
  public static void checkNormalization(BigramLanguageModel lm) {
    checkNormalization(lm, "the");
    checkNormalization(lm, "asked");
    checkNormalization(lm, "did");
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
  public static void getWordProbabilites(BigramLanguageModel lm) {
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
	  
	  String[] leftAndMiddleWords = {"can", "can", "can", "can", "can", "can"};
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
		  //System.out.println(leftAndMiddleWord[i] + " " + min1);
		  if(leftAndMiddleWord[i] > min1 && leftAndMiddleWord[i] > 0.0000000001) {
			  min1Index = i;
			  min1 = leftAndMiddleWord[i];
		  }
		  
	  }
	  for(int i = 0; i < middleAndRightWord.length; i++) {
		  //System.out.println(middleAndRightWord[i] + " " + min2);
		  if(middleAndRightWord[i] > min2 && middleAndRightWord[i] > 0.0000000001) {
			  min2Index = i;
			  min2 = middleAndRightWord[i];
		  }
	  }

	  System.out.println("Result: " + leftAndMiddleWords[min1Index] + " you " + middleAndRightWords[min2Index]);
  }

  public void run(){
  //public void edit() {
    List<List<String>> trainLines = readWikitext("resources/wiki.train.tokens", -1);
    // List<List<String>> validLines = readWikitext("resources/wiki.valid.tokens", -1);
    BigramLanguageModel lm = BigramLanguageModel.estimate(trainLines);
    checkNormalization(lm);
    
    getWordProbabilites(lm);
  }
}