package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comment")
	private Long idComment;
	
	@Column(name="content")
	private String content;
	
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="id_comment_detail")
	private Long idCommentDetail;

	@JoinColumn(name="id_staff")
	@ManyToOne
	Staff staff;
	
	@JoinColumn(name="id_phone_product")
	@ManyToOne
	PhoneProduct phoneProduct;
	
	
	@JoinColumn(name="id_customer")
	@ManyToOne
	Customer customer;


	@Column(name="trang_thai")
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


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
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


	public Comment(Long idComment, String content, LocalDate date, Long idCommentDetail, Staff staff,
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
	
	public Comment() {
		
	}
}
