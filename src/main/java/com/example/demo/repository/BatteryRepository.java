package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Battery;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long>{

	@Query(value = "SELECT * FROM battery",nativeQuery = true)
	List<Battery> displayBattery();
	
	
	@Query(value = "SELECT * FROM battery WHERE id_battery=:idBattery",nativeQuery = true)
	Battery findBatteryById(@Param("idBattery") Long idBattery);
	
	@Query(value = "UPDATE battery SET name_battery=:nameBattery WHERE id_battery=:idBattery",nativeQuery = true)
	@Modifying
	@Transactional
	void updateBattery(@Param("idBattery") Long idBattery,@Param("nameBattery") String nameBatterty);
	
	@Query(value="DELETE FROM battery WHERE id_battery=:idBattery",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteBatteryById(@Param("idBattery") Long idBattery);
	
	@Query(value = "SELECT * from battery EXCEPT (SELECT b.* FROM phone_product p INNER JOIN phoneproduct_detail pd ON p.id_phone_product_detail = pd.id_phone_product_detail INNER JOIN battery b ON pd.id_battery = b.id_battery WHERE p.id_phone_product = :idPhoneProduct)",nativeQuery = true)
	List<Battery> displayListBatteryToUpdate(@Param("idPhoneProduct") Long idPhoneProduct);
}
