package test.com.poc.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.poc.util.PatientValidation;

class PatientValidationTest {
	
	PatientValidation patientValidation = new PatientValidation();;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testValidateNumbersInput() {
		Boolean isValid = patientValidation.validateNumbersInput("129567");
		assertTrue(isValid);
		assertFalse(patientValidation.validateNumbersInput("129567A"));
	}

	@Test
	final void testValidateAlphabetsInput() {
		assertTrue(patientValidation.validateAlphabetsInput("Himadri Sekhar Sahani"));
		assertFalse(patientValidation.validateAlphabetsInput("Himadri1"));
		assertFalse(patientValidation.validateAlphabetsInput(" Himadri"));
		assertTrue(patientValidation.validateAlphabetsInput("Himadri"));
	}

	@Test
	final void testValidateAlphaNumericInput() {
		assertTrue(patientValidation.validateAlphaNumericInput("#38, MCN Nagar Ext"));
		assertFalse(patientValidation.validateAlphaNumericInput(" #38, MCN Nagar Ext"));
		
	}

	@Test
	final void testValidateMobileNumber() {
		assertTrue(patientValidation.validateMobileNumber("9884473347"));
		assertFalse(patientValidation.validateMobileNumber("98844733470"));
		assertFalse(patientValidation.validateMobileNumber("988447"));
		assertFalse(patientValidation.validateMobileNumber("ABC"));
	}

	@Test
	final void testValidatePatientData() {
		Boolean isValid = patientValidation.validatePatientData("Himadri Sekhar Sahani", "06/06/1986", null);
		assertTrue(isValid);
		assertEquals(true, isValid);
	}

	@Test
	final void testValidatePatientAddressData() {
		Boolean isValid = patientValidation.validatePatientAddressData("Present Address", "#38 MCN Nagar Ext", "Chennai", "Tamil Nadu", "600097", null, "Present");
		assertTrue(isValid);
		assertEquals(true, isValid);
		assertTrue(patientValidation.validatePatientAddressData("Permanent Address", "#38 MCN Nagar Ext", "Chennai", "Tamil Nadu", "600097", null, "Permanent"));
	}

	@Test
	final void testValidatePatientContactData() {
		assertTrue(patientValidation.validatePatientContactData("Primary Contact", "9884473347", "91", null, "Primary"));
		assertTrue(patientValidation.validatePatientContactData("Alternate Contact", "9884473347", "91", null, "Alternate"));
		
	}

}
