package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="gioi_thieu_phone")
public class GioiThieuPhone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_gioi_thieu_phone_product")
	private Long idGioiThieuPhoneProduct;

	@ManyToOne
	@JoinColumn(name="id_gioi_thieu")
	private GioiThieu gioiThieu;
	
	@ManyToOne
	@JoinColumn(name="id_phone_product")
	private PhoneProduct phoneProduct;

	public Long getIdGioiThieuPhoneProduct() {
		return idGioiThieuPhoneProduct;
	}

	public void setIdGioiThieuPhoneProduct(Long idGioiThieuPhoneProduct) {
		this.idGioiThieuPhoneProduct = idGioiThieuPhoneProduct;
	}

	public GioiThieu getGioiThieu() {
		return gioiThieu;
	}

	public void setGioiThieu(GioiThieu gioiThieu) {
		this.gioiThieu = gioiThieu;
	}

	public PhoneProduct getPhoneProduct() {
		return phoneProduct;
	}

	public void setPhoneProduct(PhoneProduct phoneProduct) {
		this.phoneProduct = phoneProduct;
	}

	public GioiThieuPhone(Long idGioiThieuPhoneProduct, GioiThieu gioiThieu, PhoneProduct phoneProduct) {
		super();
		this.idGioiThieuPhoneProduct = idGioiThieuPhoneProduct;
		this.gioiThieu = gioiThieu;
		this.phoneProduct = phoneProduct;
	}
	
	public GioiThieuPhone() {
		
	}
	
}
