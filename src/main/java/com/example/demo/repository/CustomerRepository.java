package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerList;
import com.example.demo.model.CustomerUpdate;
import com.example.demo.model.NameAndEmailCustomer;

@Repository
public interface CustomerRepository extends	JpaRepository<Customer, Long>{

	@Query(value = "SELECT * FROM customer WHERE name_customer=:nameCustomer AND phone_number=:phoneNumber",nativeQuery = true)
	Customer findCustomerByNameAndPhoneNumber(@Param("nameCustomer") String nameCustomer,@Param("phoneNumber") String sdt);
	
	
	@Query(value = "SELECT c.id_customer,c.name_customer,ac.email,c.phone_number FROM `customer` c INNER JOIN account ac ON c.id_customer = ac.id_customer",nativeQuery = true)
	List<CustomerList> displayCustomer();
	
	
	@Query(value = "SELECT c.id_customer,c.name_customer,ac.email,ac.password,c.phone_number FROM `customer` c INNER JOIN account ac ON c.id_customer = ac.id_customer WHERE c.id_customer = :idCustomer",nativeQuery = true)
	CustomerUpdate findCustomerToUpdate(@Param("idCustomer") Long idCustomer);
	
	
	@Query(value = "UPDATE customer SET name_customer=:nameCustomer,phone_number=:sdt WHERE id_customer=:idCustomer",nativeQuery = true)
	@Modifying
	@Transactional
	void updateCustomerById(@Param("idCustomer") Long idCustomer,@Param("nameCustomer") String nameCustomer,@Param("sdt") String sdt);
	
	@Query(value = "DELETE FROM customer WHERE id_customer=:idCustomer",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteCustomerById(Long idCustomer);
	
	@Query(value = "SELECT c.id_customer,c.name_customer,ac.email FROM `account` ac INNER JOIN customer c ON ac.id_customer = c.id_customer WHERE email = :email",nativeQuery = true)
	NameAndEmailCustomer displayNameAndEmailCustomer(@Param("email") String email);
	
	@Query(value = "SELECT * FROM `account` ac INNER JOIN customer c ON ac.id_customer = c.id_customer WHERE ac.email = :email",nativeQuery = true)
	Customer findCustomerByEmail(@Param("email") String email);
	
	@Query(value = "SELECT * FROM customer WHERE id_customer = :idCustomer",nativeQuery = true)
	Customer findCustomerById(@Param("idCustomer") Long idCustomer);
	
	
}
