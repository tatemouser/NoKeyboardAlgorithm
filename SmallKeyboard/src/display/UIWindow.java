package display;

import org.eclipse.swt.SWT;
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
    private String enteredText = ""; // Variable to store the entered text
    private Button enterButton;
    private Text textBox;
    private Display display = new Display();
    private Shell shell = new Shell(display);
    
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
    
    public void setText(Label input, GridData outputData) {
        input.setLayoutData(outputData);
        input.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    }
    
    public void displayBackgroundWithColor() {
        Label horizontalGridLine = new Label(shell, SWT.NONE);
        horizontalGridLine.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        horizontalGridLine.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
    }
    
    public void addCompositeRow(Composite temp) {
    	temp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	temp.setLayout(new GridLayout(3, true)); // 3 columns in the second row
    	temp.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
    }
    
    private void fillSecondComposite(Composite parent, String labelText) {
        Label input = new Label(parent, SWT.CENTER);
        input.setText(labelText);
        GridData inputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        setText(input, inputData);
    }
    
    // Fills row one with Input Label / Text Box / Enter Button
	public void fillFirstComposite(Composite inputComposite) {
    	Label inputLabel = new Label(inputComposite, SWT.RIGHT); 
    	inputLabel.setText("Type Sentence");
    	GridData inputLabelData = new GridData(SWT.FILL, SWT.CENTER, false, false);
    	inputLabel.setLayoutData(inputLabelData);
    	inputLabel.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_GRAY));

    	Text textBox = new Text(inputComposite, SWT.BORDER | SWT.SINGLE); 
    	GridData textBoxData = new GridData(SWT.FILL, SWT.CENTER, true, false);
    	textBox.setLayoutData(textBoxData);
    	textBox.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    	textBox.setText("how do you say hello world");
    	this.textBox = textBox;
    	
    	Button enterButton = new Button(inputComposite, SWT.NONE); 
    	enterButton.setText("Enter");
    	shell.setDefaultButton(enterButton);
    	GridData enterButtonData = new GridData(SWT.FILL, SWT.CENTER, false, false);
    	enterButton.setLayoutData(enterButtonData);
    	this.enterButton = enterButton;
	}
    
    public void run() {
    	setShell();
    	
    	// Create parent composite and rows within
    	Composite parentComposite = new Composite(shell, SWT.NONE);
    	GridData parentGridData = new GridData(SWT.FILL, SWT.FILL, true, true); 
    		parentComposite.setLayoutData(parentGridData);
    		parentComposite.setLayout(new GridLayout(1, false)); 
    	
    	Composite inputComposite = new Composite(parentComposite, SWT.NONE);
    		addCompositeRow(inputComposite);
    	
    	Composite outputTitleComposite = new Composite(parentComposite, SWT.NONE);
    		addCompositeRow(outputTitleComposite);
    	
    	Composite outputComposite = new Composite(parentComposite, SWT.NONE);
    		addCompositeRow(outputComposite);
    	

    	// Fill composites
    	fillFirstComposite(inputComposite);
    	
    	fillSecondComposite(outputTitleComposite, "Input");
    	fillSecondComposite(outputTitleComposite, "Word to Number");
    	fillSecondComposite(outputTitleComposite, "Number to Word");
        	
        // Third composite, Show input / Show num to word / Show word to num
    	Label input = new Label(outputComposite, SWT.BEGINNING);
    	GridData inputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
    	setText(input, inputData);

    	Label inputConversion = new Label(outputComposite, SWT.BEGINNING);
    	GridData inputConversionData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
    	setText(inputConversion, inputConversionData);

    	Label output = new Label(outputComposite, SWT.BEGINNING);
    	GridData outputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
    	setText(output, outputData);

    	
        
        // Receive input for first composite
        enterButton.addListener(SWT.Selection, event -> {
        	String[] vals = new String[3];
        	vals[0] = textBox.getText().trim();
        	
            Index runner = new Index();
            vals = runner.GUIMode(vals[0]);
            
            input.setText(vals[0]);
            inputConversion.setText(vals[1]);
            output.setText(vals[2]);
        });
        
        // Non-essential Code
        displayBackgroundWithColor();
        
        initializeShell();
    }
}
