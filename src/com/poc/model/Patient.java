package com.poc.model;

import java.util.ArrayList;
import java.util.List;
/**
 * This class is a Model class for holding Patient details
 * @author HS106406
 * @version 1.0
 */
public class Patient {
	private Integer patientId;

    private String patientName;

    private String dateOfBirth;

    private Character gender;
    
    private List<Address> address = new ArrayList<>();
    
    private List<Telephone>  mobileNumber = new ArrayList<>();

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Telephone> getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(List<Telephone> mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + ", address=" + address + ", mobileNumber=" + mobileNumber + "]";
	}
    
    

}
