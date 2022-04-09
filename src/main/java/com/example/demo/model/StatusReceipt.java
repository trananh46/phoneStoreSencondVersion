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
@Table(name="trangthai_hoadon")
public class StatusReceipt {

	@Id
	@Column(name="id_trang_thai")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public StatusReceipt(Long idTrangThai, String nameTrangThai) {
		super();
		this.idTrangThai = idTrangThai;
		this.nameTrangThai = nameTrangThai;
	}
	
	public StatusReceipt() {
	}
	
	@OneToMany(mappedBy = "statusReceipt")
	private List<Receipt> receipt;
}
