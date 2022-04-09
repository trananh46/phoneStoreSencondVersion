package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="image_phone_product")
public class ImagePhoneProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_image_phone_product")
	private Long idImagePhoneProduct;
	
	
	@OneToOne
	@JoinColumn(name="id_image_product")
	ImageProduct imageProduct;
	
	@OneToOne
	@JoinColumn(name="id_phone_product")
	PhoneProduct phoneProduct;

	public Long getIdImagePhoneProduct() {
		return idImagePhoneProduct;
	}

	public void setIdImagePhoneProduct(Long idImagePhoneProduct) {
		this.idImagePhoneProduct = idImagePhoneProduct;
	}

	public ImageProduct getImageProduct() {
		return imageProduct;
	}

	public void setImageProduct(ImageProduct imageProduct) {
		this.imageProduct = imageProduct;
	}

	public PhoneProduct getPhoneProduct() {
		return phoneProduct;
	}

	public void setPhoneProduct(PhoneProduct phoneProduct) {
		this.phoneProduct = phoneProduct;
	}

	public ImagePhoneProduct(Long idImagePhoneProduct, ImageProduct imageProduct, PhoneProduct phoneProduct) {
		super();
		this.idImagePhoneProduct = idImagePhoneProduct;
		this.imageProduct = imageProduct;
		this.phoneProduct = phoneProduct;
	}
	
	public ImagePhoneProduct() {
		
	}
	
}
