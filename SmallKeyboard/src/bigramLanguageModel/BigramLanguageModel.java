package bigramLanguageModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BigramLanguageModel {
 /**
  * The outer map (bigramCounts) maps a previous word to an inner map.
  *	The inner map maps a next word to the count of occurrences of that specific bigram.
  *
  * If you check to see if "apple pie" exist in the brigram. You would seach "apple" in the outer map, 
  * if there is an inner map associated with it, then you can look into that inner map to check if "pie" 
  * is one of the keys. If it is, you retrieve the count of "apple pie" occurrences.
  */
	
  // Store counts of bigrams: Map<PreviousWord, Map<NextWord, Count>>
  public final Map<String, Map<String, Integer>> bigramCounts;

  // Store counts of contexts (previous words)
  private final Map<String, Integer> contextCounts;

  // Store counts of individual words (unigrams)
  private final Map<String, Integer> unigramCounts;

  // Total count of all unigrams in the training data
  private final int totalUnigramCount;

  // Smoothing parameter for interpolation
  public static final double LAMBDA = 0.9;

  // Special symbols to mark the beginning and end of a sentence
  public static final String BEGIN_SYMBOL = "<S>";
  public static final String END_SYMBOL = "</S>";

  // Store words not found in the language model
  public static String wordsNotFound = "";

  // Constructor to initialize the bigram language model with counts
  public BigramLanguageModel(Map<String, Map<String, Integer>> bigramCounts,
                             Map<String, Integer> contextCounts,
                             Map<String, Integer> unigramCounts) {
    this.bigramCounts = bigramCounts;
    this.contextCounts = contextCounts;
    this.unigramCounts = unigramCounts;

    // Calculate total count of unigrams
    int totalUnigramCount = 0;
    for (Integer value : unigramCounts.values()) {
      totalUnigramCount += value;
    }
    this.totalUnigramCount = totalUnigramCount;
  }

  // Get the vocabulary (list of unique words) from the unigram counts
  public List<String> getVocabulary() {
    return new ArrayList<>(unigramCounts.keySet());
  }

  // Calculate the probability of a single word using unigram counts
  private double getUnigramProbability(String word) {
    if (unigramCounts.containsKey(word)) {
      return ((double) unigramCounts.get(word)) / totalUnigramCount;
    } else {
      wordsNotFound += word + " ";
      return 1.0; // May need a different return value depending on use case
    }
  }

  // Calculate the probability of a bigram using interpolation of bigram and unigram probabilities
  public double getProbability(String prevWord, String nextWord) {
    int countAfterPrevWord = 0;
    if (bigramCounts.containsKey(prevWord)) {
      Map<String, Integer> countsAfterPrevWord = bigramCounts.get(prevWord);
      countAfterPrevWord = countsAfterPrevWord.getOrDefault(nextWord, 0);
      return LAMBDA * ((double) countAfterPrevWord) / contextCounts.get(prevWord) +
             (1 - LAMBDA) * getUnigramProbability(nextWord);
    } else {
      return (1 - LAMBDA) * getUnigramProbability(nextWord);
    }
  }

  // Estimate a bigram language model from training sequences
  public static BigramLanguageModel estimate(List<List<String>> trainSeqs) {
    Map<String, Map<String, Integer>> bigramCounts = new HashMap<>();
    Map<String, Integer> unigramCounts = new HashMap<>();
    Map<String, Integer> contextCounts = new HashMap<>();
    
    // Iterate through training sequences to count bigrams, unigrams, and contexts
    for (List<String> trainSeq : trainSeqs) {
      for (int i = 1; i < trainSeq.size(); i++) {
        String prevWord = trainSeq.get(i - 1);
        String currWord = trainSeq.get(i);
        
        // Count bigrams
        if (!bigramCounts.containsKey(prevWord)) {
          bigramCounts.put(prevWord, new HashMap<>());
        }
        Map<String, Integer> map = bigramCounts.get(prevWord);
        map.put(currWord, map.getOrDefault(currWord, 0) + 1);
        
        // Count contexts and unigrams
        contextCounts.put(prevWord, contextCounts.getOrDefault(prevWord, 0) + 1);
        unigramCounts.put(currWord, unigramCounts.getOrDefault(currWord, 0) + 1);
      }
    }
    
    // Create and return a new BigramLanguageModel instance
    return new BigramLanguageModel(bigramCounts, contextCounts, unigramCounts);
  }
  
  // Get a string containing words not found in the language model
  public String getWordsNotFound() {
    return wordsNotFound;
  }
}
