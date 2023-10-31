package display;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class RowTwoSub extends RowTwo {

	public RowTwoSub(Composite parent) {
		super(parent);
	}


	
	public static void fillConversionBox(Composite conversionBox, int index) {
    	GridLayout gridLayout = new GridLayout(1, false);
    	conversionBox.setLayout(gridLayout);
    	
    	Composite bestScoreBox = new Composite(conversionBox, SWT.BORDER);
    	bestScoreBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    	bestScoreBox.setBackground(conversionBox.getDisplay().getSystemColor(SWT.COLOR_BLUE));
        conversionBox.layout();
        
        if(index == 0) {
        	showFirstWord(index);
        } else if(index == wordMatchesUIOnly.size()-1){
        	showLastWord(index);
        } else {
        	showMiddleWord(index);
        }
	}
	
	public static void firstWord() {
		
	}
	
    public static void showFirstWord(int index) {
        ArrayList<String> first = wordMatchesUIOnly.get(index);
        ArrayList<String> second = wordMatchesUIOnly.get(index+1);
        System.out.println(first);
        System.out.println(second);
    	System.out.println("First");
    	System.out.println(index);
    }
    
    public static void showMiddleWord(int index) {
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
    
    public static void showLastWord(int index) {
    	// First = second to last word		Second = Last word
        ArrayList<String> first = wordMatchesUIOnly.get(index-1);
        ArrayList<String> second = wordMatchesUIOnly.get(index);
        System.out.println(first);
        System.out.println(second);
    	System.out.println("Last");
    	System.out.println(index);
    }
}
