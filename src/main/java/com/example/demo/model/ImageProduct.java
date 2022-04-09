package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="image_product")
public class ImageProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_image_product")
	private Long idImageProduct;
	
	@Column(name="image")
	private String image;

	@Column(name="id_phone_product")
	private Long idPhoneProduct;


	
	
	
	public Long getIdImageProduct() {
		return idImageProduct;
	}





	public void setIdImageProduct(Long idImageProduct) {
		this.idImageProduct = idImageProduct;
	}





	public String getImage() {
		return image;
	}





	public void setImage(String image) {
		this.image = image;
	}





	public Long getIdPhoneProduct() {
		return idPhoneProduct;
	}





	public void setIdPhoneProduct(Long idPhoneProduct) {
		this.idPhoneProduct = idPhoneProduct;
	}


	public ImageProduct(Long idImageProduct, String image, Long idPhoneProduct) {
		super();
		this.idImageProduct = idImageProduct;
		this.image = image;
		this.idPhoneProduct = idPhoneProduct;
	}


	public ImageProduct() {
		
	}

	@OneToOne(mappedBy = "imageProduct")
	ImagePhoneProduct imagePhoneProduct;
	
}
