package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ImageProduct;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, Long>{

	
	
	@Query(value = "SELECT * FROM image_product WHERE id_phone_product =:idPhoneProduct AND image=:image",nativeQuery = true)
	ImageProduct findImageByNameAndIdProduct(@Param("idPhoneProduct") Long idPhoneProduct,@Param("image") String image);
	
	
	@Query(value = "SELECT ip.id_image_product,ip.image,ip.id_phone_product FROM `phone_product` p INNER JOIN image_phone_product ipd ON p.id_phone_product = ipd.id_phone_product INNER JOIN image_product ip ON ipd.id_image_product = ip.id_image_product WHERE p.id_phone_product = :idPhoneProduct",nativeQuery = true)
	List<ImageProduct> displayListImagePhoneProductDetailCustomer(@Param("idPhoneProduct") Long idPhoneProduct);
}
