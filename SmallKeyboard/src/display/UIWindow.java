package display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import main.Index;


public class UIWindow extends Index {
	//TODO: Fix multiple clicks of button, removing old buttons
    private Display display = new Display();
    private Shell shell = new Shell(display);
    private static RowTwo rowTwo;

    // Shell demensions
    public void setShell() {
        int screenWidth = shell.getDisplay().getClientArea().width;
        int screenHeight = shell.getDisplay().getClientArea().height;
        int width = shell.getBounds().width;
        int height = shell.getBounds().height;

        // Size and center the shell after it's open
        shell.setSize(screenWidth * 3 / 4, screenHeight * 3 / 4);

        int x = (screenWidth - width) / 2;
        int y = (screenHeight - height) / 2;
        shell.setLocation(x, y);
        
        shell.setText("Text Conversion");
        shell.setLayout(new GridLayout(4, false));	
        shell.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_GRAY));
    }
    
    
    // Fetch new conversion results upon pressing enter
    public static void updateWindow() {
    	rowTwo.setWordMatchesUIOnly(wordMatchesUIOnly);    	
    	rowTwo.addButton();
    }
    
    // Needed to pass new conversion values from Index main package to RowTow displays
    public static void passVals(String[] vals) {
    	rowTwo.getVals(vals);
    }
    
    // Loop to keep shell open
    public void initializeShell() {
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) { 
            	display.sleep();
        	}
        }
        display.dispose();
        System.out.println("Display Closed");
    }

    public void run() {
    	setShell();
    	shell.setLayout(new GridLayout(1, false));    	
    	
    	
    	Composite parentComposite = new Composite(shell, SWT.FLAT);
    		GridData parentGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
	    	parentComposite.setLayoutData(parentGridData);
	    	parentComposite.setLayout(new GridLayout(1, false));
	    	
        RowOne rowOne = new RowOne(parentComposite);
        	rowOne.setBackgroundColor(SWT.COLOR_WHITE);
     
        
        // Independent class, since only needed upon pressing enter. *Different implementation for different inputs*
        UIWindow.rowTwo = new RowTwo(parentComposite);
        rowTwo.setBackgroundColor(SWT.COLOR_GRAY);
    	
    	
        
    	shell.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
        initializeShell();
    }
}
