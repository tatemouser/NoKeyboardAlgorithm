package display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

public class UIWindow {
    private String enteredText = ""; // Variable to store the entered text

    public void run() {
        Display display = new Display();

        Shell shell = new Shell(display);
        shell.setText("Word Conversion");

        int screenWidth = shell.getDisplay().getClientArea().width;
        int screenHeight = shell.getDisplay().getClientArea().height;
        int width = shell.getBounds().width;
        int height = shell.getBounds().height;
        
        // Size and center
        shell.setSize(screenWidth / 2, screenHeight / 2);
        int x = (screenWidth - width) / 2;
        int y = (screenHeight - height) / 2;
        shell.setLocation(x, y);
        
        GridLayout layout = new GridLayout(5, false);
        shell.setLayout(layout);

        Text textBox = new Text(shell, SWT.BORDER);
        GridData textGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        //textGridData.horizontalSpan = 6;
        textBox.setLayoutData(textGridData);

        Button enterButton = new Button(shell, SWT.PUSH);
        enterButton.setText("Enter");

        Label resultLabel = new Label(shell, SWT.NONE);
        resultLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        enterButton.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                if (event.widget == enterButton) {
                    enteredText = textBox.getText(); // Assign the text to the variable
                    resultLabel.setText("Original Input: " + enteredText);
                    System.out.println("Entered text: " + enteredText); // For demonstration
                }
            }
        });

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
