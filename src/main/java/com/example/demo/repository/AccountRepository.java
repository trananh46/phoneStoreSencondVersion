package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Account;
import com.example.demo.model.AccountCustomer;
import com.example.demo.model.AccountStaff;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	@Query(value = "SELECT ac.id_account,c.name_customer,ac.email,ac.password FROM `account` ac INNER JOIN customer c ON ac.id_customer = c.id_customer WHERE user_type = 1",nativeQuery = true)
	List<AccountCustomer> displayAccountCustomer();
	
	
	@Query(value = "SELECT ac.id_account,s.name_staff,ac.email,ac.password FROM `account` ac INNER JOIN staff s ON ac.id_staff = s.id_staff WHERE user_type = 0",nativeQuery = true)
	List<AccountStaff> displayAccountStaff();
	
	@Query(value = "UPDATE account SET email=:email,password=:password,id_role=:idRole WHERE id_staff=:idStaff",nativeQuery = true)
	@Modifying
	@Transactional
	void updateAccount(@Param("idStaff") Long idStaff,@Param("email") String email,@Param("password") String password,@Param("idRole") Long idRole);
	
	
	@Query(value = "DELETE FROM account WHERE id_staff = :idStaff",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteAccount(@Param("idStaff") Long idStaff);
	
	
	@Query(value="UPDATE account SET email=:email,password=:password WHERE id_customer=:idCustomer",nativeQuery = true)
	@Modifying
	@Transactional
	void updateAccountCustomer(@Param("idCustomer") Long idCustomer,@Param("email") String email,@Param("password") String password);
	
	
	@Query(value = "DELETE FROM account WHERE id_customer = :idCustomer",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteAccountCustomer(@Param("idCustomer") Long idCustomer);
	
	
	@Query(value = "SELECT * FROM account WHERE email=:email",nativeQuery = true)
	Account findAccountByEmail(@Param("email") String email);
	
	@Query(value = "SELECT * FROM `account` WHERE id_customer = :idCustomer",nativeQuery = true)
	Account displayAccountByIdCustomer(@Param("idCustomer") Long idCustomer);
	
}
