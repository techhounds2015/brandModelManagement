package com.kashitkalaecom.brandmodelmgmt.models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Entity
@Table(name="storesetting")
@DiscriminatorValue("E")
public class StoreSetting {
	
	@Column(name="storename")
	private String storeName;
	private String address;
	private String city;
	private String state;
	
	@Column(name="createdby")
	private String pinCode;
	private String country;
	
	@Column(name="supportemail")
	private String supportEmail;
	
	@Column(name="supportphonenumber")
	private String supportPhoneNumber;
	
	@Column(name="companyname")
	private String companyName;
	
	@Column(name="gstnumber")
	private String gstNumber;
	
	@Column(name="playstorelink")
	private String playStoreLink;
	
	@Column(name="instagramlink")
	private String instagramLink;
	
	@Column(name="facebooklink")
	private String facebookLink;
	
	@Column(name="appstorelink")
	private String appStoreLink;
	
	@Column(name="twitterlink")
	private String twitterLink;
	
	@Column(name="phonenumberoninvoice")
	private String phoneNumberOnInvoice;
	
	@Column(name="whatsAppnumber")
	private String whatsAppNumber;
	private String logo;
	private String favicon;
	private Boolean status;
	
	@Id //@GeneratedValue(strategy = GenerationType.AUTO)
	protected String id = UUID.randomUUID().toString();
	@Column(name="createdby")
	protected String createdBy;
	@Column(name="createdon")
	protected Timestamp createdOn =  CustomClock.timestamp();
	@Column(name="modifiedby")
	protected String modifiedBy;
	@Column(name="modifiedon")
	protected Timestamp modifiedOn;
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSupportEmail() {
		return supportEmail;
	}
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
	public String getSupportPhoneNumber() {
		return supportPhoneNumber;
	}
	public void setSupportPhoneNumber(String supportPhoneNumber) {
		this.supportPhoneNumber = supportPhoneNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public String getPlayStoreLink() {
		return playStoreLink;
	}
	public void setPlayStoreLink(String playStoreLink) {
		this.playStoreLink = playStoreLink;
	}
	public String getInstagramLink() {
		return instagramLink;
	}
	public void setInstagramLink(String instagramLink) {
		this.instagramLink = instagramLink;
	}
	public String getFacebookLink() {
		return facebookLink;
	}
	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}
	public String getAppStoreLink() {
		return appStoreLink;
	}
	public void setAppStoreLink(String appStoreLink) {
		this.appStoreLink = appStoreLink;
	}
	public String getTwitterLink() {
		return twitterLink;
	}
	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}
	public String getPhoneNumberOnInvoice() {
		return phoneNumberOnInvoice;
	}
	public void setPhoneNumberOnInvoice(String phoneNumberOnInvoice) {
		this.phoneNumberOnInvoice = phoneNumberOnInvoice;
	}
	public String getWhatsAppNumber() {
		return whatsAppNumber;
	}
	public void setWhatsAppNumber(String whatsAppNumber) {
		this.whatsAppNumber = whatsAppNumber;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getFavicon() {
		return favicon;
	}
	public void setFavicon(String favicon) {
		this.favicon = favicon;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
}
