package com.poc.practice;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Test1 {

	public static void main(String[] args) {
		initUI();
	}
	
	private static void initUI() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Welcome to Patient Health Portal");
		shell.setSize(300, 200);
		shell.open();
		
		Image img = new Image(display, 100, 100);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBackgroundImage(img);
		//label.setBackground("C:\\Users\\HS106406\\workspace\\Eclipse\\PatientUIPOC\\img\\healthcare_image.jfif");
		
		Button createButton = new Button(shell, SWT.PUSH);
		createButton.setText("Create");
		createButton.setBounds(75, 40, 80, 30);
	
		
		Button updateButton = new Button(shell, SWT.PUSH);
		updateButton.setText("Modify");
		updateButton.setSize(80, 30);
//		updateButton.setL(80, 30);
		updateButton.setBounds(200, 40, 80, 30);
		
		Button deleteButton = new Button(shell, SWT.PUSH);
		deleteButton.setText("Delete");
		deleteButton.setBounds(325, 40, 80, 30);
		
		while (!shell.isDisposed()) { 
            if (!display.readAndDispatch()) { 
            	display.sleep();
            } 
        }
        // disposes all associated windows and their components
        display.dispose();		
	}

}
