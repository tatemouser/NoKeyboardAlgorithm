package display;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class RowTwo {
	private Composite parent;
	private Composite parentComposite;
	private Composite leftComp;
	private String[] vals;
    private static ArrayList<ArrayList<String>> wordMatchesUIOnly;
	//TODO: Extend these two classes to UIWindow

    public void setWordMatchesUIOnly(ArrayList<ArrayList<String>> wordSet) {
    	wordMatchesUIOnly = wordSet;
    }

	
    public void setBackgroundColor(int swtColorConstant) {
        if (parentComposite != null) {
            // Use Display.getCurrent().getSystemColor to get the color based on the SWT constant
            Color color = Display.getCurrent().getSystemColor(swtColorConstant);
            parentComposite.setBackground(color);
        }
    }
    
    public void getVals(String[] vals) {
    	this.vals = vals;
    }
    
    public void addCompositeRow(Composite temp) {
    	temp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	temp.setLayout(new GridLayout(3, true)); // 3 columns in the second row
    	temp.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
    }
 
    public void addButton() {
    	if(vals[2] != null) {
            String[] words = vals[2].split(" ");
    		
	    	for (int i = 0; i < words.length; i++) {
	    	    Button button = new Button(leftComp, SWT.NONE);
	    	    button.setText(words[i]); // Set a label for the button
	
	    	    // Set the button's size and any other properties as needed
	    	    GridData buttonData = new GridData(SWT.CENTER, SWT.CENTER, true, false);
	    	    buttonData.widthHint = 100; // Set the width
	    	    buttonData.heightHint = 40; // Set the height
	    	    button.setLayoutData(buttonData);
	    	    
	    	}
    	} else System.out.println("Error: Could not pass input to rowTwo class for button assignment.");
    	leftComp.layout();
    	System.out.println(wordMatchesUIOnly.size());
    }
    
    
    public void fillLeftCol(Composite leftCol) {
    	this.leftComp = new Composite(leftCol, SWT.NONE);
    	leftComp.setLayout(new FillLayout(SWT.VERTICAL)); // Use FillLayout with SWT.VERTICAL style
    	leftComp.setBackground(leftCol.getDisplay().getSystemColor(SWT.COLOR_BLACK));
    	
    	Label title = new Label(leftComp, SWT.NONE);
    	title.setText("Show Conversions:");	
    }
    
	
    public RowTwo(Composite parent) { 
    	// Reference to main shell composite
        this.parent = parent;
        // Reference to row one main composite
    	this.parentComposite = new Composite(parent, SWT.FLAT);
    	
    	GridData parentGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
    	parentComposite.setLayoutData(parentGridData);
    	parentComposite.setLayout(new GridLayout(2, false)); // Use 2 columns for horizontal layout
    	

    	
    	Composite leftCol = new Composite(parentComposite, SWT.NONE);
    		leftCol.setLayout(new GridLayout(1, false));
    		leftCol.setLayout(new FillLayout(SWT.VERTICAL)); // Use FillLayout with SWT.VERTICAL style
    	GridData comp1Grid = new GridData(SWT.BEGINNING, SWT.FILL, true, true);
    		leftCol.setLayoutData(comp1Grid);
    		leftCol.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));

    	fillLeftCol(leftCol);
    	
    	Composite rightCol = new Composite(parentComposite, SWT.NONE);
    		rightCol.setLayout(new GridLayout(1, false));
    		rightCol.setLayout(new FillLayout(SWT.VERTICAL)); // Use FillLayout with SWT.VERTICAL style
    	GridData comp2Grid = new GridData(SWT.BEGINNING, SWT.FILL, true, true);
    		rightCol.setLayoutData(comp2Grid);
    		rightCol.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));
    	
    	// Row two calls update window in UIWindow, upon button press. This triggers row two to update.
    	//fillRightCol(rightCol);
        
    }
}
