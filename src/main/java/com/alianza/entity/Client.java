package com.alianza.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que contiene los campos de la tabla
 * @author Jhon Torres
 * @version 2023-10-02
 */
@Entity
@Table(name = "CLIENTES")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "SHARED_KEY", length = 60, nullable = false)
	private String sharedKey;

	@Column(name = "BUSINESS_ID", length = 60, nullable = false)
	private String businessId;

	@Column(name = "EMAIL", length = 60, nullable = false, unique = true)
	private String email;

	@Column(name = "PHONE", length = 60, nullable = false, unique = true)
	private String phone;

	@Column(name = "DATA_ADDED", length = 60, nullable = false, unique = true)
	private Date dataAdded;

	public Client() {
		// Constructor empty
	}

	public Client(Long id, String sharedKey, String businessId, String email, String phone, Date dataAdded) {
		super();
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
