package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Camera;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long>{

	@Query(value = "SELECT * FROM camera",nativeQuery = true)
	List<Camera> displayListCamera(); 
	
	@Query(value = "SELECT * FROM camera WHERE id_camera =:idCamera",nativeQuery = true)
	Camera findCameraById(@Param("idCamera") Long idCamera);
	
	@Query(value = "SELECT * from camera EXCEPT (SELECT c.* FROM phone_product p INNER JOIN phoneproduct_detail pd ON p.id_phone_product_detail = pd.id_phone_product_detail INNER JOIN camera c ON pd.id_camera = c.id_camera WHERE p.id_phone_product = :idPhoneProduct)",nativeQuery = true)
	List<Camera> displayListCameraToUpdate(@Param("idPhoneProduct") Long idPhoneProduct);
}
