package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ram;
import com.example.demo.repository.RamRepository;

@Service
public class RamService {

	@Autowired
	private RamRepository ramRepository;
	
	public List<Ram> displayRam(){
		List<Ram> listRams = ramRepository.displayRam();
		return listRams;
	}
	
	public Ram findRamById(Long idRam) {
		Ram r = ramRepository.findRamById(idRam);
		return r;
	}
	
	public List<Ram> listRamToUpdatePhoneProduct( Long idPhoneProduct){
		List<Ram> listRam = ramRepository.listRamToUpdatePhoneProduct(idPhoneProduct);
		return listRam;
	}
}
