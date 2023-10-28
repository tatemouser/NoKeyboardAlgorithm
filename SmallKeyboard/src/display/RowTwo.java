package display;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import main.Index;

public class RowTwo {
	private Composite parent;
	private Composite parentComposite;
	private Composite leftComp;
	private Composite rightComp;
	private String[] vals;
    private static ArrayList<ArrayList<String>> wordMatchesUIOnly;
    
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
 
    
    
    
    public void fillLeftCol(Composite leftCol) {
    	this.leftComp = new Composite(leftCol, SWT.NONE);
    	leftComp.setLayout(new FillLayout(SWT.VERTICAL));
    	leftComp.setBackground(leftCol.getDisplay().getSystemColor(SWT.COLOR_BLACK));
    	
    	Label leftTitle = new Label(leftComp, SWT.NONE);
    	leftTitle.setText("Show Conversions:");	
    	leftTitle.setBackground(parentComposite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    }
    
    public void addButton() {
    	if(vals[2] != null) {
            String[] words = vals[2].split(" ");
            // This
            List<Runnable> buttonActions = new ArrayList<>();

	    	for (int i = 0; i < words.length; i++) {
	    		// this
	            final int index = i; // Declare index inside the loop

	    	    Button wordButton = new Button(leftComp, SWT.NONE);
	    	    
	    	    wordButton.setText(words[i]); // Set a label for the button
	
	    	    // Set the button's size and any other properties as needed
	    	    GridData buttonData = new GridData(SWT.CENTER, SWT.CENTER, true, false);
	    	    buttonData.widthHint = 100; // Set the width
	    	    buttonData.heightHint = 40; // Set the height        
	    	    
	    	    wordButton.setLayoutData(buttonData);
	    	    
	    	    // These
	            buttonActions.add(() -> {
	                // Define the action for the button
	                System.out.println("Button " + (index + 1) + " was clicked.");
	            });
	    	    
	    	    wordButton.addSelectionListener(new SelectionAdapter() {
	                @Override
	                public void widgetSelected(SelectionEvent e) {
	                	// this
	                    buttonActions.get(index).run();
	                }
	            });
	    	    
	    	}
    	} else System.out.println("Error: Could not pass input to rowTwo class for button assignment.");
    	leftComp.layout();
    }

    
    public void fillRightCol(Composite rightCol) {
    	this.rightComp = new Composite(rightCol, SWT.NONE);
    	rightComp.setLayout(new FillLayout(SWT.VERTICAL));
    	rightComp.setBackground(rightCol.getDisplay().getSystemColor(SWT.COLOR_BLACK));
    }
   
    public void called() {
    	// Remove word conversion visuals
        for (Control control : rightComp.getChildren()) {
            control.dispose();
        }
    	// Create a GridLayout for the rightComp Composite
    	GridLayout gridLayout = new GridLayout(1, false);
    	rightComp.setLayout(gridLayout);

    	// Create three small boxes (Composites)
    	Composite box1 = new Composite(rightComp, SWT.BORDER);
    	box1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    	box1.setBackground(rightComp.getDisplay().getSystemColor(SWT.COLOR_GREEN));
    	
        rightComp.layout();
    }
    
    
    public void showFirstWord() {
    	
    }
    
    public void showMiddleWord() {
    	
    }
    
    public void lastWord() {
    	
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
    		leftCol.setLayout(new FillLayout(SWT.VERTICAL)); 
    	// Area for title and buttons generated in left col
    	GridData comp1Grid = new GridData(SWT.CENTER, SWT.FILL, false, false);
    		leftCol.setLayoutData(comp1Grid);
    		leftCol.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));

    	fillLeftCol(leftCol);
    	
    	Composite rightCol = new Composite(parentComposite, SWT.NONE);
    	rightCol.setLayout(new FillLayout(SWT.VERTICAL));
    	rightCol.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true)); 
    	rightCol.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_RED));
    	
    	fillRightCol(rightCol);

    }
}




