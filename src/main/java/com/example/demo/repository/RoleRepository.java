package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query(value = "SELECT * FROM role",nativeQuery = true)
	List<Role> displayRole();
	
	@Query(value = "SELECT * FROM role WHERE id_role = :idRole",nativeQuery = true)
	Role findRoleById(@Param("idRole") Long idRole);
	
	@Query(value = "UPDATE `role` SET `role_name` = :nameRole, `role_code` = :roleCode WHERE `role`.`id_role` = :idRole",nativeQuery = true)
	@Modifying
	@Transactional
	void updateRole(@Param("idRole") Long idRole,@Param("nameRole") String nameRole,@Param("roleCode") String roleCode);
	
	
	@Query(value = "DELETE FROM role WHERE id_role =:idRole",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteRole(@Param("idRole") Long idRole);
	

	@Query(value = "SELECT * FROM role WHERE role_code =:roleCode",nativeQuery = true)
	Role findRoleByRoleCode(@Param("roleCode") String roleCode);
	
	@Query(value = "SELECT r.id_role,r.role_name,r.role_code FROM `account` ac INNER JOIN role r ON ac.id_role = r.id_role WHERE ac.email = :email",nativeQuery = true)
	Role findRoleByEmail(@Param("email") String email);
}
