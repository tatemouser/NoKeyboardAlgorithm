package display;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import bigramLanguageModel.BigramMain;

public class RowTwoSub extends RowTwo {
	public static ArrayList<Double> scores = new ArrayList<Double>();
	
	public RowTwoSub(Composite parent) {
		super(parent);
	}


	
	// Simple text box, re-used for all 3 main boxes
	private static void createStackedLabel(Composite parent, String text, boolean markUp) {
		// Area for text
	    Label label = new Label(parent, SWT.NONE);
	    // Text in area
	    label.setText(text);
	    GridData gridData = new GridData(SWT.CENTER, SWT.TOP, true, false);
	    gridData.verticalIndent = 0; 
	    label.setLayoutData(gridData);
	    // Highlighting if needed
	    if(markUp) label.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW)); 
	}
	
	// Used only by fillConversionBox() to locate / display / highlight certain words 
	public static void fillWords(Composite wordColumn, int index, int mark) {
		wordColumn.setLayout(new GridLayout());
		// Adjusting word selection based on index
		// For conversions, first word looks to next word while all other words look to prior word
		if(index == 0) {
			for(int i = 0; i < wordMatchesUIOnly.get(index).size(); i++) {
				String word = wordMatchesUIOnly.get(index).get(i);
				if(	i == mark  ||    // highlight one word
				   -1 == mark  ) {   // highlight entire column
					createStackedLabel(wordColumn, word, true);
				} else {
					createStackedLabel(wordColumn, word, false);
				}
			}		
		} else {
			// Mirror methods to index == 0, just swapped
			for(int i = 0; i < wordMatchesUIOnly.get(index).size(); i++) {
				String word = wordMatchesUIOnly.get(index).get(i);
				if(i == mark || mark == -1) {
					createStackedLabel(wordColumn, word, true);
				} else {
					createStackedLabel(wordColumn, word, false);
				}
			}	
		}
	}
	
	// Used only by fillConversionBox(), builds boxes that will be filled by fillWord()
    public static void fillWord(int index, Composite conversionBox, boolean isFirst) {
		int toHighlight = 0;
		int placement = isFirst ? 0 : index;
		
		for(String word: wordMatchesUIOnly.get(index)) {
			// Two boxes needed for each word comparison
    		Composite wordSpaceOne = new Composite(conversionBox, SWT.BORDER);
    		GridData gridDataOne = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, true);

    		Composite wordSpaceTwo = new Composite(conversionBox, SWT.BORDER);
    		GridData gridDataTwo = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, true);
    		
    		// Used for sizing of boxes
    		int size1 = 0;
    		int size2 = 0;
    		
    		// Adjusting word selection based on index
    		// For conversions, first word looks to next word while all other words look to prior word
    		if(isFirst) { 
    			fillWords(wordSpaceOne, index, toHighlight);
    			fillWords(wordSpaceTwo, index+1, -1);
        		toHighlight++;
        		size1 = wordMatchesUIOnly.get(index).size() * 25;
        		size2 = wordMatchesUIOnly.get(index+1).size() * 25;
    		} else {
    			// Vital to swapping highlighting and organization of word conversion boxes that are not the first element.
    			fillWords(wordSpaceOne, index-1, -1);
    			fillWords(wordSpaceTwo, index, toHighlight);
        		toHighlight++;
        		size1 = wordMatchesUIOnly.get(index-1).size() * 25;
        		size2 = wordMatchesUIOnly.get(index).size() * 25;
    		}
    		
			// Size and set/update boxes
			int finalSize = (size1 >= size2) ? size1 : size2;
    		gridDataOne.widthHint = 50; 
    		gridDataOne.heightHint = finalSize; 
    		gridDataTwo.widthHint = 50; 
    		gridDataTwo.heightHint = finalSize;
	    		
	    	wordSpaceOne.setLayoutData(gridDataOne);
    		wordSpaceTwo.setLayoutData(gridDataTwo);
    		
    	}
    }
    
    // Used only by fillTotalsBox(), pulls from main package to see words used in this language model run
	public static String getString(int indexOfCol, int indexInCol) {
		String result = "";
		//TODO: To pull scores from this language model run
        //ArrayList<Double> scores = BigramMain.scoresForGUI;
        
		if(indexOfCol == 0) {
			for(String i: wordMatchesUIOnly.get(indexOfCol+1)) 
				result += wordMatchesUIOnly.get(indexOfCol).get(indexInCol) + " + " + i + " \n";
		} else {
			for(String i: wordMatchesUIOnly.get(indexOfCol-1))  
				result +=  i + " + " + wordMatchesUIOnly.get(indexOfCol).get(indexInCol) + "\n";
		}
		return result;
	}
	
	
	
	
	
	
	
	/* 
	 * Show all words being compared and highlight order of comparisons.
	 */
	public static void fillConversionBox(Composite conversionBox, int index) {
		GridLayout gridLayout = new GridLayout(wordMatchesUIOnly.get(index).size()*2, false);
		gridLayout.verticalSpacing = 0; 
		gridLayout.horizontalSpacing = 10;
		conversionBox.setLayout(gridLayout);

		// Fill space created
        if(index == 0) {
        	fillWord(index, conversionBox, true);
        } else fillWord(index, conversionBox, false);

		conversionBox.layout();
	}

	
	
	
	/*
	 * Show just words being compared this run, scraping future comparisons.
	 */
	public static void fillTotalsBox(Composite totalsBox, int index) {
		GridLayout gridLayout = new GridLayout(wordMatchesUIOnly.get(index).size()*2, false);
		gridLayout.verticalSpacing = 0; 
		totalsBox.setLayout(gridLayout);

		
		for(int i = 0; i < wordMatchesUIOnly.get(index).size(); i++) {
			// Create area for contents
			Composite wordSpaceOne = new Composite(totalsBox, SWT.BORDER);
			GridData gridDataOne = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, true);
			
			// Get and put contents of area
			wordSpaceOne.setLayout(new GridLayout());
		    Label label = new Label(wordSpaceOne, SWT.NONE);
		    label.setText(getString(index, i));
		    
		    // Size box
		    GridData gridData = new GridData(SWT.CENTER, SWT.TOP, true, false);
		    gridData.verticalIndent = 0;
		    label.setLayoutData(gridData);
			gridDataOne.widthHint = 120;
			int size1 = (index == 0) ? wordMatchesUIOnly.get(index).size() * 25 : wordMatchesUIOnly.get(index-1).size() * 25;
			int size2 = (index == 0) ? wordMatchesUIOnly.get(index+1).size() * 25 : wordMatchesUIOnly.get(index).size() * 25;
    		gridDataOne.heightHint = (index == 0) ? size2 : size1;
			wordSpaceOne.setLayoutData(gridDataOne);
		}
		
		totalsBox.layout();
	}

	
	
	
	/*
	 * Shows final two words chosen
	 */
	public static void fillBestScoreBox(Composite bestScoreBox, String sentence, int index) {
        String[] words = sentence.split(" ");

        // Fix layout for both areas
        GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 0; 
		gridLayout.horizontalSpacing = 10;
		bestScoreBox.setLayout(gridLayout);

		// Create and initialize two spaces for current and chosen word
		Composite wordSpaceOne = new Composite(bestScoreBox, SWT.BORDER);
			GridData gridDataOne = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, true);
			wordSpaceOne.setLayout(new GridLayout());

		Composite wordSpaceTwo = new Composite(bestScoreBox, SWT.BORDER);
			GridData gridDataTwo = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, true);
			wordSpaceTwo.setLayout(new GridLayout());


		// Fill two areas created
		if(index < words.length-1) { 
			createStackedLabel(wordSpaceOne, words[index], false);
			createStackedLabel(wordSpaceTwo, words[index+1], false);
		} else {
			createStackedLabel(wordSpaceOne, words[index-1], false);
			createStackedLabel(wordSpaceTwo, words[index], false);
		}

		// Resize areas
		gridDataOne.widthHint = 50; 
		gridDataOne.heightHint = 25; 
		wordSpaceOne.setLayoutData(gridDataOne);
		gridDataTwo.widthHint = 50; 
		gridDataTwo.heightHint = 25;
		wordSpaceTwo.setLayoutData(gridDataTwo);
		
		// Update display to show changes
        bestScoreBox.layout();
	    
	}
}
