package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.KhuyenMai;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, Long>{

	@Query(value="SELECT * FROM khuyen_mai",nativeQuery = true)
	List<KhuyenMai> displayKhuyenMai();
	
	
	@Query(value = "SELECT * FROM khuyen_mai WHERE id_khuyen_mai =:idKhuyenMai",nativeQuery = true)
	KhuyenMai findKhuyenMaiById(@Param("idKhuyenMai") Long idKhuyenMai);
	
	@Query(value = "UPDATE khuyen_mai SET name_khuyen_mai = :nameKhuyenMai WHERE id_khuyen_mai = :idKhuyenMai",nativeQuery = true)
	@Modifying
	@Transactional
	void updateKhuyenMai(@Param("idKhuyenMai") Long idKhuyenMai,@Param("nameKhuyenMai") String nameKhuyenMai);
	
	@Query(value = "DELETE FROM khuyen_mai WHERE id_khuyen_mai =:idKhuyenMai",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteKhuyenMai(Long idKhuyenMai);
	
	@Query(value = "SELECT km.id_khuyen_mai,km.name_khuyen_mai FROM `phone_product` p INNER JOIN khuyen_mai_phone kmp ON p.id_phone_product = kmp.id_phone_product INNER JOIN khuyen_mai km ON kmp.id_khuyen_mai = km.id_khuyen_mai WHERE p.id_phone_product = :idPhoneProduct",nativeQuery = true)
	List<KhuyenMai> displayInformationKhuyenMaiPhoneProductDetailCustomer(@Param("idPhoneProduct") Long idPhoneProduct);
}
