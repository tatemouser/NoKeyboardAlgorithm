package display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import main.Index;

public class RowOne {
	private Composite parent;
	private Composite parentComposite;
    private Button enterButton;
    private Text textBox;         
    
    // Quick adding of composites to parent
    public void addCompositeRow(Composite temp) {
    	temp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	temp.setLayout(new GridLayout(3, true)); // 3 columns in the second row
    	temp.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    }
    
    // Input Label / Text Box / Enter Button
	public void fillFirstComposite(Composite inputComposite) {		
    	Label inputLabel = new Label(inputComposite, SWT.RIGHT); 
    	inputLabel.setText("Type Sentence");
    	GridData inputLabelData = new GridData(SWT.FILL, SWT.CENTER, false, false);
    	inputLabel.setLayoutData(inputLabelData);
    	inputLabel.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));

    	Text textBox = new Text(inputComposite, SWT.BORDER | SWT.SINGLE); 
    	GridData textBoxData = new GridData(SWT.FILL, SWT.CENTER, true, false);
    	textBox.setLayoutData(textBoxData);
    	textBox.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    	textBox.setText("how do you say hello world");
    	this.textBox = textBox;
    	
    	Button enterButton = new Button(inputComposite, SWT.NONE); 
    	enterButton.setText("Enter");
    	enterButton.setBounds(10, 10, 100, 40); // Set the x, y, width, and height
    	this.enterButton = enterButton;
	}
	
	// Titles: "Input" / "Word to Num" / "Num to Word"
    private void fillSecondComposite(Composite parent, String labelText) {
        Label input = new Label(parent, SWT.CENTER);
        input.setText(labelText);
        GridData inputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        setText(input, inputData);
    }
	
    public void setText(Label input, GridData inputData) {
        input.setLayoutData(inputData);
        input.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    }

    public void setBackgroundColor(int swtColorConstant) {
        if (parentComposite != null) {
            Color color = Display.getCurrent().getSystemColor(swtColorConstant);
            parentComposite.setBackground(color);
        }
    }

    
    public RowOne(Composite parent) { 
    	// Reference to main shell composite
        this.parent = parent;
        // Reference to row one main composite
    	this.parentComposite = new Composite(parent, SWT.FLAT);
    	
    	GridData parentGridData = new GridData(SWT.FILL, SWT.FILL, true, false); //Set false to remove extra space, compact composite
    		parentComposite.setLayoutData(parentGridData);
    		parentComposite.setLayout(new GridLayout(1, false));

    	
    	// Add 3 rows to parentComposite
    	Composite inputComposite = new Composite(parentComposite, SWT.NONE);
    		addCompositeRow(inputComposite);
    	Composite outputTitleComposite = new Composite(parentComposite, SWT.NONE);
    		addCompositeRow(outputTitleComposite);
    	Composite outputComposite = new Composite(parentComposite, SWT.NONE);
    		addCompositeRow(outputComposite);
    	

    	// Fill 3 composite rows
    	fillFirstComposite(inputComposite);
    	
    	fillSecondComposite(outputTitleComposite, "Input");
    	fillSecondComposite(outputTitleComposite, "Word to Number");
    	fillSecondComposite(outputTitleComposite, "Number to Word");
        	
        // Third composite, Show input / Show num to word / Show word to num
    	Label input = new Label(outputComposite, SWT.CENTER);
    	GridData inputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
    	setText(input, inputData);

    	Label inputConversion = new Label(outputComposite, SWT.CENTER);
    	GridData inputConversionData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
    	setText(inputConversion, inputConversionData);

    	Label output = new Label(outputComposite, SWT.CENTER);
    	GridData outputData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
    	setText(output, outputData);

        enterButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Handle the button click event here
                String[] vals = new String[3];
                vals[0] = textBox.getText().trim();
                
                Index runner = new Index();
                vals = runner.GUIMode(vals[0]);
                
                input.setText(vals[0]);
                inputConversion.setText(vals[1]);
                output.setText(vals[2]);
                UIWindow.passVals(vals);
                UIWindow.updateWindow();
                }
        });
    }
}
