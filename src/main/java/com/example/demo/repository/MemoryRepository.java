package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Memory;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long>{

	@Query(value = "SELECT * FROM memory",nativeQuery = true)
	List<Memory> displayMemory();
	
	@Query(value = "SELECT * FROM memory WHERE id_memory=:idMemory",nativeQuery = true)
	Memory findMemoryById(@Param("idMemory") Long idMemory);
	
	@Query(value = "SELECT * from memory EXCEPT (SELECT m.* FROM phone_product p INNER JOIN phoneproduct_detail pd ON p.id_phone_product_detail = pd.id_phone_product_detail INNER JOIN memory m ON pd.id_memory = m.id_memory WHERE p.id_phone_product = :idPhoneProduct)",nativeQuery = true)
	List<Memory> displayListMemoryToUpdate(@Param("idPhoneProduct") Long idPhoneProduct);
	
}
