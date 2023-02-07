package com.poc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Test {

	public static void main(String[] args) {
		System.out.println("Hi");
		
		Display display = new Display();
		Shell shell = new Shell(display);
        shell.open();
        
        Button createButton = new Button(shell, SWT.PUSH);
		createButton.setText("Close");
		createButton.setBounds(75, 40, 80, 30);

        while (!shell.isDisposed()) { 
            if (!display.readAndDispatch()) 
             { display.sleep();} 
        }

        // disposes all associated windows and their components
        display.dispose();
	}

}
