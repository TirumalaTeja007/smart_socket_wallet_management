package com.sana.avocado.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sana.avocado.enums.StatusEnum;


public class User {

	private Integer id;
	private String name;
	private String password;
	private String userName;
	private String email;
	private String countryCode;
	private String dialCode;
	private String mobileNumber;
	@Enumerated(EnumType.STRING)
	private StatusEnum status = StatusEnum.ACTIVE;
	private String brand;
	private String model;
	private String androidVersion;
	
	
	
	
	public User() {
	
	}
	public User(Integer id, String name, String password, String userName, String email, String countryCode,
			String dialCode, String mobileNumber, StatusEnum status, String brand, String model,
			String androidVersion) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.userName = userName;
		this.email = email;
		this.countryCode = countryCode;
		this.dialCode = dialCode;
		this.mobileNumber = mobileNumber;
		this.status = status;
		this.brand = brand;
		this.model = model;
		this.androidVersion = androidVersion;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAndroidVersion() {
		return androidVersion;
	}
	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDialCode() {
		return dialCode;
	}
	public void setDialCode(String dialCode) {
		this.dialCode = dialCode;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", userName=" + userName + ", email="
				+ email + ", countryCode=" + countryCode + ", dialCode=" + dialCode + ", mobileNumber=" + mobileNumber
				+ ", status=" + status + ", brand=" + brand + ", model=" + model
				+ ", androidVersion=" + androidVersion + "]";
	}
}
