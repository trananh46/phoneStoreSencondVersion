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
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_role")
	private Long idRole;
		
	@Column(name="role_name")
	private String nameRole;
	
	@Column(name="role_code")
	private String codeRole;

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public String getCodeRole() {
		return codeRole;
	}

	public void setCodeRole(String codeRole) {
		this.codeRole = codeRole;
	}

	public Role(Long idRole, String nameRole, String codeRole) {
		super();
		this.idRole = idRole;
		this.nameRole = nameRole;
		this.codeRole = codeRole;
	}
	
	public Role() {
		
	}
	
	
	@OneToMany(mappedBy = "role")
	private List<Account> account;
}
