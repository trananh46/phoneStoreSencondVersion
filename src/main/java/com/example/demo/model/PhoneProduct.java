package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="phone_product")
public class PhoneProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_phone_product")
	private Long idPhoneProduct;
	
	@Column(name="name_phone_product")
	private String namePhoneProduct;
	
	
	@OneToOne
	@JoinColumn(name="id_trang_thai")
	private TrangThaiPhoneProduct trangThaiPhoneProduct;
	
	
	
	@Column(name="image")
	private String image;
	
	
	@Column(name="price")
	private Long price;
	
	
	@OneToOne
	@JoinColumn(name="id_phone_product_detail")
	PhoneProductDetail phoneProductDetail;
	
	
	@OneToOne
	@JoinColumn(name="id_hang_dien_thoai")
	HangDienThoai hangDienThoai;


	@OneToOne(mappedBy = "phoneProduct2")
	PhoneProductDetail phoneProductDetail2;

	
	@OneToMany(mappedBy = "phoneProduct")
	List<ImagePhoneProduct> imagePhoneProduct;
	
	
	@OneToMany(mappedBy = "phoneProduct3")
	List<KhuyenMaiPhone> khuyenMaiPhone;
	
	
	@OneToMany(mappedBy = "phoneProduct")
	List<GioiThieuPhone> gioiThieuPhone;

	@OneToMany(mappedBy = "phoneProduct")
	private	List<ReceiptDetail> receiptDetail;
	
	@Column(name="trang_thai")
	private Long status;

	public Long getIdPhoneProduct() {
		return idPhoneProduct;
	}

	public void setIdPhoneProduct(Long idPhoneProduct) {
		this.idPhoneProduct = idPhoneProduct;
	}

	public String getNamePhoneProduct() {
		return namePhoneProduct;
	}

	public void setNamePhoneProduct(String namePhoneProduct) {
		this.namePhoneProduct = namePhoneProduct;
	}

	public TrangThaiPhoneProduct getTrangThaiPhoneProduct() {
		return trangThaiPhoneProduct;
	}

	public void setTrangThaiPhoneProduct(TrangThaiPhoneProduct trangThaiPhoneProduct) {
		this.trangThaiPhoneProduct = trangThaiPhoneProduct;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public PhoneProductDetail getPhoneProductDetail() {
		return phoneProductDetail;
	}

	public void setPhoneProductDetail(PhoneProductDetail phoneProductDetail) {
		this.phoneProductDetail = phoneProductDetail;
	}

	public HangDienThoai getHangDienThoai() {
		return hangDienThoai;
	}

	public void setHangDienThoai(HangDienThoai hangDienThoai) {
		this.hangDienThoai = hangDienThoai;
	}

	public PhoneProductDetail getPhoneProductDetail2() {
		return phoneProductDetail2;
	}

	public void setPhoneProductDetail2(PhoneProductDetail phoneProductDetail2) {
		this.phoneProductDetail2 = phoneProductDetail2;
	}

	public List<ImagePhoneProduct> getImagePhoneProduct() {
		return imagePhoneProduct;
	}

	public void setImagePhoneProduct(List<ImagePhoneProduct> imagePhoneProduct) {
		this.imagePhoneProduct = imagePhoneProduct;
	}

	public List<KhuyenMaiPhone> getKhuyenMaiPhone() {
		return khuyenMaiPhone;
	}

	public void setKhuyenMaiPhone(List<KhuyenMaiPhone> khuyenMaiPhone) {
		this.khuyenMaiPhone = khuyenMaiPhone;
	}

	public List<GioiThieuPhone> getGioiThieuPhone() {
		return gioiThieuPhone;
	}

	public void setGioiThieuPhone(List<GioiThieuPhone> gioiThieuPhone) {
		this.gioiThieuPhone = gioiThieuPhone;
	}

	public List<ReceiptDetail> getReceiptDetail() {
		return receiptDetail;
	}

	public void setReceiptDetail(List<ReceiptDetail> receiptDetail) {
		this.receiptDetail = receiptDetail;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public PhoneProduct(Long idPhoneProduct, String namePhoneProduct, TrangThaiPhoneProduct trangThaiPhoneProduct,
			String image, Long price, PhoneProductDetail phoneProductDetail, HangDienThoai hangDienThoai,
			PhoneProductDetail phoneProductDetail2, List<ImagePhoneProduct> imagePhoneProduct,
			List<KhuyenMaiPhone> khuyenMaiPhone, List<GioiThieuPhone> gioiThieuPhone, List<ReceiptDetail> receiptDetail,
			Long status) {
		super();
		this.idPhoneProduct = idPhoneProduct;
		this.namePhoneProduct = namePhoneProduct;
		this.trangThaiPhoneProduct = trangThaiPhoneProduct;
		this.image = image;
		this.price = price;
		this.phoneProductDetail = phoneProductDetail;
		this.hangDienThoai = hangDienThoai;
		this.phoneProductDetail2 = phoneProductDetail2;
		this.imagePhoneProduct = imagePhoneProduct;
		this.khuyenMaiPhone = khuyenMaiPhone;
		this.gioiThieuPhone = gioiThieuPhone;
		this.receiptDetail = receiptDetail;
		this.status = status;
	}
	
	public PhoneProduct() {
		
	}
	
	@OneToMany(mappedBy = "phoneProduct")
	List<Comment> comment;
	
}
