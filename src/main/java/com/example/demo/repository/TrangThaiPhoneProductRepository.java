package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TrangThaiPhoneProduct;

@Repository
public interface TrangThaiPhoneProductRepository extends JpaRepository<TrangThaiPhoneProduct, Long>{

	@Query(value = "SELECT * FROM trangthai_phoneproduct",nativeQuery = true)
	List<TrangThaiPhoneProduct> displayTrangThaiSanPham();
	
	@Query(value = "SELECT * FROM trangthai_phoneproduct WHERE id_trang_thai=:idTrangThai",nativeQuery = true)
	TrangThaiPhoneProduct findTrangThaiPhoneProductById(@Param("idTrangThai") Long idTrangThai);
	
	@Query(value = "SELECT * from trangthai_phoneproduct EXCEPT (SELECT tt.* FROM phone_product p INNER JOIN phoneproduct_detail pd ON p.id_phone_product_detail = pd.id_phone_product_detail INNER JOIN trangthai_phoneproduct tt ON p.id_trang_thai = tt.id_trang_thai WHERE p.id_phone_product = :idPhoneProduct)",nativeQuery = true)
	List<TrangThaiPhoneProduct> displayTrangThaiSanPhamToUpdatePhoneProduct(@Param("idPhoneProduct") Long idPhoneProduct);
}
