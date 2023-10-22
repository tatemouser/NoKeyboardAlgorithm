package display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import main.Index;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class UIWindow {

    private Display display = new Display();
    private Shell shell = new Shell(display);
    
    // Shell demensions
    public void setShell() {
        int screenWidth = shell.getDisplay().getClientArea().width;
        int screenHeight = shell.getDisplay().getClientArea().height;
        int width = shell.getBounds().width;
        int height = shell.getBounds().height;

        // Size and center the shell after it's open
        shell.setSize(screenWidth / 2, screenHeight / 2);
        int x = (screenWidth - width) / 2;
        int y = (screenHeight - height) / 2;
        shell.setLocation(x, y);
        
        shell.setText("Text Conversion");
        shell.setLayout(new GridLayout(4, false));	
        shell.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_GRAY));
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
        rowOne.setBackgroundColor(SWT.COLOR_RED);
        
        RowOne temp = new RowOne(parentComposite);
        temp.setBackgroundColor(SWT.COLOR_BLUE);

    	
    	
    	
    	shell.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));

        
        initializeShell();
    }
}
