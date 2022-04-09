package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ImagePhoneProduct;
import com.example.demo.model.ImagePhoneProductDetail;

@Repository
public interface ImagePhoneProductRepository extends JpaRepository<ImagePhoneProduct, Long>{

	
	@Query(value = "SELECT ip.id_image_phone_product,i.image FROM `image_phone_product` ip INNER JOIN image_product i ON ip.id_image_product = i.id_image_product WHERE ip.id_phone_product = :idPhoneProduct",nativeQuery = true)
	List<ImagePhoneProductDetail> displayAllImageByIdPhone(@Param("idPhoneProduct") Long idPhoneProduct);
	
	
}
