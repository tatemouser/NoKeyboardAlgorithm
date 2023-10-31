package display;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
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
	    gridData.verticalIndent = 0; // Set verticalIndent to zero
	    label.setLayoutData(gridData);
	    if(markUp) label.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW)); // Change the color as needed
	}

	
	public static void fillWords(Composite wordColumn, int index, int mark) {
		wordColumn.setLayout(new GridLayout());
		for(int i = 0; i < wordMatchesUIOnly.get(index).size(); i++) {
			String word = wordMatchesUIOnly.get(index).get(i);
			if(i == mark || mark == -1) {
				createStackedLabel(wordColumn, word, true);
			} else {
				createStackedLabel(wordColumn, word, false);
			}
		}
	}
	
    public static void fillWord(int index, Composite conversionBox, boolean isFirst) {
		int toHighlight = 0;
		int placement = isFirst ? 0 : wordMatchesUIOnly.size()-1;
		
    	for(String word: wordMatchesUIOnly.get(placement)) {
    		Composite wordSpaceOne = new Composite(conversionBox, SWT.BORDER);
    		wordSpaceOne.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
    		//wordSpaceOne.setBackground(conversionBox.getDisplay().getSystemColor(SWT.COLOR_BLUE));
    		
    		// Create the second Composite
    		Composite wordSpaceTwo = new Composite(conversionBox, SWT.BORDER);
    		wordSpaceTwo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
    		//wordSpaceTwo.setBackground(conversionBox.getDisplay().getSystemColor(SWT.COLOR_RED));
    		
    		if(isFirst) { 
    			fillWords(wordSpaceOne, index, toHighlight);
    			fillWords(wordSpaceTwo, index+1, -1);
        		toHighlight++;
    		} else {
    			fillWords(wordSpaceOne, index-1, toHighlight);
    			fillWords(wordSpaceTwo, index, -1);
    		}
    	}

//        ArrayList<String> first = wordMatchesUIOnly.get(index);
//        ArrayList<String> second = wordMatchesUIOnly.get(index+1);
//        System.out.println(first);
//        System.out.println(second);
    	System.out.println("First");
    	System.out.println(index);
    }
    
    public static void fillMiddleWord(int index, Composite conversionBox) {
    	// First equal index-1 		Second = index 		Third = index + 1
        ArrayList<String> first = wordMatchesUIOnly.get(index-1);
        ArrayList<String> second = wordMatchesUIOnly.get(index);
        ArrayList<String> third = wordMatchesUIOnly.get(index+1);
        System.out.println(first);
        System.out.println(second);
        System.out.println(third);
    	System.out.println("Middle");
    	System.out.println(index);
    }
    
    
    
    
    
    
    
    
	public static void fillConversionBox(Composite conversionBox, int index) {
		GridLayout gridLayout = new GridLayout(wordMatchesUIOnly.get(index).size()*2, false);
		gridLayout.verticalSpacing = 10; // Adjust the vertical spacing as needed

		conversionBox.setLayout(gridLayout);

		// TODO: Highlight words in question
        if(index == 0) {
        	// First Word
        	fillWord(index, conversionBox, true);
        } else fillWord(index, conversionBox, false);

        
        
		conversionBox.layout();

	}
}
