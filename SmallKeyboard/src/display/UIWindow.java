package display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import main.Index;

import org.eclipse.swt.widgets.Button;
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
    
    
    
    
    public void run() {

    	setShell();
    	
        // Input Label / Text Box / Enter Button
        Label inputLabel = new Label(shell, SWT.RIGHT);
        	inputLabel.setText("Type Sentence:");
        	inputLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        	inputLabel.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_GRAY));

        Text textBox = new Text(shell, SWT.BORDER | SWT.SINGLE);
        	GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
        	data.minimumWidth = 200;
        	textBox.setLayoutData(data);
        	textBox.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
        	textBox.setText("how do you say hello world");
        	
        Button enterButton = new Button(shell, SWT.LEFT);
        	enterButton.setText("Enter");
        	shell.setDefaultButton(enterButton);
        	enterButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
        	
        	
        	
        // Changing Text Boxes -> Show input / Show num to word / Show word to num
        Label input = new Label(shell, SWT.BEGINNING);
        	GridData inputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        	setText(input, inputData);
        
        Label inputConversion = new Label(shell, SWT.BEGINNING);
        	GridData inputConversionData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        	setText(inputConversion, inputConversionData);

        Label output = new Label(shell, SWT.BEGINNING);
        	GridData outputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        	setText(output, outputData);
        
        // Mutating User Input	
        enterButton.addListener(SWT.Selection, event -> {
        	String[] vals = new String[3];
        	vals[0] = textBox.getText().trim();
        	
            Index runner = new Index();
            vals = runner.GUIMode(vals[0]);
            
            input.setText("Input: " + vals[0]);
            inputConversion.setText("Converted Input: " + vals[1]);
            output.setText("Output: " + vals[2]);
        });
        
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
