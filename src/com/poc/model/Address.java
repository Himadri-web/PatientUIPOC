package com.poc.model;


/**
 * Address class represents as Model class for holding Address details of a patient
 * @author HS106406
 * @version 1.0
 */
public class Address {

    private Integer addressId;
    private String addressType;
    private String street;
    private String city;
    private String state;
    private String postalCode;
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressType=" + addressType + ", street=" + street + ", city="
				+ city + ", state=" + state + ", postalCode=" + postalCode + "]";
	}
    
    

}
