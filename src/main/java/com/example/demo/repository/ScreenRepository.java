package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long>{
	
	@Query(value="SELECT * FROM screen",nativeQuery = true)
	List<Screen> displayScreen();
	
	@Query(value = "SELECT * FROM screen WHERE id_screen=:idScreen",nativeQuery = true)
	Screen findScreenById(@Param("idScreen") Long idScreen);
	
	@Query(value = "SELECT * from screen EXCEPT (SELECT s.* FROM phone_product p INNER JOIN phoneproduct_detail pd ON p.id_phone_product_detail = pd.id_phone_product_detail INNER JOIN screen s ON pd.id_screen = s.id_screen WHERE p.id_phone_product = :idPhoneProduct)",nativeQuery = true)
	List<Screen> displayListScreenToUpdatePhoneProduct(@Param("idPhoneProduct") Long idPhoneProduct);
	
	
}
