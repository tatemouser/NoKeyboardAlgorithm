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
		
		ArrayList<ArrayList<String>> temp1 = new ArrayList<>();
        ArrayList<String> temp2 = new ArrayList<String>() {{
            add("are");
            add("you");
            add("can");
            add("rrr");
            add("aaa");
            add("rea");
        }};		
        ArrayList<String> temp3 = new ArrayList<String>() {{
            add("sure");
            add("ssre");
            add("ssse");
            add("ssss");
            add("ssee");
            add("tres");
        }};	
        temp1.add(temp2);
		temp1.add(temp3);
		String result = BigramMain.getCenterWord("you", temp1);
		BigramMain.printWordsNotFound();
		
		System.out.println(" Final Result:" + result);
	}
}
