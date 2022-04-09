package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="trangthai_phoneproduct")
public class TrangThaiPhoneProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_trang_thai")
	private Long idTrangThai;
	
	@Column(name="name_trang_thai")
	private String nameTrangThai;

	public Long getIdTrangThai() {
		return idTrangThai;
	}

	public void setIdTrangThai(Long idTrangThai) {
		this.idTrangThai = idTrangThai;
	}

	public String getNameTrangThai() {
		return nameTrangThai;
	}

	public void setNameTrangThai(String nameTrangThai) {
		this.nameTrangThai = nameTrangThai;
	}

	public TrangThaiPhoneProduct(Long idTrangThai, String nameTrangThai) {
		super();
		this.idTrangThai = idTrangThai;
		this.nameTrangThai = nameTrangThai;
	}
	
	public TrangThaiPhoneProduct() {
		
	}
	
	@OneToMany(mappedBy = "trangThaiPhoneProduct")
	List<PhoneProduct> listPhoneProduct;
	
	
	
}
