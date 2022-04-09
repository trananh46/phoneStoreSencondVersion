package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.Screen;
import com.example.demo.repository.ScreenRepository;

@Service
public class ScreenService {

	@Autowired
	private ScreenRepository screenRepository;
	
	public List<Screen> displayScreen(){	
		List<Screen> listScreen = screenRepository.displayScreen();
		return listScreen;
	}

	
	public void insertScreen(String technologyScreen,String doPhanGiai) {
		Screen s = new Screen();
		s.setTechnologyScreen(technologyScreen);
		s.setDoPhanGiai(doPhanGiai);
		screenRepository.save(s);
	}
	
	
	public Screen findScreenById(Long idScreen) {
		Screen s = screenRepository.findScreenById(idScreen);
		return s;
	}
	
	public List<Screen> displayListScreenToUpdatePhoneProduct(Long idPhoneProduct){
		List<Screen> listScreen = screenRepository.displayListScreenToUpdatePhoneProduct(idPhoneProduct);
		return listScreen;
	}
}
