package bigramLanguageModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BigramLanguageModel {

  public final Map<String,Map<String,Integer>> bigramCounts;
  private final Map<String,Integer> contextCounts; 
  // TODO: check if these actually need to be different
  private final Map<String,Integer> unigramCounts;
  private final int totalUnigramCount;
  public static final double LAMBDA = 0.9;
  public static final String BEGIN_SYMBOL = "<S>";
  public static final String END_SYMBOL = "</S>";
  public static String wordsNotFound = "";

  public BigramLanguageModel(Map<String,Map<String,Integer>> bigramCounts, Map<String,Integer> contextCounts,
                             Map<String,Integer> unigramCounts) {
    this.bigramCounts = bigramCounts;
    this.contextCounts = contextCounts;
    this.unigramCounts = unigramCounts;
    int totalUnigramCount = 0;
    for (Integer value : unigramCounts.values()) {
      totalUnigramCount += value;
    }
    this.totalUnigramCount = totalUnigramCount;
  }

  public List<String> getVocabulary() {
    return new ArrayList(unigramCounts.keySet());
  }

  private double getUnigramProbability(String word) {
	  if(unigramCounts.containsKey(word)) {
		  return ((double)unigramCounts.get(word))/totalUnigramCount;
	  } else {
		  wordsNotFound += word + " ";
		  return 1.0;
		  // May need a different return value.
	  }
  }

  public double getProbability(String prevWord, String nextWord) {
    int countAfterPrevWord = 0;
    if (this.bigramCounts.containsKey(prevWord)) {
      Map<String,Integer> countsAfterPrevWord = this.bigramCounts.get(prevWord);
      countAfterPrevWord = countsAfterPrevWord.getOrDefault(nextWord, 0);
      return LAMBDA * ((double)countAfterPrevWord)/this.contextCounts.get(prevWord) + (1 - LAMBDA) * getUnigramProbability(nextWord);
    } else {
      return (1 - LAMBDA) * getUnigramProbability(nextWord);
    }
  }

  public static BigramLanguageModel estimate(List<List<String>> trainSeqs) {
    Map<String,Map<String,Integer>> bigramCounts = new HashMap<String,Map<String,Integer>>();
    Map<String,Integer> unigramCounts = new HashMap<String,Integer>();
    Map<String,Integer> contextCounts = new HashMap<String,Integer>();
    for (List<String> trainSeq : trainSeqs) {
      for (int i = 1; i < trainSeq.size(); i++) {
        String prevWord = trainSeq.get(i-1);
        String currWord = trainSeq.get(i);
        if (!bigramCounts.containsKey(prevWord)) {
          bigramCounts.put(prevWord, new HashMap<String,Integer>());
        }
        Map<String,Integer> map = bigramCounts.get(prevWord);
        map.put(currWord, map.getOrDefault(currWord, 0) + 1);
        contextCounts.put(prevWord, contextCounts.getOrDefault(prevWord, 0) + 1);
        unigramCounts.put(currWord, unigramCounts.getOrDefault(currWord, 0) + 1);
      }
    }
    return new BigramLanguageModel(bigramCounts, contextCounts, unigramCounts);
  }
  
  public String getWordsNotFound() {
	  return wordsNotFound;
  }
}
