/**
 * 
 */
package com.poc;

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
		removePatient(1);
		// updatePatient(1);
		// createPatient(null);
		// fetchPatientList();
		System.out.println("Fetch Patient by Id");
		// fetchPatientById(100);
	}

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

	private static String sampleData() {

		String requestBody = "{\r\n" + "\"patientName\":\"Himadri\",\r\n" + "\"dateOfBirth\":\"06/06/1985\",\r\n"
				+ "\"gender\":\"M\",\r\n" + "\"address\":[\r\n" + "{\r\n" + "\"addressType\":\"Present Address\",\r\n"
				+ "\"street\":\"Thoraipakkam\",\r\n" + "\"city\":\"Chennai\",\r\n" + "\"state\":\"Tamil Nadu\",\r\n"
				+ "\"postalCode\":\"600097\"\r\n" + "}\r\n" + "],\r\n" + "\"mobileNumber\":[\r\n" + "{\r\n"
				+ "\"phoneType\":\"Primary Contact\",\r\n" + "\"phoneNumber\":\"9884473347\",\r\n"
				+ "\"countryCode\":\"+91\"\r\n" + "},\r\n" + "{\r\n" + "\"phoneType\":\"Alternate Contact\",\r\n"
				+ "\"phoneNumber\":\"9884473XXX\",\r\n" + "\"countryCode\":\"+91\"\r\n" + "}\r\n" + "]\r\n" + "}";

		return requestBody;
	}

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
