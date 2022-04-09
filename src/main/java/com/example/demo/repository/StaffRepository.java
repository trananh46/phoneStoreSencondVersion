package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ListStaff;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.model.Staff;
import com.example.demo.model.StaffUpdate;

public interface StaffRepository extends JpaRepository<Staff, Long>{

	@Query(value = "SELECT s.id_staff,s.name_staff,ac.email,r.role_name,s.sdt FROM `staff` s INNER JOIN account ac ON s.id_staff = ac.id_staff INNER JOIN role r ON ac.id_role = r.id_role",nativeQuery = true)
	List<ListStaff> displayStaff();
	
	
	@Query(value = "SELECT * FROM staff WHERE name_staff=:nameStaff and sdt=:sdt",nativeQuery = true)
	Staff findStaffByNameAndPhoneNumber(@Param("nameStaff") String nameStaff,@Param("sdt") String sdt);
	
	
	@Query(value = "SELECT s.id_staff,s.name_staff,ac.email,ac.password,r.id_role,r.role_name,s.sdt FROM `staff` s INNER JOIN account ac ON s.id_staff = ac.id_staff INNER JOIN role r ON ac.id_role = r.id_role WHERE s.id_staff = :idStaff",nativeQuery = true)
	StaffUpdate findStaffByIdToUpdate(@Param("idStaff") Long idStaff);
	
	@Query(value = "UPDATE staff SET name_staff = :nameStaff,sdt=:sdt WHERE id_staff =:idStaff",nativeQuery = true)
	@Modifying
	@Transactional
	void updateStaff(@Param("idStaff") Long idStaff,@Param("nameStaff") String nameStaff,@Param("sdt") String sdt);
	
	
	@Query(value = "DELETE FROM staff WHERE id_staff=:idStaff",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteStaff(@Param("idStaff") Long idStaff);
	
	
	@Query(value = "SELECT s.id_staff,s.name_staff,r.role_code,ac.email FROM `account` ac INNER JOIN staff s ON ac.id_staff = s.id_staff INNER JOIN role r ON ac.id_role = r.id_role WHERE ac.email = :email",nativeQuery = true)
	NameAndRoleStaff displayNameAndRoleStaff(@Param("email") String email);
}
