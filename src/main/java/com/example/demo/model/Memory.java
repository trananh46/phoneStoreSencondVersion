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
@Table(name="memory")
public class Memory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_memory")
	private Long idMemory;
	
	@Column(name="name_memory")
	private String nameMemory;

	public Long getIdMemory() {
		return idMemory;
	}

	public void setIdMemory(Long idMemory) {
		this.idMemory = idMemory;
	}

	public String getNameMemory() {
		return nameMemory;
	}

	public void setNameMemory(String nameMemory) {
		this.nameMemory = nameMemory;
	}

	public Memory(Long idMemory, String nameMemory) {
		super();
		this.idMemory = idMemory;
		this.nameMemory = nameMemory;
	}
	
	public Memory() {
		
	}
	
	
	@OneToMany(mappedBy = "memory")
	List<PhoneProductDetail> phoneProductDetail;
	
	
}
