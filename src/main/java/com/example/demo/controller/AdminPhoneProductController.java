package com.example.demo.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.DTO.InformationUpdatePhoneProduct;
import com.example.demo.model.Battery;
import com.example.demo.model.Camera;
import com.example.demo.model.Color;
import com.example.demo.model.GioiThieuPhoneProductDetail;
import com.example.demo.model.HangDienThoai;
import com.example.demo.model.ImagePhoneProductDetail;
import com.example.demo.model.KhuyenMaiPhoneProductDetail;
import com.example.demo.model.Memory;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.model.PhoneProduct;
import com.example.demo.model.PhoneProductAndDetail;
import com.example.demo.model.PhoneProductAndPhoneProductDetail;
import com.example.demo.model.PhoneProductDetail;
import com.example.demo.model.Ram;
import com.example.demo.model.Screen;
import com.example.demo.model.TinhTrangPhoneProduct;
import com.example.demo.model.TrangThaiPhoneProduct;
import com.example.demo.service.BatteryService;
import com.example.demo.service.CameraService;
import com.example.demo.service.ColorService;
import com.example.demo.service.GioiThieuPhoneService;
import com.example.demo.service.GioiThieuService;
import com.example.demo.service.HangDienThoaiService;
import com.example.demo.service.ImagePhoneProductService;
import com.example.demo.service.KhuyenMaiPhoneService;
import com.example.demo.service.MemoryService;
import com.example.demo.service.PhoneProductDetailService;
import com.example.demo.service.PhoneProductService;
import com.example.demo.service.RamService;
import com.example.demo.service.ScreenService;
import com.example.demo.service.StaffService;
import com.example.demo.service.TinhTrangSanPhamService;
import com.example.demo.service.TrangThaiPhoneProductService;

@Controller
@RequestMapping("/admin_phone")
public class AdminPhoneProductController {
	
	@Autowired
	private StaffService staffService;
	
	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	
	@Autowired
	private TrangThaiPhoneProductService trangThaiPhoneProductService;
	
	@Autowired
	private HangDienThoaiService hangDienThoaiService;
	
	@Autowired
	private ColorService colorService;
	
	@Autowired
	private RamService ramService;
	
	@Autowired
	private ScreenService screenService;
	
	@Autowired
	private BatteryService batteryService;
	
	@Autowired
	private MemoryService memoryService;
	
	@Autowired
	private TinhTrangSanPhamService tinhTrangSanPhamService;
	
	@Autowired
	private CameraService cameraService;
	
	@Autowired
	private PhoneProductDetailService phoneProductDetailService;
	
	@Autowired
	private PhoneProductService phoneProductService;
	
	@Autowired
	private ImagePhoneProductService imagePhoneProductService;
	
	@Autowired
	private KhuyenMaiPhoneService khuyenMaiPhoneService;
	
	@Autowired
	private GioiThieuService gioiThieuService;
	
	
	@Autowired
	private GioiThieuPhoneService gioiThieuPhoneService;
	
	@GetMapping("/insert_phone_product")
	public String insertPhoneProduct(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		
		List<TrangThaiPhoneProduct>  listTrangThai = trangThaiPhoneProductService.displayTrangThaiSanPham();
		model.addAttribute("listTrangThaiSanPham", listTrangThai);
		
		List<HangDienThoai> listHangDienThoai = hangDienThoaiService.displayHangDienThoai();
		model.addAttribute("listHangDienThoai", listHangDienThoai);
		
		
		List<Color> listColor = colorService.displayColor();
		model.addAttribute("listColor", listColor);
		
		
		List<Ram> listRam = ramService.displayRam();
		model.addAttribute("listRam", listRam);
		
		
		List<Screen> listScreen = screenService.displayScreen();
		model.addAttribute("listScreen", listScreen);
		
		
		List<Battery> listBattery = batteryService.displayBattery();
		model.addAttribute("listBattery", listBattery);
		
		List<Memory> listMemory = memoryService.displayMemory();
		model.addAttribute("listMemory", listMemory);
		
		
		List<TinhTrangPhoneProduct> listTinhTrangPhoneProduct = tinhTrangSanPhamService.displayTinhTrangSanPham();
		model.addAttribute("listTinhTrangPhoneProduct", listTinhTrangPhoneProduct);
		
		List<Camera> listCamera = cameraService.displayListCamera();
		model.addAttribute("listCamera", listCamera);
		
		return "admin/formInsertPhoneProduct";
	}
	
	
	
    @PostMapping("/insert_phone_product_process")
    public String insertHangDienThoaiProcess(@RequestParam("name_phone_product") String namePhoneProduct,@RequestParam("id_trang_thai") Long idTrangThai,
    		@RequestParam MultipartFile image,@RequestParam("price") Long price,
    		@RequestParam("id_hang_dien_thoai") Long idHangDienThoai,@RequestParam("id_color") Long idColor,
    		@RequestParam("quantity") Long quantity,@RequestParam("id_ram") Long idRam,
    		@RequestParam("id_screen") Long idScreen,@RequestParam("id_battery") Long idBattery,
    		@RequestParam("id_memory") Long idMemory,@RequestParam("date_input_product") String date,
    		@RequestParam("id_tinh_trang_phone_product") Long idTinhTrangPhone,
    		@RequestParam("id_camera") Long idCamera) throws IOException {
        Path staticPath = Paths.get("src/main/resources/static/images");
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath).resolve(image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }
        String photo = "\\images"+"\\"+image.getOriginalFilename().toString();       
     
        
        phoneProductService.insertPhoneProduct(namePhoneProduct, idTrangThai, photo, price, idHangDienThoai);
        
        PhoneProduct p = phoneProductService.findPhoneProduct(namePhoneProduct, idTrangThai, photo, price, idHangDienThoai);
        
        phoneProductDetailService.insertPhoneProductDetail(idColor, quantity, idRam, idScreen, idBattery, idMemory, date, idTinhTrangPhone, idCamera,p.getIdPhoneProduct());
        
        PhoneProductDetail pd = phoneProductDetailService.findPhoneProductDetailByIdPhoneProduct(p.getIdPhoneProduct());
           
        phoneProductService.updatePhoneProduct(p.getIdPhoneProduct(), pd);
           
        return "redirect:/admin_phone/phone_product";
    }
    
	

    @GetMapping("/phone_product")
    public String displayPhoneProduct(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
    	
    	
    	List<PhoneProductAndDetail> listPhoneProduct = phoneProductService.displayPhoneProduct();
    	model.addAttribute("listPhoneProduct", listPhoneProduct);
    	
    	return "admin/phoneProduct";
    }

    
    @GetMapping("/phone_product_and_phone_product_detail")
    public String displayPhoneProductAndPhoneProductDetail(@RequestParam("idPhoneProduct") Long idPhoneProduct,Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
    	  	
    	PhoneProductAndPhoneProductDetail p = phoneProductService.displayAllInformationPhoneProduct(idPhoneProduct);
    	model.addAttribute("informationPhoneProduct", p);
    	
    	
    	List<ImagePhoneProductDetail> l = imagePhoneProductService.displayAllImageByIdPhone(idPhoneProduct);
    	model.addAttribute("listImage", l);
    	
    	List<KhuyenMaiPhoneProductDetail> km = khuyenMaiPhoneService.displayKhuyenMaiPhoneByIdPhone(idPhoneProduct);
    	model.addAttribute("listKhuyenMaiPhone", km);
    	
    	List<GioiThieuPhoneProductDetail> gtl = gioiThieuPhoneService.displayGioiThieuPhoneProductDetailByIdPhone(idPhoneProduct);
    	model.addAttribute("listGioiThieuPhoneProductDetail", gtl);
    	return "admin/displayInformationPhoneProduct";
    }
    
    @GetMapping("/update-phone-product")
    public String updatePhoneProduct(@RequestParam("id_phone_product") Long idPhoneProduct,Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
    	
		InformationUpdatePhoneProduct i = phoneProductService.informationToUpdatePhoneProduct(idPhoneProduct);
		model.addAttribute("informationPhoneProduct", i);
		
		List<HangDienThoai> listHangDienThoai = hangDienThoaiService.listHangDienThoaiToUpdatePhoneProduct(idPhoneProduct);
		model.addAttribute("listHangDienThoai", listHangDienThoai);
		
		List<TrangThaiPhoneProduct> listTrangThaiPhone = trangThaiPhoneProductService.displayTrangThaiSanPhamToUpdatePhoneProduct(idPhoneProduct);
		model.addAttribute("listTrangThaiPhone", listTrangThaiPhone);
		
		List<Color> listColor = colorService.listColorToUpdatePhoneProduct(idPhoneProduct);
		model.addAttribute("listColor", listColor);
		
		List<Ram> listRam = ramService.listRamToUpdatePhoneProduct(idPhoneProduct);
		model.addAttribute("listRam", listRam);
		
		List<Screen> listScreen = screenService.displayListScreenToUpdatePhoneProduct(idPhoneProduct);
		model.addAttribute("listScreen", listScreen);
		
		List<Battery> listBattery = batteryService.displayListBatteryToUpdate(idPhoneProduct);
		model.addAttribute("listBattery", listBattery);
		
		List<Memory> listMemory = memoryService.displayListMemoryToUpdate(idPhoneProduct);
		model.addAttribute("listMemory", listMemory);
		
		List<TinhTrangPhoneProduct> listTinhTrangPhoneProduct = tinhTrangSanPhamService.displayListTinhTrangSanPhamTopUpdatePhoneProduct(idPhoneProduct);
		model.addAttribute("listTinhTrangPhoneProduct", listTinhTrangPhoneProduct);
		
		List<Camera> listCamera = cameraService.displayListCameraToUpdate(idPhoneProduct);
		model.addAttribute("listCamera", listCamera);
		
    	return "admin/formUpdatePhoneProduct";
		
    }
    
    @PostMapping("/update-phone-product-process")
    public String updatePhoneProductProcess(@ModelAttribute PhoneProduct p,@ModelAttribute PhoneProductDetail p1,RedirectAttributes redirectAttributes) {
    	
    	phoneProductService.updatePhoneProduct(p);
    	phoneProductDetailService.updatePhoneProductDetail(p1);
    	
    	redirectAttributes.addAttribute("idPhoneProduct", p.getIdPhoneProduct());
    	return "redirect:/admin_phone/phone_product_and_phone_product_detail";
    	
    }
    
    @GetMapping("/lock-phone-product")
    public String lockPhoneProduct(@RequestParam("idPhoneProduct") Long idPhoneProduct) {
    	
    	phoneProductService.lockPhoneProduct(idPhoneProduct);
    	return "redirect:/admin_phone/phone_product";
    	
    }
    
    @GetMapping("/open-phone-product")
    public String openPhoneProduct(@RequestParam("idPhoneProduct") Long idPhoneProduct) {
    	
    	phoneProductService.openPhoneProduct(idPhoneProduct);
    	return "redirect:/admin_phone/phone_product";
    	
    }
}
