package com.alianza.dto;

public class RequestClientDTO {

	private String sharedKey;
	private String businessId;
	private String email;
	private String phone;
	private String dataAdded;

	public RequestClientDTO() {
		// Constructor empty
	}

	public RequestClientDTO(Long id, String sharedKey, String businessId, String email, String phone,
			String dataAdded) {
		this.sharedKey = sharedKey;
		this.businessId = businessId;
		this.email = email;
		this.phone = phone;
		this.dataAdded = dataAdded;
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

	public String getDataAdded() {
		return dataAdded;
	}

	public void setDataAdded(String dataAdded) {
		this.dataAdded = dataAdded;
	}

}
