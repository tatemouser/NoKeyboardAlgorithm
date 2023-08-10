package testing;

import java.util.ArrayList;

import bigramLanguageModel.*;

public class customBigramSet {
	public void run() {
		// Initialize and train model with data set.
		BigramMain bigramMain = new BigramMain();
		
		//BigramMain.exampleSet();
		//BigramMain.getAfterWord();
		//BigramMain.getPriorWord();
		//BigramMain.getCenterWord();
		
		//DELETE NEXT TWO LINES
		//	  String[] leftAndMiddleWords = {"can", "can", "can", "bow", "bow", "bow"};
		//   String[] middleAndRightWords = {"see", "sea", "sss", "sea", "ses", "ese"};
		ArrayList<ArrayList<String>> temp1 = new ArrayList<>();
        ArrayList<String> temp2 = new ArrayList<String>() {{
            add("you");
            add("d");
            add("x");
            add("s");
            add("d");
            add("q");
        }};		
        ArrayList<String> temp3 = new ArrayList<String>() {{
            add("is");
            add("si");
            add("ii");
            add("ss");
            add("ls");
            add("sl");
        }};	
        temp1.add(temp2);
		temp1.add(temp3);
		String result = BigramMain.getCenterWord("that", temp1);
		BigramMain.printWordsNotFound();
		
		System.out.println(" Final Result:" + result);
	}
}
