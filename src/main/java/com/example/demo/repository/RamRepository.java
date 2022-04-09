package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ram;

@Repository
public interface RamRepository extends JpaRepository<Ram, Long>{

	
	@Query(value = "SELECT * FROM ram",nativeQuery = true)
	List<Ram> displayRam();
	
	@Query(value = "SELECT * FROM ram WHERE id_ram=:idRam",nativeQuery = true)
	Ram findRamById(@Param("idRam") Long idRam);
	
	@Query(value = "SELECT * from ram EXCEPT (SELECT r.* FROM phone_product p INNER JOIN phoneproduct_detail pd ON p.id_phone_product_detail = pd.id_phone_product_detail INNER JOIN ram r ON pd.id_ram = r.id_ram WHERE p.id_phone_product = :idPhoneProduct)",nativeQuery = true)
	List<Ram> listRamToUpdatePhoneProduct(@Param("idPhoneProduct") Long idPhoneProduct);
}
