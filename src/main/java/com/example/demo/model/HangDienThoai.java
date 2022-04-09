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
@Table(name="hang_dienthoai")
public class HangDienThoai {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_hang_dien_thoai")
	private Long idHangDienThoai;
	
	@Column(name="name_hang_dien_thoai")
	private String nameHangDienThoai;
	
	@Column(name="image")
	private String image;

	public Long getIdHangDienThoai() {
		return idHangDienThoai;
	}

	public void setIdHangDienThoai(Long idHangDienThoai) {
		this.idHangDienThoai = idHangDienThoai;
	}

	public String getNameHangDienThoai() {
		return nameHangDienThoai;
	}

	public void setNameHangDienThoai(String nameHangDienThoai) {
		this.nameHangDienThoai = nameHangDienThoai;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public HangDienThoai(Long idHangDienThoai, String nameHangDienThoai, String image) {
		super();
		this.idHangDienThoai = idHangDienThoai;
		this.nameHangDienThoai = nameHangDienThoai;
		this.image = image;
	}
	
	public HangDienThoai() {
		
	}
	
	@OneToMany(mappedBy = "hangDienThoai")
	List<PhoneProduct> phoneProduct;
}
