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
@Table(name="battery")
public class Battery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_battery")
	private Long idBattery;
	
	@Column(name="name_battery")
	private String nameBattery;

	public Long getIdBattery() {
		return idBattery;
	}

	public void setIdBattery(Long idBattery) {
		this.idBattery = idBattery;
	}

	public String getNameBattery() {
		return nameBattery;
	}

	public void setNameBattery(String nameBattery) {
		this.nameBattery = nameBattery;
	}

	public Battery(Long idBattery, String nameBattery) {
		super();
		this.idBattery = idBattery;
		this.nameBattery = nameBattery;
	}
	
	public Battery() {
		
	}
	
	
	@OneToMany(mappedBy = "battery")
	List<PhoneProductDetail> phoneProductDetail;
}
