package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.Battery;
import com.example.demo.repository.BatteryRepository;

@Service
public class BatteryService {

	@Autowired
	private BatteryRepository batteryRepository;
	
	public List<Battery> displayBattery(){
		List<Battery> listBattery = batteryRepository.displayBattery();
		return listBattery;
	}
	
	public void insertBattery(String nameBattery) {
		Battery b = new Battery();
		b.setNameBattery(nameBattery);
		batteryRepository.save(b);
	}
	
	public Battery findBatteryById(Long idBattery){
		Battery b = batteryRepository.findBatteryById(idBattery);
		return b;
	}
	
	public void updateBattery(Long idBattery,String nameBatter) {
		batteryRepository.updateBattery(idBattery, nameBatter);
	}
	
	public void deleteBattery(Long idBattery) {
		batteryRepository.deleteBatteryById(idBattery);
	}
	
	public List<Battery> displayListBatteryToUpdate( Long idPhoneProduct){
		List<Battery> listBattery = batteryRepository.displayListBatteryToUpdate(idPhoneProduct);
		return listBattery;
	}
}
