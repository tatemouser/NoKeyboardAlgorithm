package testing;

import java.util.ArrayList;

import bigramLanguageModel.*;
import main.LinkingMatchesAndBigram;

public class customBigramSet {
	/** EXAMPLE SET: 3 and 3 for two words. (input combinations taken from NumToWord class)
	 *  1. thast tool 	
	 *  2. hatas bool	
	 *  3. thats cool  
	 *  
	 *  1. thast-tool				 	2. hatas-tool-bestScore-12  	3. thats-tool
	 *  1. thast-bool- 			 		2. hatas-bool				 	3. thats-bool
	 *  1. thast-cool- bestScore-10 	2. hatas-cool				 	3. thats-cool-bestScore-20
	 *  
	 *  Generates 9 total scores. -> Collects best of each base word -> Selects "thats cool" since its the best of the 3 final scores.
	 */
	
	public void run() {
		ArrayList<ArrayList<String>> wordMatches = new ArrayList<>();
		
		ArrayList<String> temp1 = new ArrayList<>();
		ArrayList<String> temp2 = new ArrayList<>();
		ArrayList<String> temp3 = new ArrayList<>();
		//////Set 1//////
		temp1.add("cat");
		temp1.add("cna");
		temp1.add("can"); 
		//////Set 2//////
		temp2.add("yuu"); 
		temp2.add("yoo");
		temp2.add("you"); 
		//////Set 3//////
		temp3.add("see"); 
		temp3.add("sea");
		temp3.add("saa");
		wordMatches.clear();
		wordMatches.add(temp1);
		wordMatches.add(temp2);
		wordMatches.add(temp3);
		
		LinkingMatchesAndBigram findMatches = new LinkingMatchesAndBigram(wordMatches);
		String result = findMatches.getResult();
		
		System.out.println(result);
	}
}
