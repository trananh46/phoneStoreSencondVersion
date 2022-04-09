package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.demo.model.Battery;
import com.example.demo.model.Camera;
import com.example.demo.model.Color;
import com.example.demo.model.Memory;
import com.example.demo.model.PhoneProduct;
import com.example.demo.model.PhoneProductDetail;
import com.example.demo.model.Ram;
import com.example.demo.model.Screen;
import com.example.demo.model.TinhTrangPhoneProduct;
import com.example.demo.repository.PhoneProductDetailRepository;

@Service
public class PhoneProductDetailService {

	@Autowired
	private PhoneProductDetailRepository phoneProductDetailRepository;
	
	@Autowired
	private ColorService colorService;
	
	@Autowired
	private RamService ramService;
	
	@Autowired
	private ScreenService screenService;
	
	@Autowired
	private MemoryService memoryService;
	
	@Autowired
	private TinhTrangSanPhamService tinhTrangSanPhamService;
	
	@Autowired
	private CameraService cameraService;
	
	@Autowired
	private BatteryService batteryService;
	
	@Autowired
	private PhoneProductService phoneProductService;
	
	public void insertPhoneProductDetail(Long idColor,Long quantity,Long idRam,Long idScreen,Long idBattery,Long idMemory,String date,Long idTinhTrangPhoneProduct,Long idCamera,Long idPhoneProduct ) {
		PhoneProductDetail p = new PhoneProductDetail();
		
		Color c = colorService.findColorById(idColor);	
		p.setColor(c);
		
		p.setQuantity(quantity);
		
		Ram r = ramService.findRamById(idRam);
		p.setRam(r);
		
		Screen s = screenService.findScreenById(idScreen);
		p.setScreen(s);
		
		Battery b = batteryService.findBatteryById(idBattery);
		p.setBattery(b);
			
		Memory m = memoryService.findMemoryById(idMemory);
		p.setMemory(m);
		
		p.setDateInputProduct(date);
		
		TinhTrangPhoneProduct t = tinhTrangSanPhamService.findTinhTrangPhoneProductById(idTinhTrangPhoneProduct);
		p.setTinhTrangPhoneProduct(t);
		
		Camera ca = cameraService.findCameraById(idCamera);
		p.setCamera(ca);
		
		
		PhoneProduct pd = phoneProductService.findPhoneProductById(idPhoneProduct);
		p.setPhoneProduct2(pd);
		
		
		phoneProductDetailRepository.save(p);
		
	}
	
	
	public PhoneProductDetail findPhoneProductDetailById(Long idPhoneProductDetail) {
		PhoneProductDetail p = phoneProductDetailRepository.findPhoneProductDetailById(idPhoneProductDetail);
		return p;
	}
	
	
	public PhoneProductDetail findPhoneProductDetailByIdPhoneProduct(Long idPhoneProduct) {
		PhoneProductDetail p = phoneProductDetailRepository.findPhoneProductDetailByIdPhoneProduct(idPhoneProduct);
		return p;
	}
	
	public void updatePhoneProductDetail(PhoneProductDetail p) {
		PhoneProductDetail p1 = phoneProductDetailRepository.findPhoneProductDetailById(p.getIdPhoneProductDetail());
		p1.setColor(p.getColor());
		p1.setQuantity(p.getQuantity());
		p1.setRam(p.getRam());
		p1.setScreen(p.getScreen());
		p1.setBattery(p.getBattery());
		p1.setMemory(p.getMemory());
		p1.setTinhTrangPhoneProduct(p.getTinhTrangPhoneProduct());
		p1.setCamera(p.getCamera());
		phoneProductDetailRepository.save(p1);
	}
}
