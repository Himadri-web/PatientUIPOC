package com.poc.practice;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Test2 {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell();
		shell.setText("SWT Demo");
		shell.setSize(300, 200);
		shell.open();
		
		Button button1 = new Button(shell, SWT.PUSH);
		button1.setText("Close");
		button1.setBounds(75, 40, 80, 30);
		button1.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				display.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button2 = new Button(shell, SWT.PUSH);
		button2.setText("Cancel");
		button2.setSize(80, 30);
		button2.setLocation(75, 75);
		
		
		while (!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
			
		}
		
		//display.dispose();
		
		

		
	}

}
