package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "receipt")
public class Receipt {

	@Id
	@Column(name = "id_receipt")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReceipt;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "note")
	private String note;

	@ManyToOne
	@JoinColumn(name = "id_customer")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "id_trang_thai")
	private StatusReceipt statusReceipt;

	@Column(name = "total_price_receipt")
	private Long totalPriceReceipt;

	public Long getIdReceipt() {
		return idReceipt;
	}

	public void setIdReceipt(Long idReceipt) {
		this.idReceipt = idReceipt;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public StatusReceipt getStatusReceipt() {
		return statusReceipt;
	}

	public void setStatusReceipt(StatusReceipt statusReceipt) {
		this.statusReceipt = statusReceipt;
	}

	public Long getTotalPriceReceipt() {
		return totalPriceReceipt;
	}

	public void setTotalPriceReceipt(Long totalPriceReceipt) {
		this.totalPriceReceipt = totalPriceReceipt;
	}

	
	
	public Receipt(Long idReceipt, LocalDate date, String note, Customer customer, StatusReceipt statusReceipt,
			Long totalPriceReceipt) {
		super();
		this.idReceipt = idReceipt;
		this.date = date;
		this.note = note;
		this.customer = customer;
		this.statusReceipt = statusReceipt;
		this.totalPriceReceipt = totalPriceReceipt;
	}

	public Receipt() {
		
	}

	@OneToMany(mappedBy = "receipt")
	private List<ReceiptDetail> receiptDetail;
}
