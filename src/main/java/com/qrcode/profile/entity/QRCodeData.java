package com.qrcode.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "qr_code_data")
@Data
public class QRCodeData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column
	private String landline;

	@Column
	private String mobile;

	@Column
	private String email;

	@Column
	private String website;

	@Column
	private String address;

	@Column
	private String company;

	@Column
	private String designation;

	@Column(nullable = false)
	private String secureKey;
}
