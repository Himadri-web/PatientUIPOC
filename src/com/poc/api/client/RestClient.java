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
	
	private static final String REST_END_POINT = "http://localhost:8080/api/patient";
	/**
	 * This method is used to fetch all available patient details as a List
	 * @return All patient list
	 * @throws IOException
	 * @throws InterruptedException
	 */

	public static List<Patient> fetchPatients() throws IOException, InterruptedException {
		String apiEndPoint = "http://localhost:8080/api/patient";
		var request = HttpRequest.newBuilder().uri(URI.create(REST_END_POINT)).header("Content-Type", "application-json")
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
	 * This method is used to fetch one patient details based on Patient Id
	 * @return patient object for the given Patient Id
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static Patient fetchPatientById(Integer patientId) throws IOException, InterruptedException {
		String apiEndPoint = REST_END_POINT + "/" + patientId;
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
	public static List<Patient> fetchPatientsByName(String patientName) throws IOException, InterruptedException {
		String name = patientName.replaceAll("\\s", "%20");
		String apiEndPoint = REST_END_POINT + "/name/" + name;
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
		
		var request = HttpRequest.newBuilder().uri(URI.create(REST_END_POINT)).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(patient))).build();
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

		String apiEndPoint = REST_END_POINT + "/" + patient.getPatientId();

		var request = HttpRequest.newBuilder().uri(URI.create(apiEndPoint)).header("Content-Type", "application/json")
				.PUT(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(patient))).build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		System.out.println(response.statusCode());
		return response;

	}

	/**
	 * This method is used to delete one patient based on Patient Id
	 * @param patientId
	 * @return returns response as a string with response body and response code
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static HttpResponse<String> removePatient(Integer patientId) throws IOException, InterruptedException {

		String apiEndPoint = REST_END_POINT + "/" + patientId;
		var request = HttpRequest.newBuilder().uri(URI.create(apiEndPoint)).header("Content-Type", "application/json")
				.DELETE().build();

		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		System.out.println(response.statusCode());
		return response;
	}
}
