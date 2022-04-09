package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="staff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_staff")
	private Long idStaff;
	
	@Column(name="name_staff")
	private String nameStaff;
	
	@Column(name="sdt")
	private String sdt;

	public Long getIdStaff() {
		return idStaff;
	}

	public void setIdStaff(Long idStaff) {
		this.idStaff = idStaff;
	}

	public String getNameStaff() {
		return nameStaff;
	}

	public void setNameStaff(String nameStaff) {
		this.nameStaff = nameStaff;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public Staff(Long idStaff, String nameStaff, String sdt) {
		super();
		this.idStaff = idStaff;
		this.nameStaff = nameStaff;
		this.sdt = sdt;
	}
	
	public Staff() {
		
	}
	
	@OneToOne(mappedBy = "staff")
	private Account account;

	@OneToMany(mappedBy = "staff")
	private List<Comment> lisComments;
	
	
}
