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
@Table(name="color")
public class Color {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_color")
	private Long idColor;
	
	@Column(name="name_color")
	private String nameColor;

	public Long getIdColor() {
		return idColor;
	}

	public void setIdColor(Long idColor) {
		this.idColor = idColor;
	}

	public String getNameColor() {
		return nameColor;
	}

	public void setNameColor(String nameColor) {
		this.nameColor = nameColor;
	}

	public Color(Long idColor, String nameColor) {
		super();
		this.idColor = idColor;
		this.nameColor = nameColor;
	}
	
	public Color() {
		
	}
	
	@OneToMany(mappedBy = "color")
	List<PhoneProductDetail> phoneProductDetail;
}
