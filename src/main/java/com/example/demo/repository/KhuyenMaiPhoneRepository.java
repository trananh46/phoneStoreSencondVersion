package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.KhuyenMaiPhoneProduct;
import com.example.demo.model.KhuyenMaiPhone;
import com.example.demo.model.KhuyenMaiPhoneProductDetail;

@Repository
public interface KhuyenMaiPhoneRepository extends JpaRepository<KhuyenMaiPhone, Long>{

	@Query(value = "SELECT kmp.id_khuyen_mai_phone,km.name_khuyen_mai FROM `khuyen_mai_phone` kmp INNER JOIN khuyen_mai km ON kmp.id_khuyen_mai = km.id_khuyen_mai WHERE kmp.id_phone_product = :idPhoneProduct",nativeQuery = true)
	List<KhuyenMaiPhoneProductDetail> displayKhuyenMaiByIdPhone(@Param("idPhoneProduct") Long idPhoneProduct);
	
	@Query(value = "SELECT km.*,kmp.id_phone_product FROM khuyen_mai km INNER JOIN khuyen_mai_phone kmp ON km.id_khuyen_mai = kmp.id_khuyen_mai",nativeQuery = true)
	List<KhuyenMaiPhoneProduct> displayKhuyenMaiByEachPhoneProduct();
}
