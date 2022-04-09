package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.Color;
import com.example.demo.repository.ColorRepository;

@Service
public class ColorService {

	@Autowired
	private ColorRepository colorRepository;
	
	public List<Color> displayColor(){
		List<Color> listColor = colorRepository.displayColor();
		return listColor;
	}
	
	public void insertColor(String nameColor) {
		Color c = new Color();
		c.setNameColor(nameColor);
		colorRepository.save(c);
	}
	
	public Color findColorById(Long idColor) {
		Color c = colorRepository.findColorById(idColor);
		return c;
	}
	
	
	public void updateColor(Long idColor,String nameColor) {
		colorRepository.updateColor(idColor, nameColor);
	}
	
	public void deleteColor(Long idColor) {
		colorRepository.deleteColor(idColor);
	}
	
	public List<Color> listColorToUpdatePhoneProduct(Long idPhoneProduct){
		List<Color> listColor = colorRepository.listColorToUpdatePhoneProduct(idPhoneProduct);
		return listColor;
	}
}
