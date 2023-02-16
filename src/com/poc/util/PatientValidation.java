/**
 * 
 */
package com.poc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This class used to validate the patient form fileds
 * @author HS106406
 * @version 1.0
 */
public class PatientValidation {

	public Boolean validateNumbersInput(String input) {
		String regex = "[0-9]+";
		return validate(input, regex);
	}

	public boolean validateAlphabetsInput(String input) {
		String regex = "[^ ][A-Za-z ]+";
		return validate(input, regex);
	}

	public boolean validateAlphaNumericInput(String input) {
		String regex = "[^ ][A-Za-z0-9#, ]+";// [0-9 ]*[# ]
		return validate(input, regex);

	}
	
	public boolean validateMobileNumber(String input) {
		String regex = "\\d{10}";// [0-9 ]*[# ]
	
		return validate(input, regex);

	}

	private Boolean validate(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches() ? true : false;
	}

	public Boolean validatePatientData( String patientName, String patientDOB, Shell shell) {
		Boolean isValid = validateAlphabetsInput(patientName);
		if(!isValid) {
			PopupWindow.showDialogeBox(shell, SWT.ICON_ERROR, "Error",
					"Please provide valid Patient Name");
		}
		return isValid;
	}

	public Boolean validatePatientAddressData(String patientAddressType, String patientStreet,
			String patientCity, String patientState, String patientPostalCode, Shell shell, String type) {
		Boolean isValid = true;
		if(isValid && (!validateAlphabetsInput(patientAddressType))) {
			isValid = false;
			PopupWindow.showDialogeBox(shell, SWT.ICON_ERROR, "Error",
					"Please provide valid " + type + " Address Type");
		}
		if(isValid && (!validateAlphaNumericInput(patientStreet))) {
			isValid = false;
			PopupWindow.showDialogeBox(shell, SWT.ICON_ERROR, "Error",
					"Please provide valid " + type+ " Street");
		}
		if(isValid && (!validateAlphabetsInput(patientCity))) {
			isValid = false;
			PopupWindow.showDialogeBox(shell, SWT.ICON_ERROR, "Error",
					"Please provide valid " + type+ " City");
		}
		if(isValid && (!validateAlphabetsInput(patientState))) {
			isValid = false;
			PopupWindow.showDialogeBox(shell, SWT.ICON_ERROR, "Error",
					"Please provide valid " + type+ " State");
		}
		if(isValid && (!validateNumbersInput(patientPostalCode))) {
			isValid = false;
			PopupWindow.showDialogeBox(shell, SWT.ICON_ERROR, "Error",
					"Please provide valid " + type+ " Postal Code");
		}
				
		return isValid;
			
	}

	public Boolean validatePatientContactData(String phoneType, String mobileNumber, String countryCode, Shell shell, String type) {
		Boolean isValid = true;
		if(isValid && (!validateAlphabetsInput(phoneType))) {
			isValid = false;
			PopupWindow.showDialogeBox(shell, SWT.ICON_ERROR, "Error",
					"Please provide valid " + type + " Phone Type");
		}
		
		if(isValid && (!validateMobileNumber(mobileNumber))) {
			isValid = false;
			PopupWindow.showDialogeBox(shell, SWT.ICON_ERROR, "Error",
					"Please provide valid " + type + " Mobile Number. It should be 10 digits");
		}
		
		if(isValid && (!validateNumbersInput(countryCode))) {
			isValid = false;
			PopupWindow.showDialogeBox(shell, SWT.ICON_ERROR, "Error",
					"Please provide valid " + type + " Country Code");
		}
		//return validateAlphabetsInput(phoneType) && validateMobileNumber(mobileNumber) && validateNumbersInput(countryCode);
		
		return isValid;
		
	}

	
	
}
