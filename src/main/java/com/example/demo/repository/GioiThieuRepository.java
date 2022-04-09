package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GioiThieu;

@Repository
public interface GioiThieuRepository extends JpaRepository<GioiThieu, Long>{

	@Query(value = "SELECT * FROM gioi_thieu",nativeQuery = true)
	List<GioiThieu> displayGioiThieu();
	
	
	@Query(value = "SELECT * FROM gioi_thieu WHERE id_gioi_thieu =:idGioiThieu",nativeQuery = true)
	GioiThieu findGioiThieuById(Long idGioiThieu);
	
	@Query(value = "UPDATE gioi_thieu SET title =:title,post=:post,loai_dien_thoai=:loaiDienThoai WHERE id_gioi_thieu =:idGioiThieu",nativeQuery = true)
	@Modifying
	@Transactional
	void updateGioiThieu(@Param("title") String title,@Param("post") String post,@Param("loaiDienThoai") String loaiDienThoai,@Param("idGioiThieu") Long idGioiThieu);
	
	@Query(value = "DELETE FROM gioi_thieu WHERE id_gioi_thieu =:idGioiThieu",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteGioiThieu(Long idGioiThieu);
	
	
	@Query(value = "SELECT * FROM `gioi_thieu` WHERE title LIKE %:hangDienThoai% OR loai_dien_thoai LIKE %:hangDienThoai%",nativeQuery = true)
	List<GioiThieu> displayGioiThieuByTitleAndLoaiDienThoai(@Param("hangDienThoai") String hangDienThoai);
	
	@Query(value = "SELECT gt.id_gioi_thieu,gt.title,gt.post,gt.image,gt.loai_dien_thoai FROM `phone_product` p INNER JOIN gioi_thieu_phone gtp ON p.id_phone_product = gtp.id_phone_product INNER JOIN gioi_thieu gt ON gtp.id_gioi_thieu = gt.id_gioi_thieu WHERE p.id_phone_product = :idPhoneProduct",nativeQuery = true)
	List<GioiThieu> displayGioiThieuPhoneProductDetailCustomer(@Param("idPhoneProduct") Long idPhoneProduct);


	@Query(value = "SELECT * FROM `gioi_thieu` ORDER BY RAND () LIMIT 9",nativeQuery = true)
	List<GioiThieu> displayGioiThieuPhoneInCustomerPage();
	
	
}
