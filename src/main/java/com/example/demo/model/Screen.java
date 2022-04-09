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
@Table(name="screen")
public class Screen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_screen")
	private Long idScreen;
	
	
	@Column(name="technology_screen")
	private String technologyScreen;
	
	@Column(name="do_phan_giai")
	private String doPhanGiai;

	public Long getIdScreen() {
		return idScreen;
	}

	public void setIdScreen(Long idScreen) {
		this.idScreen = idScreen;
	}

	public String getTechnologyScreen() {
		return technologyScreen;
	}

	public void setTechnologyScreen(String technologyScreen) {
		this.technologyScreen = technologyScreen;
	}

	public String getDoPhanGiai() {
		return doPhanGiai;
	}

	public void setDoPhanGiai(String doPhanGiai) {
		this.doPhanGiai = doPhanGiai;
	}

	public Screen(Long idScreen, String technologyScreen, String doPhanGiai) {
		super();
		this.idScreen = idScreen;
		this.technologyScreen = technologyScreen;
		this.doPhanGiai = doPhanGiai;
	}
	
	public Screen() {
		
	}
	
	
	@OneToMany(mappedBy = "screen")
	List<PhoneProductDetail> phoneProductDetail;
}
