/**
 * 
 */
package com.poc.api.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.net.http.HttpResponse;

import java.util.Arrays;
import java.util.List;

import com.poc.model.Address;
import com.poc.model.Patient;
import com.poc.model.Telephone;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is to connect rest api
 * RestClient invoke rest api and returns response for each rest call and feed the data into SWT UI
 * @author HS106406
 *
 */
public class RestClient {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		fetchPatientByName("Himadri");
		//removePatient(1);
		// updatePatient(1);
		// createPatient(null);
		// fetchPatientList();
		System.out.println("Fetch Patient by Id");
		// fetchPatientById(100);
	}
	
	/**
	 * This method is used to fetch all available patient details as a List
	 * @return All patient list
	 * @throws IOException
	 * @throws InterruptedException
	 */

	public static List<Patient> fetchPatientList() throws IOException, InterruptedException {
		String apiEndPoint = "http://localhost:8080/api/patient";
		var request = HttpRequest.newBuilder().uri(URI.create(apiEndPoint)).header("Content-Type", "application-json")
				.GET().build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		System.out.println(response.statusCode());
		ObjectMapper mapper = new ObjectMapper();
		List<Patient> patients = mapper.readValue(response.body(), new TypeReference<List<Patient>>() {
		});

		System.out.println("patients details: " + patients);

		return patients;

		// mapper.readValue(response.body(), new TypeReference<Patient>() {});
	}

	/**
	 * This method is used to fetch one patient details based on Patient Id
	 * @return patient object for the given Patient Id
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static Patient fetchPatientById(Integer patientId) throws IOException, InterruptedException {
		String apiEndPoint = "http://localhost:8080/api/patient/" + patientId;
		var request = HttpRequest.newBuilder().uri(URI.create(apiEndPoint)).header("Content-Type", "application-json")
				.GET().build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		System.out.println(response.statusCode());

		ObjectMapper mapper = new ObjectMapper();
		Patient patient = mapper.readValue(response.body(), new TypeReference<Patient>() {
		});

		System.out.println("patient : " + patient.toString());
		return patient;

	}
	
	/**
	 * This method is used to fetch patient details based on Patient Name
	 * @return All patient list for the matched Patient Name
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<Patient> fetchPatientByName(String patientName) throws IOException, InterruptedException {
		String apiEndPoint = "http://localhost:8080/api/patient/name/" + patientName;
		var request = HttpRequest.newBuilder().uri(URI.create(apiEndPoint)).header("Content-Type", "application-json")
				.GET().build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		System.out.println(response.statusCode());

		ObjectMapper mapper = new ObjectMapper();
		List<Patient> patients = mapper.readValue(response.body(), new TypeReference<List<Patient>>() {
		});

		System.out.println("patients details: " + patients);

		return patients;

	}
	
	/**
	 * This method is used to create a new patient
	 * @param patient
	 * @return returns the response string with response body and response code
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> createPatient(Patient patient) throws IOException, InterruptedException {

		String apiEndPoint = "http://localhost:8080/api/patient";
		var objectMapper = new ObjectMapper();

		var request = HttpRequest.newBuilder().uri(URI.create(apiEndPoint)).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(patient))).build();
		// .POST(HttpRequest.BodyPublishers.ofString(new
		// ObjectMapper().writeValueAsString(sampleDataForCreatePatient()))).build();

		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println("response.body()" + response.body());
		System.out.println("response.statusCode()" + response.statusCode());
		return response;

	}

	/**
	 * This method is to update an existing patient details
	 * @param patient
	 * @return returns the response string with response body and response code
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> updatePatient(Patient patient) throws IOException, InterruptedException {

		String apiEndPoint = "http://localhost:8080/api/patient/" + patient.getPatientId();
		var objectMapper = new ObjectMapper();

		var request = HttpRequest.newBuilder().uri(URI.create(apiEndPoint)).header("Content-Type", "application/json")
				.PUT(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(patient))).build();
		// .PUT(HttpRequest.BodyPublishers.ofString(new
		// ObjectMapper().writeValueAsString(sampleDataForUpdatePatient()))).build();

		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		System.out.println(response.statusCode());
		return response;

	}

	/**
	 * This method is used to delete one patient based on Patient Id
	 * @param patientId
	 * @return returns response as a string with response body and qresponse code
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> removePatient(Integer patientId) throws IOException, InterruptedException {

		String apiEndPoint = "http://localhost:8080/api/patient/" + patientId;
		var request = HttpRequest.newBuilder().uri(URI.create(apiEndPoint)).header("Content-Type", "application/json")
				.DELETE().build();

		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		System.out.println(response.statusCode());
		return response;

	}
	
    //Sample data to test
	private static Patient sampleDataForCreatePatient() {
		Address permanentAddress = new Address();
		// permanentAddress.setAddressId(556);
		permanentAddress.setAddressType("Permanent Address");
		permanentAddress.setStreet("Jajpur");
		permanentAddress.setCity("Bhubaneswar");
		permanentAddress.setState("Odisha");
		permanentAddress.setPostalCode("755014");

		Telephone alternateContact = new Telephone();
		// alternateContact.setPhoneId(334);
		alternateContact.setPhoneNumber("Alternate Contact");
		alternateContact.setCountryCode("+91");
		alternateContact.setPhoneNumber("9884473XXX");

		Patient patient = new Patient();
		// patient.setPatientId(1);
		patient.setPatientName("Himadri");
		patient.setGender('M');
		patient.setMobileNumber(Arrays.asList(alternateContact));
		patient.setDateOfBirth("06/06/1985");
		patient.setAddress(Arrays.asList(permanentAddress

		));

		return patient;
	}

	//Sample data to test
	private static Patient sampleDataForUpdatePatient() {
		Address permanentAddress = new Address();
		permanentAddress.setAddressId(2);
		permanentAddress.setAddressType("Permanent Address Update");
		permanentAddress.setStreet("Jajpur");
		permanentAddress.setCity("Bhubaneswar");
		permanentAddress.setState("Odisha");
		permanentAddress.setPostalCode("755014");

		Telephone alternateContact = new Telephone();
		alternateContact.setPhoneId(2223);
		alternateContact.setPhoneType("Alternate Contact Update");
		alternateContact.setCountryCode("+91");
		alternateContact.setPhoneNumber("9884473XXX");

		Patient patient = new Patient();
		patient.setPatientId(1);
		patient.setPatientName("Himadri Update");
		patient.setGender('M');
		patient.setMobileNumber(Arrays.asList(alternateContact));
		patient.setDateOfBirth("06/06/1985");
		patient.setAddress(Arrays.asList(permanentAddress

		));

		return patient;
	}

}
