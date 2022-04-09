package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GioiThieuPhone;
import com.example.demo.model.GioiThieuPhoneProductDetail;

@Repository
public interface GioiThieuPhoneRepository extends JpaRepository<GioiThieuPhone, Long>{


	@Query(value = "SELECT gtp.id_gioi_thieu_phone_product,gt.title,gt.post,gt.image FROM `gioi_thieu_phone` gtp INNER JOIN gioi_thieu gt ON gtp.id_gioi_thieu = gt.id_gioi_thieu WHERE gtp.id_phone_product = :idPhoneProduct",nativeQuery = true)
	List<GioiThieuPhoneProductDetail> displayGioiThieuPhoneProductDetailByIdPhone(@Param("idPhoneProduct") Long idPhoneProduct);
	
	@Query(value = "DELETE FROM gioi_thieu_phone WHERE id_gioi_thieu_phone_product = :idGioiThieuPhone",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteGioiThieuPhoneProductDetail(@Param("idGioiThieuPhone") Long idGioiThieuPhone);
}
