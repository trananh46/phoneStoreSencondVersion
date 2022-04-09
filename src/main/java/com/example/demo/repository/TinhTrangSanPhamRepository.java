package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TinhTrangPhoneProduct;

@Repository
public interface TinhTrangSanPhamRepository extends JpaRepository<TinhTrangPhoneProduct, Long>{

	
	@Query(value = "SELECT * FROM tinh_trang_phone_product",nativeQuery = true)
	List<TinhTrangPhoneProduct> displayTinhTrangSanPham();
	
	@Query(value = "SELECT * FROM tinh_trang_phone_product WHERE id_tinh_trang_phone_product=:idTinhTrangPhoneProduct",nativeQuery = true)
	TinhTrangPhoneProduct findTinhTrangPhoneProductById(@Param("idTinhTrangPhoneProduct") Long idTinhTrangPhoneProduct);


	@Query(value = "SELECT * from tinh_trang_phone_product EXCEPT (SELECT tt.* FROM phone_product p INNER JOIN phoneproduct_detail pd ON p.id_phone_product_detail = pd.id_phone_product_detail INNER JOIN tinh_trang_phone_product tt ON pd.id_tinh_trang_phone_product = tt.id_tinh_trang_phone_product WHERE p.id_phone_product = :idPhoneProduct)",nativeQuery = true)
	List<TinhTrangPhoneProduct> displayListTinhTrangSanPhamTopUpdatePhoneProduct(@Param("idPhoneProduct") Long idPhoneProduct);
}
