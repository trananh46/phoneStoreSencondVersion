package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.Camera;
import com.example.demo.repository.CameraRepository;

@Service
public class CameraService {

	@Autowired
	private CameraRepository cameraRepository;
	
	public List<Camera> displayListCamera(){
		List<Camera> l = cameraRepository.displayListCamera();
		return l;
	}
	
	
	public Camera findCameraById(Long idCamera) {
		Camera c = cameraRepository.findCameraById(idCamera);
		return c;
	}
	
	public List<Camera> displayListCameraToUpdate( Long idPhoneProduct){
		List<Camera> listCamera = cameraRepository.displayListCameraToUpdate(idPhoneProduct);
		return listCamera;
	}
}
