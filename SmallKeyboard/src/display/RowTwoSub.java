package display;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class RowTwoSub extends RowTwo {

	public RowTwoSub(Composite parent) {
		super(parent);
	}
	
	private static void createStackedLabel(Composite parent, String text, boolean markUp) {
	    Label label = new Label(parent, SWT.NONE);
	    label.setText(text);
	    GridData gridData = new GridData(SWT.CENTER, SWT.TOP, true, false);
	    gridData.verticalIndent = 0; 
	    label.setLayoutData(gridData);
	    if(markUp) label.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW)); // Change the color as needed
	}

	
	public static void fillWords(Composite wordColumn, int index, int mark) {
		wordColumn.setLayout(new GridLayout());
		if(index == 0) {
			for(int i = 0; i < wordMatchesUIOnly.get(index).size(); i++) {
				String word = wordMatchesUIOnly.get(index).get(i);
				if(i == mark || mark == -1) {
					createStackedLabel(wordColumn, word, true);
				} else {
					createStackedLabel(wordColumn, word, false);
				}
			}		
		} else {
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
	
    public static void fillWord(int index, Composite conversionBox, boolean isFirst) {
		int toHighlight = 0;
		int placement = isFirst ? 0 : index;
		
		for(String word: wordMatchesUIOnly.get(index)) {
			
    		Composite wordSpaceOne = new Composite(conversionBox, SWT.BORDER);
    		GridData gridDataOne = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, true);

    		Composite wordSpaceTwo = new Composite(conversionBox, SWT.BORDER);
    		GridData gridDataTwo = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, true);
    		
    		int size1 = 0;
    		int size2 = 0;
    		
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
    		int finalSize = (size1 >= size2) ? size1 : size2;
    		
    		gridDataOne.widthHint = 50; 
    		gridDataOne.heightHint = finalSize; 
    		wordSpaceOne.setLayoutData(gridDataOne);
    		gridDataTwo.widthHint = 50; 
    		gridDataTwo.heightHint = finalSize;
    		wordSpaceTwo.setLayoutData(gridDataTwo);
    		
    	}
    }
    
    
	public static void fillConversionBox(Composite conversionBox, int index) {
		GridLayout gridLayout = new GridLayout(wordMatchesUIOnly.get(index).size()*2, false);
		gridLayout.verticalSpacing = 0; // Adjust the vertical spacing as needed
		gridLayout.horizontalSpacing = 10;
		conversionBox.setLayout(gridLayout);

        if(index == 0) {
        	// First Word
        	fillWord(index, conversionBox, true);
        } else fillWord(index, conversionBox, false);

		conversionBox.layout();
	}
	
	public static String getString(int indexOfCol, int indexInCol) {
		String result = "";
		if(indexOfCol == 0) {
			for(String i: wordMatchesUIOnly.get(indexOfCol+1)) {
				result += wordMatchesUIOnly.get(indexOfCol).get(indexInCol) + " + " + i + " \n";
			}
		} else {

			for(String i: wordMatchesUIOnly.get(indexOfCol-1)) {
				result +=  i + " + " + wordMatchesUIOnly.get(indexOfCol).get(indexInCol) + "\n";
			}
		}
		return result;
	}
	
	public static void fillTotalsBox(Composite totalsBox, int index) {
		GridLayout gridLayout = new GridLayout(wordMatchesUIOnly.get(index).size()*2, false);
		gridLayout.verticalSpacing = 0; // Adjust the vertical spacing as needed

		totalsBox.setLayout(gridLayout);
	    System.out.println(wordMatchesUIOnly.get(index).size() + " size is here");

		for(int i = 0; i < wordMatchesUIOnly.get(index).size(); i++) {
			Composite wordSpaceOne = new Composite(totalsBox, SWT.BORDER);
			GridData gridDataOne = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, true);
			
			wordSpaceOne.setLayout(new GridLayout());
		    Label label = new Label(wordSpaceOne, SWT.NONE);
		    label.setText(getString(index, i));
		    
		    GridData gridData = new GridData(SWT.CENTER, SWT.TOP, true, false);
		    gridData.verticalIndent = 0; // Set verticalIndent to zero
		    label.setLayoutData(gridData);

			gridDataOne.widthHint = 120;
			int size1 = (index == 0) ? wordMatchesUIOnly.get(index).size() * 25 : wordMatchesUIOnly.get(index-1).size() * 25;
			int size2 = (index == 0) ? wordMatchesUIOnly.get(index+1).size() * 25 : wordMatchesUIOnly.get(index).size() * 25;
    		gridDataOne.heightHint = (index == 0) ? size2 : size1;
			wordSpaceOne.setLayoutData(gridDataOne);
		}
		totalsBox.layout();

	}
}
