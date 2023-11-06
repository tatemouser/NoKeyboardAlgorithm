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


public class RowTwo {
	private Composite parent;
	private Composite parentComposite;
	private Composite leftComp;
	private Composite rightComp;
	private String[] vals;
    protected static ArrayList<ArrayList<String>> wordMatchesUIOnly;

    
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
 
    
    
    /*
     * Area of buttons *Overall Shell - bottom row / left column*
     * Iterates each word entered in row one text box. 
     * Builds new composite enter button for each word, storing and incrementing an index for future use *FillRightCol*
     * Upon button trigger, called() builds unique box layout for inputs
     * 
     * fillLeftCol() -> addButton() -> called()
     */
    public void fillLeftCol(Composite leftCol) {
    	this.leftComp = new Composite(leftCol, SWT.NONE);
    	leftComp.setLayout(new FillLayout(SWT.VERTICAL));
    	leftComp.setBackground(leftCol.getDisplay().getSystemColor(SWT.COLOR_BLACK));
    	
    	Label leftTitle = new Label(leftComp, SWT.NONE);
    	leftTitle.setText("Show Conversions:");	
    	leftTitle.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_GRAY)); 
    }
    
    public void addButton() {
    	// vals[2] = output words * labeled as "Num To Words" in shell*
    	if(vals[2] != null) {
            String[] words = vals[2].split(" ");
            List<Runnable> buttonActions = new ArrayList<>();

	    	for (int i = 0; i < words.length; i++) {
	            final int index = i; 
	    	    Button wordButton = new Button(leftComp, SWT.NONE);
	    	    	wordButton.setText(words[i]);
	    	    GridData buttonData = new GridData(SWT.CENTER, SWT.CENTER, true, false);
	    	    	buttonData.widthHint = 100; 
	    	    	buttonData.heightHint = 40;     
	    	    	wordButton.setLayoutData(buttonData);
	    	    	
	    	    	
	    	    // Action based off assigned index to button
	            buttonActions.add(() -> {
	            	called(index);
	            });
	    	    
	            
	    	    wordButton.addSelectionListener(new SelectionAdapter() {
	                @Override
	                public void widgetSelected(SelectionEvent e) {
	                    buttonActions.get(index).run();
	                }
	            });
	    	}
    	} else System.out.println("Error: Could not pass input to rowTwo class for button assignment.");
    	leftComp.layout();
    }

    /*
     * Triggered by clicking left column buttons 
     */
    public void called(int index) {
    	// Remove prior boxes
        for (Control control : rightComp.getChildren()) control.dispose();
     
        // Right column *black background in shell*
    	GridLayout gridLayout = new GridLayout(1, false);
    	rightComp.setLayout(gridLayout);
  
    	
    	
    	// Create three areas for use as labeled
    	Composite conversionBox = new Composite(rightComp, SWT.BORDER);
    		conversionBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    		conversionBox.setBackground(rightComp.getDisplay().getSystemColor(SWT.COLOR_GRAY));
    		RowTwoSub.fillConversionBox(conversionBox, index); //TODO: differiantiate between the index's, see if index can be public
  
    	Composite totalsBox = new Composite(rightComp, SWT.BORDER);
    		totalsBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    		totalsBox.setBackground(rightComp.getDisplay().getSystemColor(SWT.COLOR_GRAY));
    		RowTwoSub.fillTotalsBox(totalsBox, index);
        
    	Composite bestScoreBox = new Composite(rightComp, SWT.BORDER);
    		bestScoreBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    		bestScoreBox.setBackground(rightComp.getDisplay().getSystemColor(SWT.COLOR_GRAY));
    		RowTwoSub.fillBestScoreBox(bestScoreBox, vals[2], index);
    		
    		
    		
        rightComp.layout();
    }
    
    
    
    
    
    
    /*
     * Area of conversion boxes when button pressed *Overall Shell - bottom row / right column*
     * Method used to initialize area. Later called by listeners in left column. 
     */
    public void fillRightCol(Composite rightCol) {
    	this.rightComp = new Composite(rightCol, SWT.NONE);
    	rightComp.setLayout(new FillLayout(SWT.VERTICAL));
    	rightComp.setBackground(rightCol.getDisplay().getSystemColor(SWT.COLOR_BLACK));
    }
    
    
    public RowTwo(Composite parent) { 
    	// Reference to main shell composite
        this.parent = parent;
        // Reference to row one main composite
    	this.parentComposite = new Composite(parent, SWT.FLAT);

    	GridData parentGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
    	parentComposite.setLayoutData(parentGridData);
    	parentComposite.setLayout(new GridLayout(2, false)); 
    	

    	
    	
		// Initialize buttons to show conversions based on # of words
    	Composite leftCol = new Composite(parentComposite, SWT.NONE);
    		leftCol.setLayout(new GridLayout(1, false));
    		leftCol.setLayout(new FillLayout(SWT.VERTICAL)); 
    		// Area for title and buttons generated in left column
    		GridData comp1Grid = new GridData(SWT.CENTER, SWT.FILL, false, false);
    		leftCol.setLayoutData(comp1Grid);
    		
    		fillLeftCol(leftCol);
    	
    		
    		
    		
    	// Initialize boxes based on buttons pressed in leftCol
    	Composite rightCol = new Composite(parentComposite, SWT.NONE);
	    	rightCol.setLayout(new FillLayout(SWT.VERTICAL));
	    	rightCol.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));  
	    	
	    	fillRightCol(rightCol);

    }
}




