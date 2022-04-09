package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.informationPhoneAndReceiptDetailFromIdReceipt;
import com.example.demo.DTO.year;
import com.example.demo.model.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long>{

	@Query(value = "SELECT * FROM `receipt` WHERE date IS NULL AND id_trang_thai IS NULL",nativeQuery = true)
	public Receipt findReceiptStatusNullAndDateNull();
	
	@Query(value = "SELECT COUNT(id_receipt) FROM `receipt` WHERE date IS NULL AND id_trang_thai IS NULL",nativeQuery = true)
	public Long countReceiptStatusNullAndDateNull();
	
	@Query(value = "SELECT * FROM `receipt` WHERE date IS NULL AND note IS NULL AND id_trang_thai IS NULL AND total_price_receipt IS NULL AND id_customer = :idCustomer",nativeQuery = true)
	public List<Receipt> findReceiptByIdCustomerToUpdate(@Param("idCustomer") Long idCustomer);
	
	@Query(value = "SELECT * FROM `receipt` WHERE date IS NULL AND note IS NULL AND id_trang_thai IS NULL AND total_price_receipt IS NULL AND id_customer = :idCustomer",nativeQuery = true)
	public Receipt findReceiptByIdCustomerToPay(@Param("idCustomer") Long idCustomer);
	
	@Query(value = "SELECT r.*,c.name_customer FROM `receipt` r INNER JOIN customer c ON r.id_customer = c.id_customer WHERE r.id_trang_thai = 1;",nativeQuery = true)
	List<ReceiptAndInfromationCustomer> displayListReceiptHasNotApprovedYet();
	
	@Query(value = "SELECT * FROM receipt WHERE id_receipt = :idReceipt",nativeQuery = true)
	Receipt findReceiptById(@Param("idReceipt") Long idReceipt);
	
	
	@Query(value = "SELECT r.*,c.name_customer FROM `receipt` r INNER JOIN customer c ON r.id_customer = c.id_customer WHERE r.id_trang_thai = 2;",nativeQuery = true)
	List<ReceiptAndInfromationCustomer> displayListReceiptApproved();
	
	@Query(value = "DELETE FROM receipt WHERE id_receipt = :idReceipt",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteReceiptById(@Param("idReceipt") Long idReceipt);
	
	
	@Query(value = "select DISTINCT year(date) as years FROM receipt",nativeQuery = true)
	List<year> listYearsOfReceipt();
}
