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
@Table(name="receipt_detail")
public class ReceiptDetail {

	@Id
	@Column(name="id_receipt_detail")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReceiptDetail;
	
	@ManyToOne
	@JoinColumn(name="id_receipt")
	private Receipt receipt;
	
	@ManyToOne
	@JoinColumn(name="id_phone_product")
	private PhoneProduct phoneProduct;
	
	@Column(name="quantity")
	private Long quantity;
	
	@Column(name="total_price")
	private Long totalPrice;

	public Long getIdReceiptDetail() {
		return idReceiptDetail;
	}

	public void setIdReceiptDetail(Long idReceiptDetail) {
		this.idReceiptDetail = idReceiptDetail;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public PhoneProduct getPhoneProduct() {
		return phoneProduct;
	}

	public void setPhoneProduct(PhoneProduct phoneProduct) {
		this.phoneProduct = phoneProduct;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ReceiptDetail(Long idReceiptDetail, Receipt receipt, PhoneProduct phoneProduct, Long quantity,
			Long totalPrice) {
		super();
		this.idReceiptDetail = idReceiptDetail;
		this.receipt = receipt;
		this.phoneProduct = phoneProduct;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public ReceiptDetail() {
		
	}
}
