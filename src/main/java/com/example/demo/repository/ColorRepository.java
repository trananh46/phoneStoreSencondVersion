package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color,Long>{

	
	@Query(value = "SELECT * FROM color",nativeQuery = true)
	List<Color> displayColor(); 
	
	@Query(value = "SELECT * FROM color WHERE id_color=:idColor",nativeQuery = true)
	Color findColorById(@Param("idColor") Long idColor);
	
	@Query(value = "UPDATE color SET name_color=:nameColor WHERE id_color =:idColor",nativeQuery = true)
	@Modifying
	@Transactional
	void updateColor(@Param("idColor") Long idColor,@Param("nameColor") String nameColor);
	
	@Query(value = "DELETE FROM color WHERE id_color =:idColor",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteColor(@Param("idColor") Long idColor);
	
	@Query(value = "SELECT * from color EXCEPT (SELECT c.* FROM phone_product p INNER JOIN phoneproduct_detail pd ON p.id_phone_product_detail = pd.id_phone_product_detail INNER JOIN color c ON pd.id_color = c.id_color WHERE p.id_phone_product = :idPhoneProduct)",nativeQuery = true)
	List<Color> listColorToUpdatePhoneProduct(@Param("idPhoneProduct") Long idPhoneProduct);
}
