package com.poc.ui;



import java.io.IOException;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.poc.RestClient;
import com.poc.model.Address;
import com.poc.model.Patient;
import com.poc.model.Telephone;

public class PatientEntry {
	
	Shell shell;
	
	public Text patientNameText;
	
	public Text patientDOBText;
	
	public Button femaleRadio; 
	
	public Button maleRadio;
	
	public Button otherRadio;
	
	public Text patientAddressTypeText; 
	
	public Text patientPermanentAddressTypeText;
	
	public Text patientStreetText;
	
	public Text patientPermanentStreetText;
	
	public Text patientCityText;
	
	public Text patientPermanentCityText;
	
	public Text patientStateText;
	
	public Text patientPermanentStateText;
	
	public Text patientPostalCodeText;
	
	public Text patientPermanentPostalCodeText;
	
	public Text phoneTypeText;
	
	public Text altphoneTypeText;
	
	public Text phoneNumberText;
	
	public Text altPhoneNumberText;
	
	public Text countryCodeText;
	
	public Text altCountryCodeText;
	
	public static PatientEntry patientEntry;
	
	public static void main(String[] args) {
		patientEntry = new PatientEntry();
		patientEntry.init();
		
	}

	private void init() {
		Display display = new Display();
		shell = new Shell();
		createNewPatient(display, shell);
	}

	//Create new patient form
	private void createNewPatient(Display display, Shell shell2) {
		shell.setText("Welcome to Patient health care");
		shell.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		
		Label label = new Label(shell, SWT.CENTER);
		label.setBounds(500, 5, 700, 30);
		label.setText("Patient Registration Form");
		createPatientDetailsSection(display, shell);
		label.pack();
		shell.open();
		shell.layout();
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
			
		}
		
	}

	private void createPatientDetailsSection(Display display, Shell shell2) {
		//Patient Name
		int labelStartingPosition = 50;
		int textStartingPosition = 160;
		int labelWidth = 100;
		Label patientName = new Label(shell, SWT.NONE);
		patientName.setText("Patient Name*");
		patientName.setBounds(labelStartingPosition, 50, labelWidth, 30);
		
		patientNameText = new Text(shell, SWT.BORDER);
		patientNameText.setBounds(textStartingPosition, 50, 300, 30);
		patientNameText.setTextLimit(25);
		
		//Patient Date of Birth
		Label patientDateOfBirth = new Label(shell, SWT.NONE);
		patientDateOfBirth.setText("Date Of Birth*");
		patientDateOfBirth.setBounds(labelStartingPosition, 90, labelWidth, 30);
		
		//Gender
		patientDOBText = new Text(shell, SWT.BORDER);
		patientDOBText.setBounds(textStartingPosition, 90, 300, 30);
		patientDOBText.setTextLimit(25);
		
		Label patientGender = new Label(shell, SWT.RADIO);
		patientGender.setText("Gender*");
		patientGender.setBounds(labelStartingPosition, 130, labelWidth, 30);
		
		femaleRadio = new Button(shell, SWT.RADIO);
		femaleRadio.setText("Female");
		femaleRadio.setBounds(textStartingPosition, 130, 80, 30);
		
		maleRadio = new Button(shell, SWT.RADIO);
		maleRadio.setText("Male");
		maleRadio.setBounds(textStartingPosition+90, 130, 80, 30);
		
		otherRadio = new Button(shell, SWT.RADIO);
		otherRadio.setText("Other");
		otherRadio.setBounds(textStartingPosition+180, 130, 80, 30);
		
		//Address
		Label address = new Label(shell, SWT.NONE);
		address.setText("Please fill the Address of the Patient*");
		address.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		address.setBounds(labelStartingPosition, 170, 900, 30);
		
		Label addressType = new Label(shell, SWT.NONE);
		addressType.setText("Address Type*");
		addressType.setBounds(labelStartingPosition,200, labelWidth, 30);	
		
		patientAddressTypeText = new Text(shell, SWT.BORDER);
		patientAddressTypeText.setBounds(textStartingPosition, 200, 300, 20);
		patientAddressTypeText.setText("Present Address");
		patientAddressTypeText.setEnabled(false);
		
		patientPermanentAddressTypeText = new Text(shell, SWT.BORDER);
		patientPermanentAddressTypeText.setBounds(480, 200, 300, 20);
		patientPermanentAddressTypeText.setText("Permanent Address");
		patientPermanentAddressTypeText.setEnabled(false);
		
		
		Label street = new Label(shell, SWT.NONE);
		street.setText("Street*");
		street.setBounds(labelStartingPosition, 240, labelWidth, 30);
		
		patientStreetText = new Text(shell, SWT.BORDER);
		patientStreetText.setBounds(textStartingPosition, 240, 300, 20);
		patientStreetText.setTextLimit(25);
		
		patientPermanentStreetText = new Text(shell, SWT.BORDER);
		patientPermanentStreetText.setBounds(480, 240, 300, 20);
		patientPermanentStreetText.setTextLimit(25);
		
		Label city = new Label(shell, SWT.NONE);
		city.setText("City*");
		city.setBounds(labelStartingPosition, 280, labelWidth, 30);
		
		patientCityText = new Text(shell, SWT.BORDER);
		patientCityText.setBounds(textStartingPosition, 280, 300, 20);
		patientCityText.setTextLimit(25);
		
		patientPermanentCityText = new Text(shell, SWT.BORDER);
		patientPermanentCityText.setBounds(480, 280, 300, 20);
		patientPermanentCityText.setTextLimit(25);
		
		Label state = new Label(shell, SWT.NONE);
		state.setText("State*");
		state.setBounds(labelStartingPosition, 320, labelWidth, 30);
		
		patientStateText = new Text(shell, SWT.BORDER);
		patientStateText.setBounds(textStartingPosition, 320, 300, 20);
		patientStateText.setTextLimit(25);
		
		patientPermanentStateText = new Text(shell, SWT.BORDER);
		patientPermanentStateText.setBounds(480, 320, 300, 20);
		patientPermanentStateText.setTextLimit(25);
		
		Label postalCode = new Label(shell, SWT.NONE);
		postalCode.setText("Postal Code*");
		postalCode.setBounds(labelStartingPosition, 360, labelWidth, 30);
		
		patientPostalCodeText = new Text(shell, SWT.BORDER);
		patientPostalCodeText.setBounds(textStartingPosition, 360, 300, 20);
		patientPostalCodeText.setTextLimit(25);
		
		patientPermanentPostalCodeText = new Text(shell, SWT.BORDER);
		patientPermanentPostalCodeText.setBounds(480, 360, 300, 20);
		patientPermanentPostalCodeText.setTextLimit(25);
		
		//Telephone
		
		Label telephone = new Label(shell, SWT.NONE);
		telephone.setText("Patient contact details");
		telephone.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		telephone.setBounds(labelStartingPosition, 400, 900, 20);
		
		Label phoneType = new Label(shell, SWT.NONE);
		phoneType.setText("Phone Type*");
		phoneType.setBounds(labelStartingPosition, 430, labelWidth, 30);
		
		phoneTypeText = new Text(shell, SWT.BORDER);
		phoneTypeText.setBounds(textStartingPosition, 430, 300, 20);
		phoneTypeText.setTextLimit(25);
		phoneTypeText.setText("Primary Contact");
		phoneTypeText.setEnabled(false);
		
		altphoneTypeText = new Text(shell, SWT.BORDER);
		altphoneTypeText.setBounds(480, 430, 300, 20);
		altphoneTypeText.setTextLimit(25);
		altphoneTypeText.setText("Alternate Contact");
		altphoneTypeText.setEnabled(false);
		//Phone number
		Label phoneNumber = new Label(shell, SWT.NONE);
		phoneNumber.setText("Phone Number*");
		phoneNumber.setBounds(labelStartingPosition, 470, labelWidth, 30);
		
		phoneNumberText = new Text(shell, SWT.BORDER);
		phoneNumberText.setBounds(textStartingPosition, 470, 300, 20);
		phoneNumberText.setTextLimit(25);
		
		altPhoneNumberText = new Text(shell, SWT.BORDER);
		altPhoneNumberText.setBounds(480, 470, 300, 20);
		altPhoneNumberText.setTextLimit(25);
		
		//country code
		Label countryCode = new Label(shell, SWT.NONE);
		countryCode.setText("Country Code*");
		countryCode.setBounds(labelStartingPosition, 510, labelWidth, 30);
		
		countryCodeText = new Text(shell, SWT.BORDER);
		countryCodeText.setBounds(textStartingPosition, 510, 300, 20);
		countryCodeText.setTextLimit(25);
		
		altCountryCodeText = new Text(shell, SWT.BORDER);
		altCountryCodeText.setBounds(480, 510, 300, 20);
		altCountryCodeText.setTextLimit(25);
		
		  
		
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
		  
		  Button createButton = new Button(shell, SWT.PUSH);
		  createButton.setText("Create");
		  createButton.setBounds(labelStartingPosition, 550, labelWidth, 30);
		  createButton.addSelectionListener(new SelectionAdapter() {
			  public void widgetSelected(SelectionEvent event) {
				  System.out.println("Insid Create event");
				  
				  createPatient();
				  
				  System.out.println("End of Create event");
			  }
		  });
		  
		  
		  Button updateButton = new Button(shell, SWT.PUSH);
		  updateButton.setText("Modify");
		  updateButton.setBounds(labelStartingPosition+120, 550, labelWidth, 30);
		  updateButton.setEnabled(false);
		  
	}

	protected void createPatient() {
		
		
		String patientName = patientNameText.getText();
		String patientDOB = patientDOBText.getText();
		Boolean femaleRadioButton = femaleRadio.getSelection();
		Boolean maleRadioButton = maleRadio.getSelection();
		Boolean otherRadioButton = otherRadio.getSelection();
		
		System.out.println("patientName: "+ patientName);
		System.out.println("patientDOB: "+ patientDOB);
		System.out.println("femaleRadioButton: "+ femaleRadioButton);
		System.out.println("maleRadioButton: "+ maleRadioButton);
		System.out.println("otherRadioButton: "+ otherRadioButton);
		
		String patientPresentAddressType = patientAddressTypeText.getText();
		String patientPermanentAddressType = patientPermanentAddressTypeText.getText();
		String patientPresentStreet = patientStreetText.getText();
		String patientPermanentStreet = patientPermanentStreetText.getText();
		String patientPresentCity = patientCityText.getText();
		String patientPermanentCity = patientPermanentCityText.getText();
		String patientPresentState = patientStateText.getText();
		String patientPermanentState = patientPermanentStateText.getText();
		String patientPresentPostalCode = patientPostalCodeText.getText();
		String patientPermanentPostalCode = patientPermanentPostalCodeText.getText();
		
		System.out.println("patientPresentAddressType: "+ patientPresentAddressType);
		System.out.println("patientPermanentAddressType: "+ patientPermanentAddressType);
		System.out.println("patientPresentStreet: "+ patientPresentStreet);
		System.out.println("patientPermanentStreet: "+ patientPermanentStreet);
		System.out.println("patientPresentCity: "+ patientPresentCity);
		System.out.println("patientPermanentCity: "+ patientPermanentCity);
		System.out.println("patientPresentState: "+ patientPresentState);
		System.out.println("patientPermanentState: "+ patientPermanentState);
		System.out.println("patientPresentPostalCode: "+ patientPresentPostalCode);
		System.out.println("patientPermanentPostalCode: "+ patientPermanentPostalCode);
		
		String priPhoneType = phoneTypeText.getText();
		String altPhoneType = altphoneTypeText.getText();
		String priPhoneNumber = phoneNumberText.getText();
		String altPhoneNumber = altPhoneNumberText.getText();
		String pricountryCode = countryCodeText.getText();
		String altCountryCode = altCountryCodeText.getText();
		
		System.out.println("priPhoneType: "+ priPhoneType);
		System.out.println("altPhoneType: "+ altPhoneType);
		System.out.println("priPhoneNumber: "+ priPhoneNumber);
		System.out.println("altPhoneNumber: "+ altPhoneNumber);
		System.out.println("pricountryCode: "+ pricountryCode);
		System.out.println("altCountryCode: "+ altCountryCode);
		
		Character gender = 'O'; 
		if(maleRadioButton) {
			gender = 'M';
		}else if(femaleRadioButton) {
			gender = 'F';
		}
		
		Patient patient = createPatientObject(patientName, patientDOB, gender, patientPresentAddressType, patientPresentStreet, patientPresentCity, patientPresentState, patientPresentPostalCode
				,priPhoneType, priPhoneNumber, pricountryCode);
		
		try {
			RestClient.createPatient(patient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("End of create");
		
		
	}

	private Patient createPatientObject(String patientName, String patientDOB, Character gender,
			String patientPresentAddressType, String patientPresentStreet, String patientPresentCity,
			String patientPresentState, String patientPresentPostalCode, String priPhoneType, String priPhoneNumber, String pricountryCode) {
		Patient patient = new Patient();
		patient.setPatientName(patientName);
		patient.setDateOfBirth(patientDOB);
		patient.setGender(gender);
		
		Address address = new Address();
		address.setAddressType(patientPresentAddressType);
		address.setStreet(patientPresentStreet);
		address.setCity(patientPresentCity);
		address.setState(patientPresentState);
		address.setPostalCode(patientPresentPostalCode);
		
		Telephone telephone = new Telephone();
		telephone.setPhoneType(priPhoneType);
		telephone.setCountryCode(pricountryCode);
		telephone.setPhoneNumber(priPhoneNumber);
				
		
		patient.setAddress(Arrays.asList(address));
		patient.setMobileNumber(Arrays.asList(telephone));
		
		return patient;
			
	}
	
	

}
