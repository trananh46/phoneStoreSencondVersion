package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.TinhTrangPhoneProduct;
import com.example.demo.repository.TinhTrangSanPhamRepository;

@Service
public class TinhTrangSanPhamService {
	@Autowired
	private TinhTrangSanPhamRepository tinhTrangSanPhamRepository;
	
	public List<TinhTrangPhoneProduct> displayTinhTrangSanPham() {
		List<TinhTrangPhoneProduct> l = tinhTrangSanPhamRepository.displayTinhTrangSanPham();
		return l;
	}
	
	public TinhTrangPhoneProduct findTinhTrangPhoneProductById(Long idTinhTrangProduct) {
		TinhTrangPhoneProduct t = tinhTrangSanPhamRepository.findTinhTrangPhoneProductById(idTinhTrangProduct);
		return t;
	}
	
	public List<TinhTrangPhoneProduct> displayListTinhTrangSanPhamTopUpdatePhoneProduct(Long idPhoneProduct){
		 List<TinhTrangPhoneProduct> listTinhTrangPhoneProduct = tinhTrangSanPhamRepository.displayListTinhTrangSanPhamTopUpdatePhoneProduct(idPhoneProduct);
		 return listTinhTrangPhoneProduct;
	}
}
