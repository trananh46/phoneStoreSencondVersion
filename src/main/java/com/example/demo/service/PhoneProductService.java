package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.InformationUpdatePhoneProduct;
import com.example.demo.model.DisplayProductCustomer;
import com.example.demo.model.HangDienThoai;
import com.example.demo.model.ImageProduct;
import com.example.demo.model.PhoneProduct;
import com.example.demo.model.PhoneProductAndDetail;
import com.example.demo.model.PhoneProductAndPhoneProductDetail;
import com.example.demo.model.PhoneProductDetail;
import com.example.demo.model.TrangThaiPhoneProduct;
import com.example.demo.repository.PhoneProductRepository;

@Service
public class PhoneProductService {

	@Autowired
	private PhoneProductRepository phoneProductRepository;
	
	@Autowired
	private TrangThaiPhoneProductService trangThaiPhoneProductService;
	
	@Autowired
	private PhoneProductDetailService phoneProductDetailService;
	
	@Autowired
	private HangDienThoaiService hangDienThoaiService;
	
	
	public void insertPhoneProduct(String namePhoneProduct,Long idTrangThai,String image,Long price,Long idHangDienThoai) {
		
		PhoneProduct p = new PhoneProduct();
		p.setNamePhoneProduct(namePhoneProduct);
		
		TrangThaiPhoneProduct t = trangThaiPhoneProductService.findTrangThaiPhoneProductById(idTrangThai);
		p.setTrangThaiPhoneProduct(t);
		
		p.setImage(image);
		
		p.setPrice(price);
		
		HangDienThoai h = hangDienThoaiService.findHangDienThoaiById(idHangDienThoai);
		p.setHangDienThoai(h);
		
		//opened status is 1
		long status = 1;
		p.setStatus(status);
		phoneProductRepository.save(p);
			
	}
	
	
	public PhoneProduct findPhoneProduct(String namePhoneProduct,Long idTrangThai,String image,Long price,Long idHangDienThoai) {
		PhoneProduct p = phoneProductRepository.findPhoneProduct(namePhoneProduct, idTrangThai, image, price, idHangDienThoai);
		return p;
	}
	
	
	public PhoneProduct findPhoneProductById(Long idPhoneProduct) {
		PhoneProduct pd = phoneProductRepository.findPhoneProductById(idPhoneProduct);
		return pd;
	}
	
	
	public void updatePhoneProduct(@Lazy Long idPhoneProduct,PhoneProductDetail p) {
		PhoneProduct pd = phoneProductRepository.findPhoneProductById(idPhoneProduct);
		pd.setPhoneProductDetail(p);
		phoneProductRepository.save(pd);
	}
	
	
	public List<PhoneProductAndDetail> displayPhoneProduct(){
		List<PhoneProductAndDetail> listPhoneProduct = phoneProductRepository.displayPhoneProduct();
		return listPhoneProduct;
	}
	
	public List<PhoneProductAndDetail> displayListLockedPhoneProduct(){
		List<PhoneProductAndDetail> listPhoneProduct = phoneProductRepository.displayListLockedPhoneProduct();
		return listPhoneProduct;
	}
	
	public PhoneProductAndPhoneProductDetail displayAllInformationPhoneProduct(Long idPhoneProduct){
		PhoneProductAndPhoneProductDetail p = phoneProductRepository.displayAllInformationPhoneProduct(idPhoneProduct);
		return p;
	}
	
	
	public List<DisplayProductCustomer> displayProductCustomer(){
		List<DisplayProductCustomer> l = phoneProductRepository.displayProductCustomer();
		return l;
	}
	
	
	public List<DisplayProductCustomer> displayAllProductCustomer(){
		List<DisplayProductCustomer> l = phoneProductRepository.displayAllProductCustomer();
		return l;
	}
	
	public List<DisplayProductCustomer> displayProductRandomCustomer(Long idPhoneProduct){
		List<DisplayProductCustomer> l = phoneProductRepository.displayProductRandomCustomer(idPhoneProduct);
		return l;
	}
	
	
	public List<DisplayProductCustomer> displayPhoneByHangDienThoai(Long idHangDienThoai){
		List<DisplayProductCustomer> l = phoneProductRepository.displayPhoneProductByHangDienThoai(idHangDienThoai);
		return l;
	}
	
	public InformationUpdatePhoneProduct informationToUpdatePhoneProduct( Long idPhoneProduct) {
		InformationUpdatePhoneProduct i = phoneProductRepository.informationToUpdatePhoneProduct(idPhoneProduct);
		return i;
	}
	
	public void updatePhoneProduct(@Lazy PhoneProduct p) {
		String namePhone = p.getNamePhoneProduct().toUpperCase();
		PhoneProduct p1 = phoneProductRepository.findPhoneProductById(p.getIdPhoneProduct());
		p1.setNamePhoneProduct(namePhone);
		p1.setTrangThaiPhoneProduct(p.getTrangThaiPhoneProduct());
		p1.setPrice(p.getPrice());
		p1.setHangDienThoai(p.getHangDienThoai());
		phoneProductRepository.save(p1);
	}
	
	public void lockPhoneProduct(Long idPhoneProduct) {
		PhoneProduct p = phoneProductRepository.findPhoneProductById(idPhoneProduct);
		//locked status is 0
		long status = 0;
		p.setStatus(status);
		phoneProductRepository.save(p);
	}
	
	public void openPhoneProduct(Long idPhoneProduct) {
		PhoneProduct p = phoneProductRepository.findPhoneProductById(idPhoneProduct);
		//opened status is 1
		long status = 1;
		p.setStatus(status);
		phoneProductRepository.save(p);
	}
}
