package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PhoneProductDetail;

@Repository
public interface PhoneProductDetailRepository extends JpaRepository<PhoneProductDetail, Long>{

	@Query(value = "SELECT * FROM phoneproduct_detail WHERE id_phone_product_detail = :idPhoneProductDetail",nativeQuery = true)
	PhoneProductDetail findPhoneProductDetailById(@Param("idPhoneProductDetail") Long idPhoneProductDetail);
	
	
	@Query(value = "SELECT * FROM phoneproduct_detail WHERE id_phone_product = :idPhoneProduct",nativeQuery = true)
	PhoneProductDetail findPhoneProductDetailByIdPhoneProduct(@Param("idPhoneProduct") Long idPhoneProduct);
}
