package test.com.poc.api.client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.api.client.RestClient;
import com.poc.constant.PatientConstants;
import com.poc.model.Address;
import com.poc.model.Patient;
import com.poc.model.Telephone;

class RestClientTest {
	
	private static final String REST_END_POINT = "http://localhost:8080/api/patient";

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
	public void testFetchPatients() {
		try {
			List<Patient> patients = RestClient.fetchPatients();
			assertNotNull(patients);
			assertTrue(patients.size() > 0);;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFetchPatientById() throws IOException, InterruptedException {
		Integer patientId = 200;
		Patient patient = RestClient.fetchPatientById(patientId);
		assertNotNull(patient);
		assertEquals(patientId, patient.getPatientId());
		
	}

	@Test
	final void testFetchPatientsByName() {
		String patientName = "Rohit";
		List<Patient> patients = null;
		try {
			patients = RestClient.fetchPatientsByName(patientName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(patients);
		assertEquals(true, patients.stream().allMatch( p -> p.getPatientName().toUpperCase().contains(patientName.toUpperCase())));
	}

	@Test
	final void testCreatePatient() {
		Address permanentAddress = new Address();
		permanentAddress.setAddressType(PatientConstants.PERMANENT_ADDRESS_TYPE);
		permanentAddress.setStreet("Jajpur");
		permanentAddress.setCity("Bhubaneswar");
		permanentAddress.setState("Odisha");
		permanentAddress.setPostalCode("755014");
		
		Address presentAddress = new Address();
		presentAddress.setAddressType(PatientConstants.PRESENT_ADDRESS_TYPE);
		presentAddress.setStreet("Jajpur");
		presentAddress.setCity("Bhubaneswar");
		presentAddress.setState("Odisha");
		presentAddress.setPostalCode("755014");

		Telephone alternateContact = new Telephone();
		alternateContact.setPhoneType(PatientConstants.ALTERNATE_CONTACT);
		alternateContact.setCountryCode("91");
		alternateContact.setPhoneNumber("9884471111");
		
		Telephone primaryContact = new Telephone();
		primaryContact.setPhoneType(PatientConstants.PRIMARY_CONTACT);
		primaryContact.setCountryCode("91");
		primaryContact.setPhoneNumber("9884471110");

		Patient patient = new Patient();
		patient.setPatientName("Himadri");
		patient.setGender('M');
		patient.setMobileNumber(Arrays.asList(primaryContact, alternateContact));
		patient.setDateOfBirth("06/06/1985");
		patient.setAddress(Arrays.asList(permanentAddress, presentAddress));
		
		try {
			HttpResponse<String> response = RestClient.createPatient(patient);
			assertNotNull(response);
			assertEquals(201, response.statusCode());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	final void testUpdatePatient() {
		Address address = new Address();
		address.setAddressId(2222);
		address.setAddressType(PatientConstants.PRESENT_ADDRESS_TYPE);
		address.setStreet("Jajpur");
		address.setCity("Bhubaneswar");
		address.setState("Odisha");
		address.setPostalCode("755014");
		
		Address permanentAddress = new Address();
		address.setAddressId(2223);
		permanentAddress.setAddressType(PatientConstants.PERMANENT_ADDRESS_TYPE);
		permanentAddress.setStreet("Jajpur");
		permanentAddress.setCity("Bhubaneswar");
		permanentAddress.setState("Odisha");
		permanentAddress.setPostalCode("755014");

		Telephone contact = new Telephone();
		contact.setPhoneId(22222);
		contact.setPhoneType(PatientConstants.PRIMARY_CONTACT);
		contact.setCountryCode("91");
		contact.setPhoneNumber("9777777777");
		
		Telephone alternateContact = new Telephone();
		contact.setPhoneId(22223);
		alternateContact.setPhoneType(PatientConstants.ALTERNATE_CONTACT);
		alternateContact.setCountryCode("91");
		alternateContact.setPhoneNumber("9884471111");

		Patient patient = new Patient();
		patient.setPatientId(200);
		patient.setPatientName("Rohit Update");
		patient.setGender('M');
		patient.setMobileNumber(Arrays.asList(contact, alternateContact));
		patient.setDateOfBirth("06/06/1985");
		patient.setAddress(Arrays.asList(address, permanentAddress));
		
		try {
			HttpResponse<String> response = RestClient.updatePatient(patient);
			assertNotNull(response);
			assertEquals(202, response.statusCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@Test
	final void testRemovePatient() {
		try {
			HttpResponse<String> response = RestClient.removePatient(1);
			assertNotNull(response);
			assertEquals(200, response.statusCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
