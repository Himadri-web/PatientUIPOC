package com.poc.util;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
/**
 * This class to provide popup window finctionality
 * @author HS106406
 * @version 1.0
 */
public class PopupWindow {
	
	/**
	 * This method to show dialog box
	 * @param shell
	 * @param iconWorking
	 * @param text
	 * @param message
	 * @return
	 */
	public static Integer showDialogeBox(Shell shell, int iconWorking, String text, String message) {
		MessageBox messageBox;
		messageBox = new MessageBox(shell, iconWorking);
		messageBox.setText(text);
		messageBox.setMessage(message);
		return messageBox.open();
	}

}
