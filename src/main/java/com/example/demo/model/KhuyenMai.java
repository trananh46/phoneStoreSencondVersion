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
@Table(name="khuyen_mai")
public class KhuyenMai {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_khuyen_mai")
	private Long idKhuyenMai;
	
	@Column(name="name_khuyen_mai")
	private String nameKhuyenMai;

	public Long getIdKhuyenMai() {
		return idKhuyenMai;
	}

	public void setIdKhuyenMai(Long idKhuyenMai) {
		this.idKhuyenMai = idKhuyenMai;
	}

	public String getNameKhuyenMai() {
		return nameKhuyenMai;
	}

	public void setNameKhuyenMai(String nameKhuyenMai) {
		this.nameKhuyenMai = nameKhuyenMai;
	}

	public KhuyenMai(Long idKhuyenMai, String nameKhuyenMai) {
		super();
		this.idKhuyenMai = idKhuyenMai;
		this.nameKhuyenMai = nameKhuyenMai;
	}
	
	public KhuyenMai() {
		
	}
	
	@OneToMany(mappedBy = "khuyenMai")
	private List<KhuyenMaiPhone> khuyenMaiPhone;
}
