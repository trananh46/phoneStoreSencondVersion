package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.TrangThaiPhoneProduct;
import com.example.demo.repository.TrangThaiPhoneProductRepository;

@Service
public class TrangThaiPhoneProductService {

	@Autowired
	private TrangThaiPhoneProductRepository trangThaiPhoneProductRepository;
	
	
	public List<TrangThaiPhoneProduct> displayTrangThaiSanPham(){
		List<TrangThaiPhoneProduct> listTrangThaiSanPham = trangThaiPhoneProductRepository.displayTrangThaiSanPham();
		return listTrangThaiSanPham;
	}
	
	public TrangThaiPhoneProduct findTrangThaiPhoneProductById(Long idTrangThai) {
		TrangThaiPhoneProduct t = trangThaiPhoneProductRepository.findTrangThaiPhoneProductById(idTrangThai);
		return t;
	}
	
	public List<TrangThaiPhoneProduct> displayTrangThaiSanPhamToUpdatePhoneProduct(@Param("idPhoneProduct") Long idPhoneProduct){
		List<TrangThaiPhoneProduct> listTrangThaiPhone = trangThaiPhoneProductRepository.displayTrangThaiSanPhamToUpdatePhoneProduct(idPhoneProduct);
		return listTrangThaiPhone;
	}
}
