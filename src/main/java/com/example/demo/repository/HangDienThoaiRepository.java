package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.StatisticBudgetByKindOfPhone;
import com.example.demo.DTO.informationPhoneAndReceiptDetailFromIdReceipt;
import com.example.demo.model.HangDienThoai;

@Repository
public interface HangDienThoaiRepository extends JpaRepository<HangDienThoai, Long>{

	@Query(value = "SELECT * FROM hang_dienthoai",nativeQuery = true)
	List<HangDienThoai> displayHangDienThoai();
	
	@Query(value = "DELETE FROM hang_dienthoai WHERE id_hang_dien_thoai =:idHangDienThoai",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteHangDienThoaiById(@Param("idHangDienThoai") Long idHangDienThoai);
	
	
	@Query(value = "UPDATE hang_dienthoai SET name_hang_dien_thoai =:nameHangDienThoai WHERE id_hang_dien_thoai =:idHangDienThoai",nativeQuery = true)
	@Modifying
	@Transactional
	void updateHangDienThoai(@Param("nameHangDienThoai") String nameHangDienThoai,@Param("idHangDienThoai") Long idHangDienThoai);
	
	@Query(value = "SELECT * FROM hang_dienthoai WHERE id_hang_dien_thoai =:idHangDienThoai",nativeQuery = true)
	HangDienThoai findHangDienThoaiById(@Param("idHangDienThoai") Long idHangDienThoai);
	
	@Query(value = "SELECT h.id_hang_dien_thoai,h.name_hang_dien_thoai,SUM(rd.total_price) AS totalMoney,SUM(rd.quantity) AS quantity FROM `receipt_detail` rd INNER JOIN receipt r ON rd.id_receipt = r.id_receipt INNER JOIN phone_product p ON rd.id_phone_product = p.id_phone_product INNER JOIN hang_dienthoai h ON p.id_hang_dien_thoai = h.id_hang_dien_thoai WHERE r.id_trang_thai = 2 GROUP BY (h.name_hang_dien_thoai) ORDER BY (totalMoney) DESC",nativeQuery = true)
	List<StatisticBudgetByKindOfPhone> listStatisticBudgetKindOfPhone();
	
	@Query(value = "select pd.id_phone_product,pd.name_phone_product,rd.quantity,rd.total_price,c.name_color,m.name_memory,ra.name_ram,tt.name_tinh_trang,pd.image from receipt_detail rd INNER JOIN receipt r ON rd.id_receipt = r.id_receipt INNER JOIN phone_product pd ON rd.id_phone_product = pd.id_phone_product INNER JOIN phoneproduct_detail pdt ON pd.id_phone_product = pdt.id_phone_product INNER JOIN color c ON pdt.id_color = c.id_color INNER JOIN memory m ON pdt.id_memory = m.id_memory INNER JOIN tinh_trang_phone_product tt ON pdt.id_tinh_trang_phone_product = tt.id_tinh_trang_phone_product INNER JOIN ram ra ON pdt.id_ram = ra.id_ram WHERE r.id_trang_thai = 2 AND pd.id_hang_dien_thoai = :idHangDienThoai",nativeQuery = true)
	List<informationPhoneAndReceiptDetailFromIdReceipt> displayListPhoneProductByIdHangDienThoai(@Param("idHangDienThoai") Long idHangDienThoai);
	
	@Query(value = "SELECT * from hang_dienthoai EXCEPT (SELECT h.* FROM phone_product p INNER JOIN phoneproduct_detail pd ON p.id_phone_product_detail = pd.id_phone_product_detail INNER JOIN hang_dienthoai h ON p.id_hang_dien_thoai = h.id_hang_dien_thoai WHERE p.id_phone_product = :idPhoneProduct)",nativeQuery = true)
	List<HangDienThoai> listHangDienThoaiToUpdatePhoneProduct(@Param("idPhoneProduct") Long idPhoneProduct);
}
