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
@Table(name="tinh_trang_phone_product")
public class TinhTrangPhoneProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tinh_trang_phone_product")
	private Long idTinhTrangPhoneProduct;
	
	@Column(name="name_tinh_trang")
	private String nameTinhTrang;

	public Long getIdTinhTrangPhoneProduct() {
		return idTinhTrangPhoneProduct;
	}

	public void setIdTinhTrangPhoneProduct(Long idTinhTrangPhoneProduct) {
		this.idTinhTrangPhoneProduct = idTinhTrangPhoneProduct;
	}

	public String getNameTinhTrang() {
		return nameTinhTrang;
	}

	public void setNameTinhTrang(String nameTinhTrang) {
		this.nameTinhTrang = nameTinhTrang;
	}

	public TinhTrangPhoneProduct(Long idTinhTrangPhoneProduct, String nameTinhTrang) {
		super();
		this.idTinhTrangPhoneProduct = idTinhTrangPhoneProduct;
		this.nameTinhTrang = nameTinhTrang;
	}
	
	public TinhTrangPhoneProduct() {
		
	}
	
	
	@OneToMany(mappedBy = "tinhTrangPhoneProduct")
	List<PhoneProductDetail> phoneProductDetail;
}
