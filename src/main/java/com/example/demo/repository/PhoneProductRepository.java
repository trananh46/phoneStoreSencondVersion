package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.InformationUpdatePhoneProduct;
import com.example.demo.DTO.StatisticBudget;
import com.example.demo.DTO.informationPhoneAndReceiptDetailFromIdReceipt;
import com.example.demo.model.DisplayProductCustomer;
import com.example.demo.model.InformationConfigurePhoneProductCustomer;
import com.example.demo.model.InformationPhoneProductDetailCustomer;
import com.example.demo.model.PhoneProduct;
import com.example.demo.model.PhoneProductAndDetail;
import com.example.demo.model.PhoneProductAndPhoneProductDetail;

@Repository
public interface PhoneProductRepository extends JpaRepository<PhoneProduct, Long>{

	
	@Query(value = "SELECT * FROM phone_product WHERE name_phone_product = :namePhoneProduct AND id_trang_thai=:idTrangThai AND image = :image AND price= :price AND id_hang_dien_thoai = :idHangDienThoai ",nativeQuery = true)
	PhoneProduct findPhoneProduct(@Param("namePhoneProduct") String namePhoneProduct,@Param("idTrangThai") Long idTrangThai,@Param("image") String image,@Param("price") Long price,@Param("idHangDienThoai") Long idHangDienThoai);
	

	@Query(value = "SELECT * FROM phone_product WHERE id_phone_product =:idPhoneProduct",nativeQuery = true)
	PhoneProduct findPhoneProductById(@Param("idPhoneProduct") Long idPhoneProduct);
	
	@Query(value="SELECT pd.id_phone_product,pd.name_phone_product,pd.price,pdt.quantity,tt.name_trang_thai,ttp.name_tinh_trang,pd.image FROM phone_product pd INNER JOIN phoneproduct_detail pdt ON pd.id_phone_product = pdt.id_phone_product "
			+ "INNER JOIN trangthai_phoneproduct tt ON pd.id_trang_thai = tt.id_trang_thai INNER JOIN tinh_trang_phone_product ttp ON pdt.id_tinh_trang_phone_product = ttp.id_tinh_trang_phone_product WHERE pd.trang_thai = 1",nativeQuery = true)
	List<PhoneProductAndDetail> displayPhoneProduct();
	
	
	@Query(value="SELECT pd.id_phone_product,pd.name_phone_product,pd.price,pdt.quantity,tt.name_trang_thai,ttp.name_tinh_trang,pd.image FROM phone_product pd INNER JOIN phoneproduct_detail pdt ON pd.id_phone_product = pdt.id_phone_product "
			+ "INNER JOIN trangthai_phoneproduct tt ON pd.id_trang_thai = tt.id_trang_thai INNER JOIN tinh_trang_phone_product ttp ON pdt.id_tinh_trang_phone_product = ttp.id_tinh_trang_phone_product WHERE pd.trang_thai = 0",nativeQuery = true)
	List<PhoneProductAndDetail> displayListLockedPhoneProduct();
	
	@Query(value = "SELECT pd.id_phone_product,pd.name_phone_product,pd.trang_thai,tt.name_trang_thai,pd.image,pd.price,hd.name_hang_dien_thoai,c.name_color,pdt.quantity,r.name_ram,s.technology_screen,s.do_phan_giai,b.name_battery,m.name_memory,ttp.name_tinh_trang,cm.name_camera FROM `phone_product` pd "
			+ "INNER JOIN trangthai_phoneproduct tt ON pd.id_trang_thai = tt.id_trang_thai INNER JOIN hang_dienthoai hd ON pd.id_hang_dien_thoai = hd.id_hang_dien_thoai INNER JOIN phoneproduct_detail pdt ON pd.id_phone_product_detail = pdt.id_phone_product_detail INNER JOIN color c ON pdt.id_color = c.id_color "
			+ "INNER JOIN battery b ON pdt.id_battery = b.id_battery INNER JOIN memory m ON pdt.id_memory = m.id_memory INNER JOIN tinh_trang_phone_product ttp ON pdt.id_tinh_trang_phone_product = ttp.id_tinh_trang_phone_product INNER JOIN ram r ON pdt.id_ram = r.id_ram INNER JOIN screen s ON pdt.id_screen = s.id_screen "
			+ "INNER JOIN camera cm ON pdt.id_camera = cm.id_camera WHERE pd.id_phone_product = :idPhoneProduct",nativeQuery = true)
	PhoneProductAndPhoneProductDetail displayAllInformationPhoneProduct(@Param("idPhoneProduct") Long idPhoneProduct);
	
	
	@Query(value = "SELECT p.id_phone_product,p.name_phone_product,p.price,p.image FROM `phone_product` p where p.trang_thai = 1 LIMIT 9",nativeQuery = true)
	List<DisplayProductCustomer> displayProductCustomer();
	
	@Query(value = "SELECT p.id_phone_product,p.name_phone_product,p.price,p.image FROM `phone_product` p where p.trang_thai = 1 ",nativeQuery = true)
	List<DisplayProductCustomer> displayAllProductCustomer();
	
	@Query(value = "SELECT p.id_phone_product,p.name_phone_product,p.price,ttp.name_trang_thai,hd.name_hang_dien_thoai FROM `phone_product` p INNER JOIN trangthai_phoneproduct ttp ON p.id_trang_thai = ttp.id_trang_thai INNER JOIN hang_dienthoai hd ON p.id_hang_dien_thoai = hd.id_hang_dien_thoai WHERE p.id_phone_product = :idPhoneProduct ",nativeQuery = true)
	InformationPhoneProductDetailCustomer displayInformationPhoneProductDetailCustomer(@Param("idPhoneProduct") Long idPhoneProduct);
	
	@Query(value = "SELECT p.id_phone_product,p.name_phone_product,p.price,p.image FROM `phone_product` p WHERE p.id_phone_product NOT IN (SELECT a.id_phone_product FROM phone_product a WHERE id_phone_product = :idPhoneProduct) ORDER BY RAND() LIMIT 4",nativeQuery = true)
	List<DisplayProductCustomer> displayProductRandomCustomer(@Param("idPhoneProduct") Long idPhoneProduct);
	
	@Query(value = "SELECT c.name_color,pdt.quantity,r.name_ram,s.technology_screen,s.do_phan_giai,b.name_battery,m.name_memory,pdt.date_input_product,ttp.name_tinh_trang,cm.name_camera FROM `phone_product` p INNER JOIN phoneproduct_detail pdt ON p.id_phone_product_detail = pdt.id_phone_product_detail INNER JOIN color c ON pdt.id_color = c.id_color INNER JOIN battery b ON pdt.id_battery = b.id_battery INNER JOIN memory m ON pdt.id_memory = m.id_memory INNER JOIN tinh_trang_phone_product ttp ON pdt.id_tinh_trang_phone_product = ttp.id_tinh_trang_phone_product INNER JOIN ram r ON pdt.id_ram = r.id_ram INNER JOIN screen s ON pdt.id_screen = s.id_screen INNER JOIN camera cm ON pdt.id_camera = cm.id_camera WHERE p.id_phone_product = :idPhoneProduct",nativeQuery = true)
	InformationConfigurePhoneProductCustomer displayInformationConfigurePhoneProductCustomer(@Param("idPhoneProduct") Long idPhoneProduct);
	
	
	@Query(value = "SELECT p.id_phone_product,p.name_phone_product,p.price,p.image FROM `phone_product` p INNER JOIN hang_dienthoai h ON p.id_hang_dien_thoai = h.id_hang_dien_thoai where p.trang_thai = 1 AND h.id_hang_dien_thoai = :idHangDienThoai",nativeQuery = true)
	List<DisplayProductCustomer> displayPhoneProductByHangDienThoai(@Param("idHangDienThoai") Long idHangDienThoai);
	
	
	@Query(value = "SELECT pd.id_phone_product,pd.name_phone_product,pd.price,pdt.id_phone_product_detail,pdt.quantity,tt.*,hd.*,c.*,b.*,m.*,ttp.*,r.*,s.*,cm.* FROM `phone_product` pd INNER JOIN trangthai_phoneproduct tt ON pd.id_trang_thai = tt.id_trang_thai INNER JOIN hang_dienthoai hd ON pd.id_hang_dien_thoai = hd.id_hang_dien_thoai INNER JOIN phoneproduct_detail pdt ON pd.id_phone_product_detail = pdt.id_phone_product_detail INNER JOIN color c ON pdt.id_color = c.id_color INNER JOIN battery b ON pdt.id_battery = b.id_battery INNER JOIN memory m ON pdt.id_memory = m.id_memory INNER JOIN tinh_trang_phone_product ttp ON pdt.id_tinh_trang_phone_product = ttp.id_tinh_trang_phone_product INNER JOIN ram r ON pdt.id_ram = r.id_ram INNER JOIN screen s ON pdt.id_screen = s.id_screen INNER JOIN camera cm ON pdt.id_camera = cm.id_camera WHERE pd.id_phone_product = :idPhoneProduct",nativeQuery = true)
	InformationUpdatePhoneProduct informationToUpdatePhoneProduct(@Param("idPhoneProduct") Long idPhoneProduct);
	
	
	
	/*
	 * Statistic 
	 */
	
	@Query(value = "SELECT YEAR(r.date) AS time,SUM(rd.total_price) AS totalMoney,SUM(rd.quantity) AS quantity FROM `receipt_detail` rd INNER JOIN receipt r ON rd.id_receipt = r.id_receipt WHERE r.id_trang_thai = 2 GROUP BY (time) ORDER BY (time) ASC",nativeQuery = true)
	List<StatisticBudget> statisticBudget();
	
	@Query(value = "SELECT month(r.date) AS time,SUM(rd.total_price) AS totalMoney,SUM(rd.quantity) AS quantity FROM `receipt_detail` rd INNER JOIN receipt r ON rd.id_receipt = r.id_receipt WHERE r.id_trang_thai = 2 AND year(r.date) = :idYear GROUP BY (time) ORDER BY (time) ASC",nativeQuery = true)
	List<StatisticBudget> statisticBudgetMonth(@Param("idYear") Long year);
	
	@Query(value = "select pd.id_phone_product,pd.name_phone_product,rd.quantity,rd.total_price,c.name_color,m.name_memory,ra.name_ram,tt.name_tinh_trang,pd.image from receipt_detail rd INNER JOIN receipt r ON rd.id_receipt = r.id_receipt INNER JOIN phone_product pd ON rd.id_phone_product = pd.id_phone_product INNER JOIN phoneproduct_detail pdt ON pd.id_phone_product = pdt.id_phone_product INNER JOIN color c ON pdt.id_color = c.id_color INNER JOIN memory m ON pdt.id_memory = m.id_memory INNER JOIN tinh_trang_phone_product tt ON pdt.id_tinh_trang_phone_product = tt.id_tinh_trang_phone_product INNER JOIN ram ra ON pdt.id_ram = ra.id_ram WHERE r.id_trang_thai = 2 AND year(r.date) = :idYear AND month(r.date) = :idMonth",nativeQuery = true)
	List<informationPhoneAndReceiptDetailFromIdReceipt> displayListPhoneProductByYearAndMonth(@Param("idYear") Long year,@Param("idMonth") Long month);
	
}
