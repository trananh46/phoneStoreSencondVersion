package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.StatusReceipt;

@Repository
public interface StatusReceiptRepository extends JpaRepository<StatusReceipt, Long>{

	@Query(value = "SELECT * FROM trangthai_hoadon",nativeQuery = true)
	List<StatusReceipt> displayListStatusReceipt();
	
	@Query(value = "SELECT * FROM trangthai_hoadon WHERE id_trang_thai = :idStatusReceipt",nativeQuery = true)
	StatusReceipt findStatusReceiptById(@RequestParam("idStatusReceipt") Long idStatusReceipt);
}
