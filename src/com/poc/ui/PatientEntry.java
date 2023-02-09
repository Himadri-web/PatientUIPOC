package com.poc.ui;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.poc.api.client.RestClient;
import com.poc.constant.PatientConstants;
import com.poc.model.Address;
import com.poc.model.Patient;
import com.poc.model.Telephone;
import com.poc.util.PatientValidation;
/**
 * This class is to initiate the application
 * UI to Save or Update or View of the patient details
 * @author HS106406
 * @version 1.0
 */
public class PatientEntry {

	Shell shell;

	public Text patientNameText;

	public Text patientDOBText;

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

	public static PatientEntry patientEntry;

	Patient existPatient;

	Button createButton;

	Button updateButton;

	/**
	 * Entry point to the application
	 * @param args
	 */
	public static void main(String[] args) {
		patientEntry = new PatientEntry();
		patientEntry.init();

	}

	public Patient getExistPatient() {
		return existPatient;
	}

	public void setExistPatient(Patient existPatient) {
		this.existPatient = existPatient;
	}
    /**
     * create shell and display
     */
	private void init() {
		Display display = new Display();
		shell = new Shell();
		// shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		// shell.setSize(766, 664);//set fixed screen
		createNewPatient(display, shell);
	}

	/**
	 * Create new patient form
	 * @param display
	 * @param shell2
	 */
	private void createNewPatient(Display display, Shell shell2) {
		shell.setText("Welcome to Cerner Health Care");
		shell.setFont(SWTResourceManager.getFont("Patient UI", 16, SWT.BOLD));

		Label label = new Label(shell, SWT.CENTER);
		label.setBounds(500, 5, 700, 30);
		label.setText("Patient Registration Form");
		label.setFont(new Font(display, "boldfont", 20, SWT.BOLD));
		createPatientDetailsSection(display, shell);
		label.pack();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}

		}

	}

	/**
	 * Create patient form with save update and locator button listener
	 * @param display
	 * @param shell2
	 */
	private void createPatientDetailsSection(Display display, Shell shell2) {
		// Patient Name
		int labelStartingPosition = 50;
		int textStartingPosition = 160;
		int labelWidth = 100;

		patientCreatePage(labelStartingPosition, textStartingPosition, labelWidth);

		Button locateButton = new Button(shell, SWT.PUSH);
		locateButton.setText("Patient Locator");
		locateButton.setBounds(800, 100, 200, 30);
		locateButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Clicked Patient Locator");
				PatientView patientView = new PatientView(display);
				patientView.setPatientEntry(patientEntry);
				patientView.showPatientDetails(false);
				System.out.println("End of Patient Locator event");

			}
		});

		createButton = new Button(shell, SWT.PUSH);
		createButton.setText("Create");
		createButton.setBounds(labelStartingPosition, 550, labelWidth, 30);
		createButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Insid Create event");

				createOrUpdatePatient(display, shell, false);

				System.out.println("End of Create event");
			}
		});

		updateButton = new Button(shell, SWT.PUSH);
		updateButton.setText("Modify");
		updateButton.setBounds(labelStartingPosition + 120, 550, labelWidth, 30);
		updateButton.setEnabled(false);
		updateButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Insid Update listenier");

				createOrUpdatePatient(display, shell, true);

				System.out.println("End of Create listener");
			}
		});

	}

	/**
	 * create patient form
	 * @param labelStartingPosition
	 * @param textStartingPosition
	 * @param labelWidth
	 */
	private void patientCreatePage(int labelStartingPosition, int textStartingPosition, int labelWidth) {
		Label patientName = new Label(shell, SWT.NONE);
		patientName.setText("Patient Name*");
		patientName.setBounds(labelStartingPosition, 50, labelWidth, 30);

		patientNameText = new Text(shell, SWT.BORDER);
		patientNameText.setBounds(textStartingPosition, 50, 300, 30);
		patientNameText.setTextLimit(25);

		// Patient Date of Birth
		Label patientDateOfBirth = new Label(shell, SWT.NONE);
		patientDateOfBirth.setText("Date Of Birth*");
		patientDateOfBirth.setBounds(labelStartingPosition, 90, labelWidth, 30);

		// Gender
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
		maleRadio.setBounds(textStartingPosition + 90, 130, 80, 30);

		otherRadio = new Button(shell, SWT.RADIO);
		otherRadio.setText("Others");
		otherRadio.setBounds(textStartingPosition + 180, 130, 80, 30);

		// Address
		Label address = new Label(shell, SWT.NONE);
		address.setText("Please fill the Address of the Patient*");
		address.setFont(SWTResourceManager.getFont("Patient UI", 9, SWT.BOLD));
		address.setBounds(labelStartingPosition, 170, 900, 30);

		Label addressType = new Label(shell, SWT.NONE);
		addressType.setText("Address Type*");
		addressType.setBounds(labelStartingPosition, 200, labelWidth, 30);

		patientAddressTypeText = new Text(shell, SWT.BORDER);
		patientAddressTypeText.setBounds(textStartingPosition, 200, 300, 20);
		patientAddressTypeText.setText(PatientConstants.PRESTNT_ADDRESS_TYPE);
		patientAddressTypeText.setEnabled(false);

		patientPermanentAddressTypeText = new Text(shell, SWT.BORDER);
		patientPermanentAddressTypeText.setBounds(480, 200, 300, 20);
		patientPermanentAddressTypeText.setText(PatientConstants.PERMANENT_ADDRESS_TYPE);
		patientPermanentAddressTypeText.setEnabled(false);

		Label street = new Label(shell, SWT.NONE);
		street.setText("Street*");
		street.setBounds(labelStartingPosition, 240, labelWidth, 30);

		patientPresentStreetText = new Text(shell, SWT.BORDER);
		patientPresentStreetText.setBounds(textStartingPosition, 240, 300, 20);
		patientPresentStreetText.setTextLimit(25);

		patientPermanentStreetText = new Text(shell, SWT.BORDER);
		patientPermanentStreetText.setBounds(480, 240, 300, 20);
		patientPermanentStreetText.setTextLimit(25);

		Label city = new Label(shell, SWT.NONE);
		city.setText("City*");
		city.setBounds(labelStartingPosition, 280, labelWidth, 30);

		patientPresentCityText = new Text(shell, SWT.BORDER);
		patientPresentCityText.setBounds(textStartingPosition, 280, 300, 20);
		patientPresentCityText.setTextLimit(25);

		patientPermanentCityText = new Text(shell, SWT.BORDER);
		patientPermanentCityText.setBounds(480, 280, 300, 20);
		patientPermanentCityText.setTextLimit(25);

		Label state = new Label(shell, SWT.NONE);
		state.setText("State*");
		state.setBounds(labelStartingPosition, 320, labelWidth, 30);

		patientPresentStateText = new Text(shell, SWT.BORDER);
		patientPresentStateText.setBounds(textStartingPosition, 320, 300, 20);
		patientPresentStateText.setTextLimit(25);

		patientPermanentStateText = new Text(shell, SWT.BORDER);
		patientPermanentStateText.setBounds(480, 320, 300, 20);
		patientPermanentStateText.setTextLimit(25);

		Label postalCode = new Label(shell, SWT.NONE);
		postalCode.setText("Postal Code*");
		postalCode.setBounds(labelStartingPosition, 360, labelWidth, 30);

		patientPresentPostalCodeText = new Text(shell, SWT.BORDER);
		patientPresentPostalCodeText.setBounds(textStartingPosition, 360, 300, 20);
		patientPresentPostalCodeText.setTextLimit(25);

		patientPermanentPostalCodeText = new Text(shell, SWT.BORDER);
		patientPermanentPostalCodeText.setBounds(480, 360, 300, 20);
		patientPermanentPostalCodeText.setTextLimit(25);

		// Telephone

		Label telephone = new Label(shell, SWT.NONE);
		telephone.setText("Patient contact details");
		telephone.setFont(SWTResourceManager.getFont("Patient UI", 9, SWT.BOLD));
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
		// Phone number
		Label phoneNumber = new Label(shell, SWT.NONE);
		phoneNumber.setText("Phone Number*");
		phoneNumber.setBounds(labelStartingPosition, 470, labelWidth, 30);

		phoneNumberText = new Text(shell, SWT.BORDER);
		phoneNumberText.setBounds(textStartingPosition, 470, 300, 20);
		phoneNumberText.setTextLimit(25);

		altPhoneNumberText = new Text(shell, SWT.BORDER);
		altPhoneNumberText.setBounds(480, 470, 300, 20);
		altPhoneNumberText.setTextLimit(25);

		// country code
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
	}

	/**
	 * This method is to validate patient form and do save or update
	 * @param display
	 * @param shell2
	 * @param isUpdate
	 */
	protected void createOrUpdatePatient(Display display, Shell shell2, boolean isUpdate) {

		String patientName = patientNameText.getText();
		String patientDOB = patientDOBText.getText();
		Boolean femaleRadioButton = femaleRadio.getSelection();
		Boolean maleRadioButton = maleRadio.getSelection();
		Boolean otherRadioButton = otherRadio.getSelection();

		System.out.println("patientName: " + patientName);
		System.out.println("patientDOB: " + patientDOB);
		System.out.println("femaleRadioButton: " + femaleRadioButton);
		System.out.println("maleRadioButton: " + maleRadioButton);
		System.out.println("otherRadioButton: " + otherRadioButton);

		String patientPresentAddressType = patientAddressTypeText.getText();
		String patientPermanentAddressType = patientPermanentAddressTypeText.getText();
		String patientPresentStreet = patientPresentStreetText.getText();
		String patientPermanentStreet = patientPermanentStreetText.getText();
		String patientPresentCity = patientPresentCityText.getText();
		String patientPermanentCity = patientPermanentCityText.getText();
		String patientPresentState = patientPresentStateText.getText();
		String patientPermanentState = patientPermanentStateText.getText();
		String patientPresentPostalCode = patientPresentPostalCodeText.getText();
		String patientPermanentPostalCode = patientPermanentPostalCodeText.getText();

		System.out.println("patientPresentAddressType: " + patientPresentAddressType);
		System.out.println("patientPermanentAddressType: " + patientPermanentAddressType);
		System.out.println("patientPresentStreet: " + patientPresentStreet);
		System.out.println("patientPermanentStreet: " + patientPermanentStreet);
		System.out.println("patientPresentCity: " + patientPresentCity);
		System.out.println("patientPermanentCity: " + patientPermanentCity);
		System.out.println("patientPresentState: " + patientPresentState);
		System.out.println("patientPermanentState: " + patientPermanentState);
		System.out.println("patientPresentPostalCode: " + patientPresentPostalCode);
		System.out.println("patientPermanentPostalCode: " + patientPermanentPostalCode);

		String priPhoneType = phoneTypeText.getText();
		String altPhoneType = altphoneTypeText.getText();
		String priPhoneNumber = phoneNumberText.getText();
		String altPhoneNumber = altPhoneNumberText.getText();
		String priCountryCode = countryCodeText.getText();
		String altCountryCode = altCountryCodeText.getText();

		System.out.println("priPhoneType: " + priPhoneType);
		System.out.println("altPhoneType: " + altPhoneType);
		System.out.println("priPhoneNumber: " + priPhoneNumber);
		System.out.println("altPhoneNumber: " + altPhoneNumber);
		System.out.println("priCountryCode: " + priCountryCode);
		System.out.println("altCountryCode: " + altCountryCode);

		Character gender = 'O';
		if (maleRadioButton) {
			gender = 'M';
		} else if (femaleRadioButton) {
			gender = 'F';
		}
		Patient patient = null;
		boolean isAllPatientDataValid = false;

		PatientValidation patientValidation = new PatientValidation();

		Boolean isPatientBasicInfoValid = patientValidation.validatePatientData(patientName, patientDOB, shell);

		Boolean isPresentAddressValid = isPatientBasicInfoValid
				? patientValidation.validatePatientAddressData(patientPresentAddressType, patientPresentStreet,
						patientPresentCity, patientPresentState, patientPresentPostalCode, shell, "Present")
				: false;

		Boolean isPermanentAddressValid = isPresentAddressValid
				? patientValidation.validatePatientAddressData(patientPermanentAddressType, patientPermanentStreet,
						patientPermanentCity, patientPermanentState, patientPermanentPostalCode, shell, "Permanent")
				: false;

		Boolean isPrimaryContactValid = isPermanentAddressValid
				? patientValidation.validatePatientContactData(priPhoneType, priPhoneNumber, priCountryCode, shell,
						"Primary")
				: false;

		Boolean isAlternateContactValid = isPrimaryContactValid
				? patientValidation.validatePatientContactData(altPhoneType, altPhoneNumber, altCountryCode, shell,
						"Alternate")
				: false;

		if (isPatientBasicInfoValid && isPresentAddressValid && isPermanentAddressValid && isPrimaryContactValid
				&& isAlternateContactValid) {
			isAllPatientDataValid = true;
		}

		if (isAllPatientDataValid) {
			if (isUpdate) {
				System.out.println("Update Patient!");
				patient = preparePatientObject(patientName, patientDOB, gender, patientPresentAddressType,
						patientPresentStreet, patientPresentCity, patientPresentState, patientPresentPostalCode,
						patientPermanentAddressType, patientPermanentStreet, patientPermanentCity,
						patientPermanentState, patientPermanentPostalCode, priPhoneType, priPhoneNumber, priCountryCode,
						altPhoneType, altPhoneNumber, altCountryCode, existPatient, true);

			} else {
				System.out.println("Create Patient!");
				patient = preparePatientObject(patientName, patientDOB, gender, patientPresentAddressType,
						patientPresentStreet, patientPresentCity, patientPresentState, patientPresentPostalCode,
						patientPermanentAddressType, patientPermanentStreet, patientPermanentCity,
						patientPermanentState, patientPermanentPostalCode, priPhoneType, priPhoneNumber, priCountryCode,
						altPhoneType, altPhoneNumber, altCountryCode, existPatient, false);

			}

			try {
				PatientView patientView = new PatientView(display);
				HttpResponse<String> response;
				if (!isUpdate) {
					response = RestClient.createPatient(patient);
				} else {
					response = RestClient.updatePatient(patient);
				}

				System.out.println("response code is : " + response.statusCode());

				// if(response.statusCode() == 201) {
				patientView.setPatientEntry(patientEntry);
				patientView.showPatientDetails(true);
				// }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException issue");
				e.printStackTrace();
			}
		}

		System.out.println("End of create");

	}

	/**
	 * prepare the patient details to create or update
	 * @param patientName
	 * @param patientDOB
	 * @param gender
	 * @param patientPresentAddressType
	 * @param patientPresentStreet
	 * @param patientPresentCity
	 * @param patientPresentState
	 * @param patientPresentPostalCode
	 * @param patientPermanentAddressType
	 * @param patientPermanentStreet
	 * @param patientPermanentCity
	 * @param patientPermanentState
	 * @param patientPermanentPostalCode
	 * @param priPhoneType
	 * @param priPhoneNumber
	 * @param pricountryCode
	 * @param altPhoneType
	 * @param altPhoneNumber
	 * @param altcountryCode
	 * @param existingPatient
	 * @param isUpdate
	 * @return
	 */
	private Patient preparePatientObject(String patientName, String patientDOB, Character gender,
			String patientPresentAddressType, String patientPresentStreet, String patientPresentCity,
			String patientPresentState, String patientPresentPostalCode, String patientPermanentAddressType,
			String patientPermanentStreet, String patientPermanentCity, String patientPermanentState,
			String patientPermanentPostalCode, String priPhoneType, String priPhoneNumber, String pricountryCode,
			String altPhoneType, String altPhoneNumber, String altcountryCode, Patient existingPatient,
			Boolean isUpdate) {
		Patient patient = new Patient();
		if (isUpdate) {
			patient.setPatientId(existingPatient.getPatientId());
		}
		patient.setPatientName(patientName);
		patient.setDateOfBirth(patientDOB);
		patient.setGender(gender);

		Address presentAddress = new Address();
		Address permanentAddress = new Address();

		Telephone primaryContact = new Telephone();
		Telephone altContact = new Telephone();
		if (existingPatient != null) {
			for (Address existingAddress : existingPatient.getAddress()) {
				if (existingAddress.getAddressType().equalsIgnoreCase("Present Address")) {
					if (isUpdate) {
						presentAddress.setAddressId(existingAddress.getAddressId());
					}

				} else if (existingAddress.getAddressType().equalsIgnoreCase("Permanent Address")) {
					if (isUpdate) {
						permanentAddress.setAddressId(existingAddress.getAddressId());
					}
				}

			}

			for (Telephone existingTelephone : existingPatient.getMobileNumber()) {
				if (existingTelephone.getPhoneType().equalsIgnoreCase("Primary Contact")) {
					if (isUpdate) {
						primaryContact.setPhoneId(existingTelephone.getPhoneId());
					}
					System.out.println("Primary Contact phone Id : " + existingTelephone.getPhoneId());
				} else if (existingTelephone.getPhoneType().equalsIgnoreCase("Alternate Contact")) {
					if (isUpdate) {
						altContact.setPhoneId(existingTelephone.getPhoneId());
					}
					System.out.println("Alternate Contact phone Id : " + altContact.getPhoneId());
				}
			}
		}

		presentAddress.setAddressType(patientPresentAddressType);
		presentAddress.setStreet(patientPresentStreet);
		presentAddress.setCity(patientPresentCity);
		presentAddress.setState(patientPresentState);
		presentAddress.setPostalCode(patientPresentPostalCode);

		permanentAddress.setAddressType(patientPermanentAddressType);
		permanentAddress.setStreet(patientPermanentStreet);
		permanentAddress.setCity(patientPermanentCity);
		permanentAddress.setState(patientPermanentState);
		permanentAddress.setPostalCode(patientPermanentPostalCode);

		primaryContact.setPhoneType(priPhoneType);
		primaryContact.setCountryCode(pricountryCode);
		primaryContact.setPhoneNumber(priPhoneNumber);

		altContact.setPhoneType(altPhoneType);
		altContact.setCountryCode(altcountryCode);
		altContact.setPhoneNumber(altPhoneNumber);

		patient.setAddress(Arrays.asList(presentAddress, permanentAddress));
		patient.setMobileNumber(Arrays.asList(primaryContact, altContact));

		return patient;

	}

	/**
	 * Set default data in the patient form
	 */
	public void setDataForDefaultPage() {

		patientNameText.setText("");
		patientDOBText.setText("");
		femaleRadio.setSelection(false);
		maleRadio.setSelection(false);
		otherRadio.setSelection(false);

		// patientAddressTypeText.setText("");
		// patientPermanentAddressTypeText.setText("");
		patientPresentStreetText.setText("");
		patientPermanentStreetText.setText("");
		patientPresentCityText.setText("");
		patientPermanentCityText.setText("");
		patientPresentStateText.setText("");
		patientPermanentStateText.setText("");
		patientPresentPostalCodeText.setText("");
		patientPermanentPostalCodeText.setText("");

		// phoneTypeText.setText("");
		// altphoneTypeText.setText("");
		phoneNumberText.setText("");
		altPhoneNumberText.setText("");
		countryCodeText.setText("");
		altCountryCodeText.setText("");

		phoneTypeText.setText("Primary Contact");
		phoneTypeText.setEnabled(false);
		altphoneTypeText.setText("Alternate Contact");
		altphoneTypeText.setEnabled(false);

		patientAddressTypeText.setText("Present Address");
		patientAddressTypeText.setEnabled(false);
		patientPermanentAddressTypeText.setText("Permanent Address");
		patientPermanentAddressTypeText.setEnabled(false);
		createButton.setEnabled(true);
		updateButton.setEnabled(false);

	}

	/**
	 * set data for view and update
	 * @param patient
	 * @param displayParent
	 * @param isDisable
	 */
	public void setDataForViewAndModify(Patient patient, Display displayParent, boolean isDisable) {
		System.out.println("Inside setData for view & modify ");
		patientNameText.setText(patient.getPatientName());
		patientDOBText.setText(patient.getDateOfBirth());
		Character gender = patient.getGender();
		if (gender.equals('M')) {
			maleRadio.setSelection(true);
		} else if (gender.equals('F')) {
			femaleRadio.setSelection(true);
		} else {
			otherRadio.setSelection(true);
		}

		for (Address address : patient.getAddress()) {
			if (address.getAddressType().equalsIgnoreCase("Present Address")) {
				patientAddressTypeText.setText(address.getAddressType());
				patientPresentStreetText.setText(address.getStreet());
				patientPresentCityText.setText(address.getCity());
				patientPresentStateText.setText(address.getState());
				patientPresentPostalCodeText.setText(address.getPostalCode());

			} else if (address.getAddressType().equalsIgnoreCase("Permanent Address")) {
				patientPermanentAddressTypeText.setText(address.getAddressType());
				patientPermanentStreetText.setText(address.getStreet());
				patientPermanentCityText.setText(address.getCity());
				patientPermanentStateText.setText(address.getState());
				patientPermanentPostalCodeText.setText(address.getPostalCode());
			}
		}

		for (Telephone telephone : patient.getMobileNumber()) {
			if (telephone.getPhoneType().equalsIgnoreCase("Primary Contact")) {
				phoneTypeText.setText(telephone.getPhoneType());
				phoneNumberText.setText(telephone.getPhoneNumber());
				countryCodeText.setText(telephone.getCountryCode());
				System.out.println("Primary Contact phone Id : " + telephone.getPhoneId());
			} else if (telephone.getPhoneType().equalsIgnoreCase("Alternate Contact")) {
				altphoneTypeText.setText(telephone.getPhoneType());
				altPhoneNumberText.setText(telephone.getPhoneNumber());
				altCountryCodeText.setText(telephone.getCountryCode());
				System.out.println("Alternate Contact phone Id : " + telephone.getPhoneId());
			}

		}

		this.setExistPatient(patient);
		if (isDisable) {
			patientNameText.setEditable(false);
			patientDOBText.setEditable(false);
			// femaleRadio.setSelection();
			// maleRadio.setSelection();
			// otherRadio.setSelection();

			patientAddressTypeText.setEditable(false);
			patientPermanentAddressTypeText.setEditable(false);
			patientPresentStreetText.setEditable(false);
			patientPermanentStreetText.setEditable(false);
			patientPresentCityText.setEditable(false);
			patientPermanentCityText.setEditable(false);
			patientPresentStateText.setEditable(false);
			patientPermanentStateText.setEditable(false);
			patientPresentPostalCodeText.setEditable(false);
			patientPermanentPostalCodeText.setEditable(false);

			phoneTypeText.setEditable(false);
			altphoneTypeText.setEditable(false);
			phoneNumberText.setEditable(false);
			altPhoneNumberText.setEditable(false);
			countryCodeText.setEditable(false);
			altCountryCodeText.setEditable(false);

		} else {
			patientNameText.setEditable(true);
			patientDOBText.setEditable(true);
			// femaleRadio.setSelection();
			// maleRadio.setSelection();
			// otherRadio.setSelection();

			patientAddressTypeText.setEditable(true);
			patientPermanentAddressTypeText.setEditable(true);
			patientPresentStreetText.setEditable(true);
			patientPermanentStreetText.setEditable(true);
			patientPresentCityText.setEditable(true);
			patientPermanentCityText.setEditable(true);
			patientPresentStateText.setEditable(true);
			patientPermanentStateText.setEditable(true);
			patientPresentPostalCodeText.setEditable(true);
			patientPermanentPostalCodeText.setEditable(true);

			phoneTypeText.setEditable(true);
			altphoneTypeText.setEditable(true);
			phoneNumberText.setEditable(true);
			altPhoneNumberText.setEditable(true);
			countryCodeText.setEditable(true);
			altCountryCodeText.setEditable(true);
		}
		createButton.setEnabled(false);
		if (!isDisable) {
			updateButton.setEnabled(true);
		} else {
			updateButton.setEnabled(false);
		}

	}

}
