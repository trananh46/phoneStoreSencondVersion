package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.Memory;
import com.example.demo.repository.MemoryRepository;

@Service
public class MemoryService {

	@Autowired
	private MemoryRepository memoryRepository;
	
	public List<Memory> displayMemory(){
		List<Memory> listMemories = memoryRepository.displayMemory();
		return listMemories;
	}
	
	
	public Memory findMemoryById(Long idMemory) {
		Memory m = memoryRepository.findMemoryById(idMemory);
		return m;
	}
	
	public List<Memory> displayListMemoryToUpdate(Long idPhoneProduct){
		List<Memory> listMemory = memoryRepository.displayListMemoryToUpdate(idPhoneProduct);
		return listMemory;
	}
}
