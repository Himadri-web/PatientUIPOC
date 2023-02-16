package com.poc.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.poc.constant.PatientConstants;

public class PatientCreateForm {
	
	//Display display = new Display ();
	//Shell shell = new Shell(display);
	public Text patientNameText;

	//public Text patientDOBText;

	public Button femaleRadio;

	public Button maleRadio;

	public Button otherRadio;

	public Text patientAddressTypeText;

	public Text patientPermanentAddressTypeText;

	public Text patientPresentStreetText;

	public Text patientPermanentStreetText;

	public Text patientPresentCityText;

	public Text patientPermanentCityText;

	public Text patientPresentStateText;

	public Text patientPermanentStateText;

	public Text patientPresentPostalCodeText;

	public Text patientPermanentPostalCodeText;

	public Text phoneTypeText;

	public Text altphoneTypeText;

	public Text phoneNumberText;

	public Text altPhoneNumberText;

	public Text countryCodeText;

	public Text altCountryCodeText;
	
	public DateTime patientDOB;
	/**
	 * create patient form
	 * @param display
	 * @param shell
	 * @param labelStartingPosition
	 * @param textStartingPosition
	 * @param labelWidth
	 */
	
	public void patientCreatePage(Display display, Shell shell, int labelStartingPosition, int textStartingPosition, int labelWidth) {
		shell.setText("Welcome to Cerner Health Care");
		shell.setFont(SWTResourceManager.getFont("Patient UI", 16, SWT.BOLD));

		Label label = new Label(shell, SWT.CENTER);
		label.setBounds(250, 5, 200, 40);
		label.setText("Patient Registration Form");
		label.setFont(new Font(display, "boldfont", 20, SWT.BOLD));
		
		Label patientName = new Label(shell, SWT.NONE);
		patientName.setText("Patient Name*");
		patientName.setBounds(labelStartingPosition, 50, labelWidth, 30);

		patientNameText = new Text(shell, SWT.BORDER);
		patientNameText.setBounds(textStartingPosition, 50, 200, 20);
		patientNameText.setTextLimit(40);

		// Patient Date of Birth
		Label patientDateOfBirth = new Label(shell, SWT.NONE);
		patientDateOfBirth.setText("Date Of Birth*");
		patientDateOfBirth.setBounds(labelStartingPosition, 80, labelWidth, 30);

		// Gender
		patientDOB = new DateTime(shell, SWT.BORDER);
		patientDOB.setBounds(textStartingPosition, 80, 100, 20);
		
		
		//patientDOBText = new Text(shell, SWT.BORDER);
		//patientDOBText.setBounds(textStartingPosition, 80, 100, 20);
		//patientDOBText.setTextLimit(25);

		Label patientGender = new Label(shell, SWT.RADIO);
		patientGender.setText("Gender*");
		patientGender.setBounds(labelStartingPosition, 110, labelWidth, 30);

		femaleRadio = new Button(shell, SWT.RADIO);
		femaleRadio.setText("Female");
		femaleRadio.setBounds(textStartingPosition, 110, 60, 30);

		maleRadio = new Button(shell, SWT.RADIO);
		maleRadio.setText("Male");
		maleRadio.setBounds(textStartingPosition + 70, 110, 60, 30);

		otherRadio = new Button(shell, SWT.RADIO);
		otherRadio.setText("Others");
		otherRadio.setBounds(textStartingPosition + 130, 110, 60, 30);

		// Address
		Label address = new Label(shell, SWT.NONE);
		address.setText("Please fill the Address of the Patient*");
		address.setFont(SWTResourceManager.getFont("Patient UI", 9, SWT.BOLD));
		address.setBounds(labelStartingPosition, 150, 500, 30);

		Label addressType = new Label(shell, SWT.NONE);
		addressType.setText("Address Type*");
		addressType.setBounds(labelStartingPosition, 185, labelWidth, 30);

		patientAddressTypeText = new Text(shell, SWT.BORDER);
		patientAddressTypeText.setBounds(textStartingPosition, 185, 200, 20);
		patientAddressTypeText.setText(PatientConstants.PRESENT_ADDRESS_TYPE);
		patientAddressTypeText.setEnabled(false);

		patientPermanentAddressTypeText = new Text(shell, SWT.BORDER);
		patientPermanentAddressTypeText.setBounds(380, 185, 200, 20);
		patientPermanentAddressTypeText.setText(PatientConstants.PERMANENT_ADDRESS_TYPE);
		patientPermanentAddressTypeText.setEnabled(false);

		Label street = new Label(shell, SWT.NONE);
		street.setText("Street*");
		street.setBounds(labelStartingPosition, 210, labelWidth, 30);

		patientPresentStreetText = new Text(shell, SWT.BORDER);
		patientPresentStreetText.setBounds(textStartingPosition, 210, 200, 20);
		patientPresentStreetText.setTextLimit(25);

		patientPermanentStreetText = new Text(shell, SWT.BORDER);
		patientPermanentStreetText.setBounds(380, 210, 200, 20);
		patientPermanentStreetText.setTextLimit(25);

		Label city = new Label(shell, SWT.NONE);
		city.setText("City*");
		city.setBounds(labelStartingPosition, 240, labelWidth, 30);

		patientPresentCityText = new Text(shell, SWT.BORDER);
		patientPresentCityText.setBounds(textStartingPosition, 240, 200, 20);
		patientPresentCityText.setTextLimit(25);

		patientPermanentCityText = new Text(shell, SWT.BORDER);
		patientPermanentCityText.setBounds(380, 240, 200, 20);
		patientPermanentCityText.setTextLimit(25);

		Label state = new Label(shell, SWT.NONE);
		state.setText("State*");
		state.setBounds(labelStartingPosition, 270, labelWidth, 30);

		patientPresentStateText = new Text(shell, SWT.BORDER);
		patientPresentStateText.setBounds(textStartingPosition, 270, 200, 20);
		patientPresentStateText.setTextLimit(25);

		patientPermanentStateText = new Text(shell, SWT.BORDER);
		patientPermanentStateText.setBounds(380, 270, 200, 20);
		patientPermanentStateText.setTextLimit(25);

		Label postalCode = new Label(shell, SWT.NONE);
		postalCode.setText("Postal Code*");
		postalCode.setBounds(labelStartingPosition, 300, labelWidth, 30);

		patientPresentPostalCodeText = new Text(shell, SWT.BORDER);
		patientPresentPostalCodeText.setBounds(textStartingPosition, 300, 200, 20);
		patientPresentPostalCodeText.setTextLimit(25);

		patientPermanentPostalCodeText = new Text(shell, SWT.BORDER);
		patientPermanentPostalCodeText.setBounds(380, 300, 200, 20);
		patientPermanentPostalCodeText.setTextLimit(25);

		// Telephone

		Label telephone = new Label(shell, SWT.NONE);
		telephone.setText("Patient contact details");
		telephone.setFont(SWTResourceManager.getFont("Patient UI", 9, SWT.BOLD));
		telephone.setBounds(labelStartingPosition, 330, 500, 20);

		Label phoneType = new Label(shell, SWT.NONE);
		phoneType.setText("Phone Type*");
		phoneType.setBounds(labelStartingPosition, 360, labelWidth, 30);

		phoneTypeText = new Text(shell, SWT.BORDER);
		phoneTypeText.setBounds(textStartingPosition, 360, 200, 20);
		phoneTypeText.setTextLimit(25);
		phoneTypeText.setText("Primary Contact");
		phoneTypeText.setEnabled(false);

		altphoneTypeText = new Text(shell, SWT.BORDER);
		altphoneTypeText.setBounds(380, 360, 200, 20);
		altphoneTypeText.setTextLimit(25);
		altphoneTypeText.setText("Alternate Contact");
		altphoneTypeText.setEnabled(false);
		// Phone number
		Label phoneNumber = new Label(shell, SWT.NONE);
		phoneNumber.setText("Phone Number*");
		phoneNumber.setBounds(labelStartingPosition, 390, labelWidth, 30);

		phoneNumberText = new Text(shell, SWT.BORDER);
		phoneNumberText.setBounds(textStartingPosition, 390, 200, 20);
		phoneNumberText.setTextLimit(25);

		altPhoneNumberText = new Text(shell, SWT.BORDER);
		altPhoneNumberText.setBounds(380, 390, 200, 20);
		altPhoneNumberText.setTextLimit(25);

		// country code
		Label countryCode = new Label(shell, SWT.NONE);
		countryCode.setText("Country Code*");
		countryCode.setBounds(labelStartingPosition, 420, labelWidth, 30);

		countryCodeText = new Text(shell, SWT.BORDER);
		countryCodeText.setBounds(textStartingPosition, 420, 200, 20);
		countryCodeText.setTextLimit(25);

		altCountryCodeText = new Text(shell, SWT.BORDER);
		altCountryCodeText.setBounds(380, 420, 200, 20);
		altCountryCodeText.setTextLimit(25);

		label.pack();
		patientName.pack();
		patientDateOfBirth.pack();
		patientGender.pack();
		addressType.pack();
		street.pack();
		city.pack();
		state.pack();
		postalCode.pack();

		phoneType.pack();
		phoneNumber.pack();
		countryCode.pack();
	}

}
