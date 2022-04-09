package com.example.demo.model;

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
@Table(name="phoneproduct_detail")
public class PhoneProductDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_phone_product_detail")
	private Long idPhoneProductDetail;
	
	@OneToOne
	@JoinColumn(name="id_color")
	private Color color;
	
	@Column(name="quantity")
	private Long quantity;
	
	
	@OneToOne
	@JoinColumn(name="id_ram")
	private Ram ram;
	
	@OneToOne
	@JoinColumn(name="id_screen")
	private Screen screen;
	
	@OneToOne
	@JoinColumn(name="id_battery")
	private Battery battery;
	
	
	@OneToOne
	@JoinColumn(name="id_memory")
	private Memory memory;
	
	@Column(name="date_input_product")
	private String dateInputProduct;
	
	@OneToOne
	@JoinColumn(name="id_tinh_trang_phone_product")
	private TinhTrangPhoneProduct tinhTrangPhoneProduct;
	
	
	@OneToOne
	@JoinColumn(name="id_camera")
	private Camera camera;

	@OneToOne
	@JoinColumn(name="id_phone_product")
	private PhoneProduct phoneProduct2;
	

	@OneToOne(mappedBy = "phoneProductDetail")
	PhoneProduct phoneProduct;


	public Long getIdPhoneProductDetail() {
		return idPhoneProductDetail;
	}


	public void setIdPhoneProductDetail(Long idPhoneProductDetail) {
		this.idPhoneProductDetail = idPhoneProductDetail;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public Ram getRam() {
		return ram;
	}


	public void setRam(Ram ram) {
		this.ram = ram;
	}


	public Screen getScreen() {
		return screen;
	}


	public void setScreen(Screen screen) {
		this.screen = screen;
	}


	public Battery getBattery() {
		return battery;
	}


	public void setBattery(Battery battery) {
		this.battery = battery;
	}


	public Memory getMemory() {
		return memory;
	}


	public void setMemory(Memory memory) {
		this.memory = memory;
	}


	public String getDateInputProduct() {
		return dateInputProduct;
	}


	public void setDateInputProduct(String dateInputProduct) {
		this.dateInputProduct = dateInputProduct;
	}


	public TinhTrangPhoneProduct getTinhTrangPhoneProduct() {
		return tinhTrangPhoneProduct;
	}


	public void setTinhTrangPhoneProduct(TinhTrangPhoneProduct tinhTrangPhoneProduct) {
		this.tinhTrangPhoneProduct = tinhTrangPhoneProduct;
	}


	public Camera getCamera() {
		return camera;
	}


	public void setCamera(Camera camera) {
		this.camera = camera;
	}


	public PhoneProduct getPhoneProduct2() {
		return phoneProduct2;
	}


	public void setPhoneProduct2(PhoneProduct phoneProduct2) {
		this.phoneProduct2 = phoneProduct2;
	}


	public PhoneProductDetail(Long idPhoneProductDetail, Color color, Long quantity, Ram ram, Screen screen,
			Battery battery, Memory memory, String dateInputProduct, TinhTrangPhoneProduct tinhTrangPhoneProduct,
			Camera camera, PhoneProduct phoneProduct2) {
		super();
		this.idPhoneProductDetail = idPhoneProductDetail;
		this.color = color;
		this.quantity = quantity;
		this.ram = ram;
		this.screen = screen;
		this.battery = battery;
		this.memory = memory;
		this.dateInputProduct = dateInputProduct;
		this.tinhTrangPhoneProduct = tinhTrangPhoneProduct;
		this.camera = camera;
		this.phoneProduct2 = phoneProduct2;
	}


	public PhoneProductDetail() {
		
	}
	
}
