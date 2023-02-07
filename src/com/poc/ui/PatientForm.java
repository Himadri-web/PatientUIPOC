package com.poc.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.poc.model.Patient;

public class PatientForm {
	
	private static PatientForm patientForm;
	
		Shell shellCreate;
	
	public static PatientForm getPatientForm() {
		return patientForm;
	}
	public static void setPatientForm(PatientForm view) {
		PatientForm.patientForm = view;
	}
	
	private void init() {
		System.out.println("Inside Main!");
		Display display = new Display();
		shellCreate = new Shell();
		createNewPatient(display, shellCreate);
		
	}
	
	private void createNewPatient(Display display, Shell shell) {
		shell.setText("Welcome to Health Portal");
		Label label = new Label(shell, SWT.CENTER);
		Font font = new Font(display,"Cambria", 22, SWT.ITALIC);
		label.setFont(font);
		label.setBounds(500, 5, 700, 31);
		label.setText("Patient Details form");
	    creatPatientDetails(display, shell);// set widgets size to their preferred size
        label.pack();
		shell.open();
		shell.layout();
		while (!shellCreate.isDisposed()) { 
            if (!display.readAndDispatch()) 
             { display.sleep();} 
        }
		
		display.dispose();
		
	}
	
	private void creatPatientDetails(Display display, Shell shell) {
		Label name = new Label(shell, SWT.NONE);
		name.setBounds(100, 80, 900, 31);
		name.setText("Name");
		Text text = new Text(shell, SWT.NONE);
		text.setText("This is the text");
		text.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
		//text.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		text.setBounds(200, 80, 900, 31);
		
		Button locateButton = new Button(shell, SWT.PUSH);
		locateButton.setText("Pateint Locator");
		locateButton.setBounds(600, 200, 300, 30);
		
		Button create = new Button(shell, SWT.PUSH);
		create.setText("CREATE");
		create.setVisible(true);
		create.setBounds(250, 200, 80, 30);
		
		Button modify = new Button(shell, SWT.PUSH);
		modify.setText("MODIFY");
		modify.setVisible(true);
		modify.setBounds(350, 200, 80, 30);
		
		Button delete = new Button(shell, SWT.PUSH);
		delete.setText("DELETE");
		delete.setVisible(true);
		delete.setBounds(450, 200, 80, 30);// register listener for the selection event
		
		locateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Called!");
				//PatientInfo patView = new PatientInfo(display);
				//patView.setView(patView);
				//patView.setPatientDetails(false);
				// System.out.println(e.getSource());
				
			}
		});
		
		create.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			System.out.println("Called!");
			createOrModifyPatient(display, shell, false);
			}
			});
		
		modify.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			System.out.println("Called!");
			createOrModifyPatient(display, shell, false);
			}
			});
		
		name.pack();
		text.pack();
		create.pack();
		modify.pack();
		locateButton.pack();
		shell.layout();
		
	}
	
	protected void createOrModifyPatient(Display display, Shell shell, boolean b) {
	
		System.out.println("inside create patient process");
		System.out.println("");
		}
	
	public static void main(String[] args) {
		System.out.println("Main!!!!");
		patientForm = new PatientForm();
		patientForm.init();
		System.out.println("End!!!!");
	}

}
