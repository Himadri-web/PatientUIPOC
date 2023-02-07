package com.poc.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Text;

public class UITest1 {
	private static Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		FillLayout fillLayout = new FillLayout();
		fillLayout.type=SWT.VERTICAL;
		
		Button btnCreate = new Button(shell, SWT.NONE);
		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Inside Create button click");
				//bindingContext.bindValue(yourTextObserveTextObserveWidget, yourModelTemplateObserveValue, null, null)
			
			}
		});
		btnCreate.setBounds(41, 194, 75, 25);
		btnCreate.setText("Create");
		
		Button btnModify = new Button(shell, SWT.NONE);
		btnModify.setBounds(160, 194, 75, 25);
		btnModify.setText("Modify");
		
		Button btnDelete = new Button(shell, SWT.NONE);
		btnDelete.setBounds(279, 194, 75, 25);
		btnDelete.setText("Delete");
		btnDelete.setEnabled(false);
		
		Button btnPatientLocator = new Button(shell, SWT.NONE);
		btnPatientLocator.setBounds(147, 29, 230, 25);
		btnPatientLocator.setText("Patient Locator");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(107, 60, 76, 21);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(26, 60, 55, 15);
		label.setText("Name");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
