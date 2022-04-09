package com.example.demo.DTO;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.model.Customer;
import com.example.demo.model.PhoneProduct;
import com.example.demo.model.Staff;

public class CommentDTO {

	private Long idComment;
	
	private String content;
	
	private String date;
	
	private Long idCommentDetail;

	Staff staff;
	
	PhoneProduct phoneProduct;
	
	Customer customer;

	private Long status;

	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getIdCommentDetail() {
		return idCommentDetail;
	}

	public void setIdCommentDetail(Long idCommentDetail) {
		this.idCommentDetail = idCommentDetail;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public PhoneProduct getPhoneProduct() {
		return phoneProduct;
	}

	public void setPhoneProduct(PhoneProduct phoneProduct) {
		this.phoneProduct = phoneProduct;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public CommentDTO(Long idComment, String content, String date, Long idCommentDetail, Staff staff,
			PhoneProduct phoneProduct, Customer customer, Long status) {
		super();
		this.idComment = idComment;
		this.content = content;
		this.date = date;
		this.idCommentDetail = idCommentDetail;
		this.staff = staff;
		this.phoneProduct = phoneProduct;
		this.customer = customer;
		this.status = status;
	}
	
	public CommentDTO() {
		
	}
}
