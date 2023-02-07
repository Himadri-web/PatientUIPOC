package com.poc.model;


/**
 * @author HS106406
 * This Telephone class represents as Entity and Model class for holding contact details of a patient
 */
public class Telephone {

    private Integer phoneId;
    private String phoneType;
    private String phoneNumber;
    private String countryCode;
	public Integer getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(Integer phoneId) {
		this.phoneId = phoneId;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	@Override
	public String toString() {
		return "Telephone [phoneId=" + phoneId + ", phoneType=" + phoneType + ", phoneNumber=" + phoneNumber
				+ ", countryCode=" + countryCode + "]";
	}
	
	

}
