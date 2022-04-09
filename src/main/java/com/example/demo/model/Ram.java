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
@Table(name="ram")
public class Ram {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_ram")
	private Long idRam;
	
	@Column(name="name_ram")
	private String nameRam;

	public Long getIdRam() {
		return idRam;
	}

	public void setIdRam(Long idRam) {
		this.idRam = idRam;
	}

	public String getNameRam() {
		return nameRam;
	}

	public void setNameRam(String nameRam) {
		this.nameRam = nameRam;
	}

	public Ram(Long idRam, String nameRam) {
		super();
		this.idRam = idRam;
		this.nameRam = nameRam;
	}
	
	public Ram() {
		
	}
	
	@OneToMany(mappedBy = "ram")
	List<PhoneProductDetail> phoneProductDetail;
}
