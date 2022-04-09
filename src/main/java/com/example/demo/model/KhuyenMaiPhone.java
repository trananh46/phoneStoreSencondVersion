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
@Table(name="khuyen_mai_phone")
public class KhuyenMaiPhone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_khuyen_mai_phone")
	private Long idKhuyenKhuyenMaiPhone;
	
	@ManyToOne
	@JoinColumn(name="id_khuyen_mai")
	private KhuyenMai khuyenMai;
	
	@ManyToOne
	@JoinColumn(name="id_phone_product")
	private PhoneProduct phoneProduct3;

	public Long getIdKhuyenKhuyenMaiPhone() {
		return idKhuyenKhuyenMaiPhone;
	}

	public void setIdKhuyenKhuyenMaiPhone(Long idKhuyenKhuyenMaiPhone) {
		this.idKhuyenKhuyenMaiPhone = idKhuyenKhuyenMaiPhone;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public PhoneProduct getPhoneProduct3() {
		return phoneProduct3;
	}

	public void setPhoneProduct3(PhoneProduct phoneProduct3) {
		this.phoneProduct3 = phoneProduct3;
	}

	public KhuyenMaiPhone(Long idKhuyenKhuyenMaiPhone, KhuyenMai khuyenMai, PhoneProduct phoneProduct3) {
		super();
		this.idKhuyenKhuyenMaiPhone = idKhuyenKhuyenMaiPhone;
		this.khuyenMai = khuyenMai;
		this.phoneProduct3 = phoneProduct3;
	}
	
	public KhuyenMaiPhone() {
		
	}
	
}
