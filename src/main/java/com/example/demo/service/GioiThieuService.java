package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GioiThieu;
import com.example.demo.repository.GioiThieuRepository;

@Service
public class GioiThieuService {

	@Autowired
	private GioiThieuRepository gioiThieuRepository;
	
	
	public void insertGioiThieu(String title,String post,String loaiDienThoai,String image) {
		GioiThieu g = new GioiThieu();
		g.setTitle(title);
		g.setPost(post);
		g.setLoaiDienThoai(loaiDienThoai);
		g.setImage(image);
		gioiThieuRepository.save(g);
	}
	
	public List<GioiThieu> displayGioiThieu(){
		List<GioiThieu> l = gioiThieuRepository.displayGioiThieu();
		return l;
	}
	
	public GioiThieu findGioiThieuById(Long idGioiThieu) {
		GioiThieu g = gioiThieuRepository.findGioiThieuById(idGioiThieu);
		return g;
	}
	
	
	public void updateGioiThieu(String title,String post,String loaiDienThoai,Long idGioiThieu) {
		gioiThieuRepository.updateGioiThieu(title, post, loaiDienThoai, idGioiThieu);
	}
	
	public void deleteGioiThieu(Long idGioiThieu) {
		gioiThieuRepository.deleteGioiThieu(idGioiThieu);
	}
	
	
	public List<GioiThieu> displayGioiThieuByTitleAndLoaiDienThoai(String hangDienThoai){
		List<GioiThieu> l = gioiThieuRepository.displayGioiThieuByTitleAndLoaiDienThoai(hangDienThoai);
		return l;
	}
	
	public List<GioiThieu> displayGioiThieuPhoneProductDetailCustomer(Long idPhoneProduct){
		List<GioiThieu> l = gioiThieuRepository.displayGioiThieuPhoneProductDetailCustomer(idPhoneProduct);
		return l;
	}
	
	public List<GioiThieu> displayGioiThieuPhoneInCustomerPage(){
		List<GioiThieu> l = gioiThieuRepository.displayGioiThieuPhoneInCustomerPage();
		return l;
	}
}
