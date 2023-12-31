package com.alianza.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResponseClientDTO {

	private Long id;
	private String sharedKey;
	private String businessId;
	private String email;
	private String phone;
	
	@JsonFormat(pattern="dd-MM-yyyy", timezone = "America/Bogota")
	private Date dataAdded;

	public ResponseClientDTO() {
		// Constructor empty
	}

	public ResponseClientDTO(Long id, String sharedKey, String businessId, String email, String phone,
			Date dataAdded) {
		this.id = id;
		this.sharedKey = sharedKey;
		this.businessId = businessId;
		this.email = email;
		this.phone = phone;
		this.dataAdded = dataAdded;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSharedKey() {
		return sharedKey;
	}

	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDataAdded() {
		return dataAdded;
	}

	public void setDataAdded(Date dataAdded) {
		this.dataAdded = dataAdded;
	}

}
