package com.poc.ui;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
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
public class PatientUIApp {

	Shell shell;
	
	PatientCreateForm patientForm;
	
	public static PatientUIApp patientApp;

	Patient existPatient;

	Button createButton;

	Button updateButton;
	
	public Patient getExistPatient() {
		return existPatient;
	}

	public void setExistPatient(Patient existPatient) {
		this.existPatient = existPatient;
	}

	/**
	 * Entry point to the application
	 * @param args
	 */
	public static void main(String[] args) {
		patientApp = new PatientUIApp();
		patientApp.init();

	}

    /**
     * create shell and display
     */
	public void init() {
		Display display = new Display();
		//shell = new Shell();
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shell.setSize(800, 580);//set fixed screen
		patientForm = new PatientCreateForm();
		
		createNewPatient(display);
	}

	/**
	 * Create new patient form
	 * @param display
	 */
	private void createNewPatient(Display display) {
		
		createPatientDetailsSection(display);
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
	 */
	private void createPatientDetailsSection(Display display) {
		
		int labelStartingPosition = 50;
		int textStartingPosition = 150;
		int labelWidth = 100;

		patientForm.patientCreatePage(display,shell,labelStartingPosition, textStartingPosition, labelWidth);

		Button locateButton = new Button(shell, SWT.PUSH);
		locateButton.setText("Patient Locator");
		locateButton.setBounds(500, 100, 200, 50);
		locateButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Inside Locator Listener");
				PatientView patientView = new PatientView(display);
				patientView.setPatientEntry(patientApp);
				patientView.showPatientDetails(false);
				System.out.println("End of Locator Listener");

			}
		});

		createButton = new Button(shell, SWT.PUSH);
		createButton.setText("Create");
		createButton.setBounds(labelStartingPosition, 450, labelWidth, 30);
		createButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Inside Create Listener");

				if(createOrUpdatePatient(display, false)) {
			        //createButton.setEnabled(false);
				}
				
				System.out.println("End of Create Listener");
			}
		});

		updateButton = new Button(shell, SWT.PUSH);
		updateButton.setText("Modify");
		updateButton.setBounds(labelStartingPosition + 120, 450, labelWidth, 30);
		updateButton.setEnabled(false);
		updateButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Insid Update listenier");

				if(createOrUpdatePatient(display, true)) {
					//updateButton.setEnabled(false);
				}

				System.out.println("End of Update Button listener");
			}
		});

	}

	/**
	 * This method is to validate patient form and do save or update
	 * @param display
	 * @param isUpdate
	 */
	protected Boolean createOrUpdatePatient(Display display, boolean isUpdate) {
		
		Boolean isCreateOrUpdateSuccessful = false;

		String patientName = patientForm.patientNameText.getText();
		
		Integer day = patientForm.patientDOB.getDay();
		Integer mon = patientForm.patientDOB.getMonth() + 1;
		Integer year = patientForm.patientDOB.getYear();
		String patientDOB = mon + "/" + day + "/" + year;
		//String patientDOB = patientForm.patientDOBText.getText();
		
		Boolean femaleRadioButton = patientForm.femaleRadio.getSelection();
		Boolean maleRadioButton = patientForm.maleRadio.getSelection();
		Boolean otherRadioButton = patientForm.otherRadio.getSelection();

		System.out.println("patientName: " + patientName);
		System.out.println("patientDOB: " + patientDOB);
		System.out.println("femaleRadioButton: " + femaleRadioButton);
		System.out.println("maleRadioButton: " + maleRadioButton);
		System.out.println("otherRadioButton: " + otherRadioButton);

		String patientPresentAddressType = patientForm.patientAddressTypeText.getText();
		String patientPermanentAddressType = patientForm.patientPermanentAddressTypeText.getText();
		String patientPresentStreet = patientForm.patientPresentStreetText.getText();
		String patientPermanentStreet = patientForm.patientPermanentStreetText.getText();
		String patientPresentCity = patientForm.patientPresentCityText.getText();
		String patientPermanentCity = patientForm.patientPermanentCityText.getText();
		String patientPresentState = patientForm.patientPresentStateText.getText();
		String patientPermanentState = patientForm.patientPermanentStateText.getText();
		String patientPresentPostalCode = patientForm.patientPresentPostalCodeText.getText();
		String patientPermanentPostalCode = patientForm.patientPermanentPostalCodeText.getText();

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

		String priPhoneType = patientForm.phoneTypeText.getText();
		String altPhoneType = patientForm.altphoneTypeText.getText();
		String priPhoneNumber = patientForm.phoneNumberText.getText();
		String altPhoneNumber = patientForm.altPhoneNumberText.getText();
		String priCountryCode = patientForm.countryCodeText.getText();
		String altCountryCode = patientForm.altCountryCodeText.getText();

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

		Boolean isPresentAddressValid = isPatientBasicInfoValid && patientValidation.validatePatientAddressData(patientPresentAddressType, patientPresentStreet,
						patientPresentCity, patientPresentState, patientPresentPostalCode, shell, "Present");

		Boolean isPermanentAddressValid = isPresentAddressValid && patientValidation.validatePatientAddressData(patientPermanentAddressType, patientPermanentStreet,
						patientPermanentCity, patientPermanentState, patientPermanentPostalCode, shell, "Permanent");

		Boolean isPrimaryContactValid = isPermanentAddressValid && patientValidation.validatePatientContactData(priPhoneType, priPhoneNumber, priCountryCode, shell,
						"Primary");

		Boolean isAlternateContactValid = isPrimaryContactValid && patientValidation.validatePatientContactData(altPhoneType, altPhoneNumber, altCountryCode, shell,
						"Alternate");

		if (isAlternateContactValid) {
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

				if(response.statusCode() == 201 || response.statusCode() == 202) {
					patientView.setPatientEntry(patientApp);
					patientView.showPatientDetails(true);
					isCreateOrUpdateSuccessful = true;
				}
			} catch (IOException e) {
				System.out.println("IOException issue");
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException issue");
				e.printStackTrace();
			}
		}

		System.out.println("isCreateOrUpdateSuccessful: "+isCreateOrUpdateSuccessful);
		System.out.println("End of create");
		return isCreateOrUpdateSuccessful;

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
	 * @return This method returns one patient
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
		patient.setPatientName(patientName.toUpperCase());
		patient.setDateOfBirth(patientDOB);
		patient.setGender(gender);

		Address presentAddress = new Address();
		Address permanentAddress = new Address();

		Telephone primaryContact = new Telephone();
		Telephone altContact = new Telephone();
		if (existingPatient != null) {
			for (Address existingAddress : existingPatient.getAddress()) {
				if (existingAddress.getAddressType().equalsIgnoreCase(PatientConstants.PERMANENT_ADDRESS_TYPE)) {
					if (isUpdate) {
						presentAddress.setAddressId(existingAddress.getAddressId());
					}

				} else if (existingAddress.getAddressType().equalsIgnoreCase(PatientConstants.PERMANENT_ADDRESS_TYPE)) {
					if (isUpdate) {
						permanentAddress.setAddressId(existingAddress.getAddressId());
					}
				}

			}

			for (Telephone existingTelephone : existingPatient.getMobileNumber()) {
				if (existingTelephone.getPhoneType().equalsIgnoreCase(PatientConstants.PRIMARY_CONTACT)) {
					if (isUpdate) {
						primaryContact.setPhoneId(existingTelephone.getPhoneId());
					}
					System.out.println("Primary Contact phone Id : " + existingTelephone.getPhoneId());
				} else if (existingTelephone.getPhoneType().equalsIgnoreCase(PatientConstants.ALTERNATE_CONTACT)) {
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

		patientForm.patientNameText.setText("");
		//patientForm.patientDOBText.setText("");
		LocalDate localDate = LocalDate.now();
		patientForm.patientDOB.setDate(localDate.getYear(), localDate.getMonth().getValue()-1, localDate.getDayOfMonth());
		patientForm.femaleRadio.setSelection(false);
		patientForm.maleRadio.setSelection(false);
		patientForm.otherRadio.setSelection(false);

		patientForm.patientPresentStreetText.setText("");
		patientForm.patientPermanentStreetText.setText("");
		patientForm.patientPresentCityText.setText("");
		patientForm.patientPermanentCityText.setText("");
		patientForm.patientPresentStateText.setText("");
		patientForm.patientPermanentStateText.setText("");
		patientForm.patientPresentPostalCodeText.setText("");
		patientForm.patientPermanentPostalCodeText.setText("");

		patientForm.phoneNumberText.setText("");
		patientForm.altPhoneNumberText.setText("");
		patientForm.countryCodeText.setText("");
		patientForm.altCountryCodeText.setText("");

		patientForm.phoneTypeText.setText("Primary Contact");
		patientForm.phoneTypeText.setEnabled(false);
		patientForm.altphoneTypeText.setText("Alternate Contact");
		patientForm.altphoneTypeText.setEnabled(false);

		patientForm.patientAddressTypeText.setText("Present Address");
		patientForm.patientAddressTypeText.setEnabled(false);
		patientForm.patientPermanentAddressTypeText.setText("Permanent Address");
		patientForm.patientPermanentAddressTypeText.setEnabled(false);
		createButton.setEnabled(true);
		updateButton.setEnabled(false);
		
		patientForm.patientNameText.setEditable(true);
		//patientForm.patientDOBText.setEditable(true);
		patientForm.patientDOB.setEnabled(true);
		patientForm.patientPresentStreetText.setEditable(true);
		patientForm.patientPresentCityText.setEditable(true);
		patientForm.patientPresentStateText.setEditable(true);
		patientForm.patientPresentPostalCodeText.setEditable(true);
		patientForm.patientPermanentStreetText.setEditable(true);
		patientForm.patientPermanentStreetText.setEditable(true);
		patientForm.patientPermanentCityText.setEditable(true);
		patientForm.patientPermanentStateText.setEditable(true);
		patientForm.patientPermanentPostalCodeText.setEditable(true);
		patientForm.phoneNumberText.setEditable(true);
		patientForm.countryCodeText.setEditable(true);
		patientForm.altPhoneNumberText.setEditable(true);
		patientForm.altCountryCodeText.setEditable(true);
	}/*

	/**
	 * set data for view and update
	 * @param patient
	 * @param displayParent
	 * @param isDisable
	 */
	public void setDataForViewAndModify(Patient patient, Display displayParent, boolean isDisable) {
		System.out.println("Inside setData for view & modify ");
		patientForm.patientNameText.setText(patient.getPatientName());
		//patientForm.patientDOBText.setText(patient.getDateOfBirth());
		String[] dob = patient.getDateOfBirth().split("/");
		patientForm.patientDOB.setDate(Integer.parseInt(dob[2]), Integer.parseInt(dob[0])-1, Integer.parseInt(dob[1]));
		
		Character gender = patient.getGender();
		if (gender.equals('M')) {
			patientForm.maleRadio.setSelection(true);
		} else if (gender.equals('F')) {
			patientForm.femaleRadio.setSelection(true);
		} else {
			patientForm.otherRadio.setSelection(true);
		}

		for (Address address : patient.getAddress()) {
			if (address.getAddressType().equalsIgnoreCase(PatientConstants.PRESENT_ADDRESS_TYPE)) {
				patientForm.patientAddressTypeText.setText(address.getAddressType());
				patientForm.patientPresentStreetText.setText(address.getStreet());
				patientForm.patientPresentCityText.setText(address.getCity());
				patientForm.patientPresentStateText.setText(address.getState());
				patientForm.patientPresentPostalCodeText.setText(address.getPostalCode());

			} else if (address.getAddressType().equalsIgnoreCase(PatientConstants.PERMANENT_ADDRESS_TYPE)) {
				patientForm.patientPermanentAddressTypeText.setText(address.getAddressType());
				patientForm.patientPermanentStreetText.setText(address.getStreet());
				patientForm.patientPermanentCityText.setText(address.getCity());
				patientForm.patientPermanentStateText.setText(address.getState());
				patientForm.patientPermanentPostalCodeText.setText(address.getPostalCode());
			}
		}

		for (Telephone telephone : patient.getMobileNumber()) {
			if (telephone.getPhoneType().equalsIgnoreCase(PatientConstants.PRIMARY_CONTACT)) {
				patientForm.phoneTypeText.setText(telephone.getPhoneType());
				patientForm.phoneNumberText.setText(telephone.getPhoneNumber());
				patientForm.countryCodeText.setText(telephone.getCountryCode());
				System.out.println("Primary Contact phone Id : " + telephone.getPhoneId());
			} else if (telephone.getPhoneType().equalsIgnoreCase(PatientConstants.ALTERNATE_CONTACT)) {
				patientForm.altphoneTypeText.setText(telephone.getPhoneType());
				patientForm.altPhoneNumberText.setText(telephone.getPhoneNumber());
				patientForm.altCountryCodeText.setText(telephone.getCountryCode());
				System.out.println("Alternate Contact phone Id : " + telephone.getPhoneId());
			}

		}

		this.setExistPatient(patient);
		if (isDisable) {
			patientForm.patientNameText.setEditable(false);
			//patientForm.patientDOBText.setEditable(false);
			patientForm.patientDOB.setEnabled(false);
			patientForm.patientAddressTypeText.setEditable(false);
			patientForm.patientPermanentAddressTypeText.setEditable(false);
			patientForm.patientPresentStreetText.setEditable(false);
			patientForm.patientPermanentStreetText.setEditable(false);
			patientForm.patientPresentCityText.setEditable(false);
			patientForm.patientPermanentCityText.setEditable(false);
			patientForm.patientPresentStateText.setEditable(false);
			patientForm.patientPermanentStateText.setEditable(false);
			patientForm.patientPresentPostalCodeText.setEditable(false);
			patientForm.patientPermanentPostalCodeText.setEditable(false);

			patientForm.phoneTypeText.setEditable(false);
			patientForm.altphoneTypeText.setEditable(false);
			patientForm.phoneNumberText.setEditable(false);
			patientForm.altPhoneNumberText.setEditable(false);
			patientForm.countryCodeText.setEditable(false);
			patientForm.altCountryCodeText.setEditable(false);

		} else {
			patientForm.patientNameText.setEditable(true);
			//patientForm.patientDOBText.setEditable(true);
			patientForm.patientDOB.setEnabled(true);
			patientForm.patientAddressTypeText.setEditable(true);
			patientForm.patientPermanentAddressTypeText.setEditable(true);
			patientForm.patientPresentStreetText.setEditable(true);
			patientForm.patientPermanentStreetText.setEditable(true);
			patientForm.patientPresentCityText.setEditable(true);
			patientForm.patientPermanentCityText.setEditable(true);
			patientForm.patientPresentStateText.setEditable(true);
			patientForm.patientPermanentStateText.setEditable(true);
			patientForm.patientPresentPostalCodeText.setEditable(true);
			patientForm.patientPermanentPostalCodeText.setEditable(true);

			patientForm.phoneTypeText.setEditable(true);
			patientForm.altphoneTypeText.setEditable(true);
			patientForm.phoneNumberText.setEditable(true);
			patientForm.altPhoneNumberText.setEditable(true);
			patientForm.countryCodeText.setEditable(true);
			patientForm.altCountryCodeText.setEditable(true);
		}
		createButton.setEnabled(false);
		if (!isDisable) {
			updateButton.setEnabled(true);
		} else {
			updateButton.setEnabled(false);
		}

	}

}
