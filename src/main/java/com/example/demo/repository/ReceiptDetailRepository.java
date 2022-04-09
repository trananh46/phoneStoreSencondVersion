package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.informationPhoneAndReceiptDetailFromIdReceipt;
import com.example.demo.model.ReceiptDetail;
import com.example.demo.model.TotalPriceProjectionOfReceipt;

@Repository
public interface ReceiptDetailRepository extends JpaRepository<ReceiptDetail, Long>{

	
	@Query(value = "SELECT r.id_customer,pd.id_phone_product,rd.id_receipt_detail,pd.name_phone_product,pd.image,pd.price,c.name_color,m.name_memory,rd.total_price,rd.quantity FROM `receipt_detail` rd "
			+ "INNER JOIN receipt r ON rd.id_receipt = r.id_receipt INNER JOIN phone_product pd ON rd.id_phone_product = pd.id_phone_product "
			+ "INNER JOIN phoneproduct_detail pdt ON pd.id_phone_product_detail = pdt.id_phone_product_detail INNER JOIN memory m ON pdt.id_memory = m.id_memory "
			+ "INNER JOIN color c ON pdt.id_color = c.id_color WHERE r.date IS NULL AND r.note IS NULL AND r.id_trang_thai IS NULL AND r.id_customer = :idCustomer",nativeQuery = true)
	public List<PhoneProductInCart> displayReceiptDetail(@Param("idCustomer") Long idCustomer);
	
	
	@Query(value = "SELECT * FROM `receipt_detail` WHERE id_receipt_detail = :idReceiptDetail",nativeQuery = true)
	public ReceiptDetail findReceiptDetailById(@Param("idReceiptDetail") Long idReceiptDetail);
	
	@Query(value = "SELECT SUM(rd.total_price) AS totalPriceProjectionReceipt FROM `receipt_detail` rd INNER JOIN receipt r ON rd.id_receipt = r.id_receipt "
			+ "INNER JOIN phone_product pd ON rd.id_phone_product = pd.id_phone_product INNER JOIN phoneproduct_detail pdt ON pd.id_phone_product_detail = pdt.id_phone_product_detail "
			+ "INNER JOIN memory m ON pdt.id_memory = m.id_memory INNER JOIN color c ON pdt.id_color = c.id_color WHERE r.date IS NULL AND r.note IS NULL AND r.id_trang_thai IS NULL AND r.id_customer = :idCustomer",nativeQuery = true)
	public TotalPriceProjectionOfReceipt displayTotalPriceProjectionOfReceipt(@Param("idCustomer") Long idCustomer);
	
	@Query(value = "DELETE FROM receipt_detail WHERE id_receipt_detail = :idReceiptDetail",nativeQuery = true)
	@Modifying
	@Transactional
	public void deleteReceiptDetailById(@Param("idReceiptDetail") Long idReceiptDetail);
	
	@Query(value = "DELETE from receipt_detail WHERE id_receipt = :idReceipt",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteReceiptDetailByIdReceipt(@Param("idReceipt") Long idReceipt);
	
	@Query(value = "select pd.id_phone_product,pd.name_phone_product,rd.quantity,rd.total_price,c.name_color,m.name_memory,ra.name_ram,tt.name_tinh_trang,pd.image from receipt_detail rd INNER JOIN receipt r ON rd.id_receipt = r.id_receipt "
			+ "INNER JOIN phone_product pd ON rd.id_phone_product = pd.id_phone_product INNER JOIN phoneproduct_detail pdt ON pd.id_phone_product = pdt.id_phone_product INNER JOIN color c ON pdt.id_color = c.id_color INNER JOIN memory m ON pdt.id_memory = m.id_memory "
			+ "INNER JOIN tinh_trang_phone_product tt ON pdt.id_tinh_trang_phone_product = tt.id_tinh_trang_phone_product INNER JOIN ram ra ON pdt.id_ram = ra.id_ram WHERE r.id_receipt = :idReceipt",nativeQuery = true)
	List<informationPhoneAndReceiptDetailFromIdReceipt> listReceiptDetailByIdReceipt(@Param("idReceipt") Long idReceipt);
	
	@Query(value = "select pd.id_phone_product,pd.name_phone_product,rd.quantity,rd.total_price,c.name_color,m.name_memory,ra.name_ram,tt.name_tinh_trang,pd.image from receipt_detail rd INNER JOIN receipt r ON rd.id_receipt = r.id_receipt "
			+ "INNER JOIN phone_product pd ON rd.id_phone_product = pd.id_phone_product INNER JOIN phoneproduct_detail pdt ON pd.id_phone_product = pdt.id_phone_product INNER JOIN color c ON pdt.id_color = c.id_color INNER JOIN memory m ON pdt.id_memory = m.id_memory "
			+ "INNER JOIN tinh_trang_phone_product tt ON pdt.id_tinh_trang_phone_product = tt.id_tinh_trang_phone_product INNER JOIN ram ra ON pdt.id_ram = ra.id_ram WHERE r.id_trang_thai = 1 AND r.id_customer = :idCustomer",nativeQuery = true)
	List<informationPhoneAndReceiptDetailFromIdReceipt> listReceiptDetailHasNotApprovedByIdCustomer(@Param("idCustomer") Long idCustomer);
	
	@Query(value = "select pd.id_phone_product,pd.name_phone_product,rd.quantity,rd.total_price,c.name_color,m.name_memory,ra.name_ram,tt.name_tinh_trang,pd.image from receipt_detail rd INNER JOIN receipt r ON rd.id_receipt = r.id_receipt "
			+ "INNER JOIN phone_product pd ON rd.id_phone_product = pd.id_phone_product INNER JOIN phoneproduct_detail pdt ON pd.id_phone_product = pdt.id_phone_product INNER JOIN color c ON pdt.id_color = c.id_color INNER JOIN memory m ON pdt.id_memory = m.id_memory "
			+ "INNER JOIN tinh_trang_phone_product tt ON pdt.id_tinh_trang_phone_product = tt.id_tinh_trang_phone_product INNER JOIN ram ra ON pdt.id_ram = ra.id_ram WHERE r.id_trang_thai = 2 AND r.id_customer = :idCustomer",nativeQuery = true)
	List<informationPhoneAndReceiptDetailFromIdReceipt> listReceiptDetailApprovedByIdCustomer(@Param("idCustomer") Long idCustomer);
}
