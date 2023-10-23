package display;

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
	
    public void setBackgroundColor(int swtColorConstant) {
        if (parentComposite != null) {
            // Use Display.getCurrent().getSystemColor to get the color based on the SWT constant
            Color color = Display.getCurrent().getSystemColor(swtColorConstant);
            parentComposite.setBackground(color);
        }
    }
    
    private void fillSecondComposite(Composite parent, String labelText) {
        Label input = new Label(parent, SWT.CENTER);
        input.setText(labelText);
        GridData inputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        input.setLayoutData(inputData);
        input.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    }
    
    public void addCompositeRow(Composite temp) {
    	temp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	temp.setLayout(new GridLayout(3, true)); // 3 columns in the second row
    	temp.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
    }
    
    public void fillLeftCol(Composite leftCol) {
    	this.leftComp = new Composite(leftCol, SWT.NONE);
    	leftComp.setLayout(new FillLayout(SWT.VERTICAL)); // Use FillLayout with SWT.VERTICAL style
    	leftComp.setBackground(leftCol.getDisplay().getSystemColor(SWT.COLOR_BLACK));
    	
    	Label title = new Label(leftComp, SWT.NONE);
    	title.setText("Show Conversions:");
    	
    	Button button1 = new Button(leftComp, SWT.NONE); 
    	button1.setText("Enter");
    	button1.setBounds(10, 10, 100, 40); // Set the x, y, width, and height
    	
    	Button button2 = new Button(leftComp, SWT.NONE); 
    	button2.setText("Enter");
    	button2.setBounds(10, 10, 100, 40); // Set the x, y, width, and height
    	
        
    }
 
    public void addButton() {
    	Button button3 = new Button(leftComp, SWT.NONE); 
    	button3.setText("Enter");
    	button3.setBounds(10, 10, 100, 40); // Set the x, y, width, and height
    	leftComp.layout();
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
    	GridData comp1Grid = new GridData(SWT.BEGINNING, SWT.FILL, true, true);
    	leftCol.setLayoutData(comp1Grid);
    	leftCol.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));

    	fillLeftCol(leftCol);
    	
    	Composite comp2 = new Composite(parentComposite, SWT.FILL);
        comp2.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_RED));
        
    }
}
