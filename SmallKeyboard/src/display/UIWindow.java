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
    
    public void setText(Label input, GridData outputData) {
        input.setLayoutData(outputData);
        input.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    }
    
    public void displayBackgroundWithColor() {
        Label horizontalGridLine = new Label(shell, SWT.NONE);
        horizontalGridLine.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_GRAY));
        horizontalGridLine.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
    }
    
    public void addCompositeRow(Composite temp) {
    	temp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	temp.setLayout(new GridLayout(3, true)); // 3 columns in the second row
    	temp.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
    }
    
    private void createInputLabel(Composite parent, String labelText) {
        Label input = new Label(parent, SWT.CENTER);
        input.setText(labelText);
        GridData inputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        setText(input, inputData);
    }
    
    public void run() {

    	setShell();
    	
    	Composite parentComposite = new Composite(shell, SWT.NONE);
    	GridData parentGridData = new GridData(SWT.FILL, SWT.FILL, true, true); // Use SWT.FILL for horizontal and vertical alignment
    	parentComposite.setLayoutData(parentGridData);
    	parentComposite.setLayout(new GridLayout(1, false)); // Single column for stacking
    	// Create two row composites
    	Composite inputComposite = new Composite(parentComposite, SWT.NONE);
    	addCompositeRow(inputComposite);
    	
    	Composite outputTitleComposite = new Composite(parentComposite, SWT.NONE);
    	addCompositeRow(outputTitleComposite);
    	
    	Composite outputComposite = new Composite(parentComposite, SWT.NONE);
    	addCompositeRow(outputComposite);


    	
    	
    	
    	// Input Label / Text Box / Enter Button
    	Label inputLabel = new Label(inputComposite, SWT.RIGHT); 
    	inputLabel.setText("Type Sentence");
    	GridData inputLabelData = new GridData(SWT.FILL, SWT.CENTER, false, false);
    	inputLabelData.widthHint = 100; 
    	inputLabel.setLayoutData(inputLabelData);
    	inputLabel.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_GRAY));

    	Text textBox = new Text(inputComposite, SWT.BORDER | SWT.SINGLE); 
    	GridData textBoxData = new GridData(SWT.FILL, SWT.CENTER, true, false);
    	textBoxData.minimumWidth = 200;
    	textBox.setLayoutData(textBoxData);
    	textBox.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    	textBox.setText("how do you say hello world");

    	Button enterButton = new Button(inputComposite, SWT.NONE); 
    	enterButton.setText("Enter");
    	shell.setDefaultButton(enterButton);
    	GridData enterButtonData = new GridData(SWT.FILL, SWT.CENTER, false, false);
    	enterButton.setLayoutData(enterButtonData);

        // Titles of outputs
    	createInputLabel(outputTitleComposite, "Input");
    	createInputLabel(outputTitleComposite, "Word to Number");
    	createInputLabel(outputTitleComposite, "Number to Word");
    	
    	
        	
        // Changing Text Boxes -> Show input / Show num to word / Show word to num
    	Label input = new Label(outputComposite, SWT.BEGINNING);
    	GridData inputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
    	setText(input, inputData);

    	Label inputConversion = new Label(outputComposite, SWT.BEGINNING);
    	GridData inputConversionData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
    	setText(inputConversion, inputConversionData);

    	Label output = new Label(outputComposite, SWT.BEGINNING);
    	GridData outputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
    	setText(output, outputData);

        
        // Mutating User Input	
        enterButton.addListener(SWT.Selection, event -> {
        	String[] vals = new String[3];
        	vals[0] = textBox.getText().trim();
        	
            Index runner = new Index();
            vals = runner.GUIMode(vals[0]);
            
            input.setText(vals[0]);
            inputConversion.setText(vals[1]);
            output.setText(vals[2]);
        });
        
//        Composite thirdRowComposite = new Composite(shell, SWT.NONE);
//        thirdRowComposite.setLayout(new GridLayout(2, true)); // Two columns for the two buttons
//        thirdRowComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
//        
//        Button showWordToNum = new Button(shell, SWT.CENTER);
//        showWordToNum.setText("Enter");
//    	shell.setDefaultButton(showWordToNum);
//    	showWordToNum.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
//    	
//        Button showNumToWord = new Button(shell, SWT.CENTER);
//        showNumToWord.setText("Enter");
//    	shell.setDefaultButton(showNumToWord);
//    	showNumToWord.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
        
        displayBackgroundWithColor();
        
        
        // Initialize shell
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) { 
            	display.sleep();
        	}
        }
        display.dispose();
        System.out.println("Display Closed");
    }
}
