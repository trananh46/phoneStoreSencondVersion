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
@Table(name="gioi_thieu")
public class GioiThieu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_gioi_thieu")
	private Long idGioiThieu;
	
	@Column(name="title")
	private String title;
	
	@Column(name="post")
	private String post;
	
	@Column(name="image")
	private String image;
	
	@Column(name="loai_dien_thoai")
	private String loaiDienThoai;

	public Long getIdGioiThieu() {
		return idGioiThieu;
	}

	public void setIdGioiThieu(Long idGioiThieu) {
		this.idGioiThieu = idGioiThieu;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLoaiDienThoai() {
		return loaiDienThoai;
	}

	public void setLoaiDienThoai(String loaiDienThoai) {
		this.loaiDienThoai = loaiDienThoai;
	}

	public GioiThieu(Long idGioiThieu, String title, String post, String image, String loaiDienThoai) {
		super();
		this.idGioiThieu = idGioiThieu;
		this.title = title;
		this.post = post;
		this.image = image;
		this.loaiDienThoai = loaiDienThoai;
	}
	
	public GioiThieu() {
		
	}
	
	@OneToMany(mappedBy = "gioiThieu")
	private List<GioiThieu> gioiThieu;
}
