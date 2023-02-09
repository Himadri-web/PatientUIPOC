package com.poc.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class PopupWindow {
	
	public static Integer showDialogeBox(Shell shell, int iconWorking, String text, String message) {
		MessageBox messageBox;
		messageBox = new MessageBox(shell, iconWorking);
		messageBox.setText(text);
		messageBox.setMessage(message);
		return messageBox.open();
	}

}
