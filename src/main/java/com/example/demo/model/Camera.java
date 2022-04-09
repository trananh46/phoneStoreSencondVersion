package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="camera")
public class Camera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_camera")
	private Long idCamera;
	
	@Column(name="name_camera")
	private String nameCamera;

	public Long getIdCamera() {
		return idCamera;
	}

	public void setIdCamera(Long idCamera) {
		this.idCamera = idCamera;
	}

	public String getNameCamera() {
		return nameCamera;
	}

	public void setNameCamera(String nameCamera) {
		this.nameCamera = nameCamera;
	}

	public Camera(Long idCamera, String nameCamera) {
		super();
		this.idCamera = idCamera;
		this.nameCamera = nameCamera;
	}
	
	public Camera() {
		
	}
	
	@OneToMany(mappedBy = "camera")
	List<PhoneProductDetail> phoneProductDetail;

}
