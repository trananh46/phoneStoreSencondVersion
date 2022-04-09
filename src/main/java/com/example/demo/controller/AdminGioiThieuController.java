package com.example.demo.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.GioiThieu;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.service.GioiThieuPhoneService;
import com.example.demo.service.GioiThieuService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin_phone")
public class AdminGioiThieuController {
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private GioiThieuService gioiThieuService;
	
	@Autowired
	private GioiThieuPhoneService gioiThieuPhoneService;
	
	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	
	@GetMapping("/gioi_thieu")
	public String displayGioiThieu(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<GioiThieu> listGioiThieu = gioiThieuService.displayGioiThieu();
		model.addAttribute("listGioiThieu", listGioiThieu);
		return "admin/gioiThieu";
	}
	
	@GetMapping("/insert_gioi_thieu")
	public String insertGioiThieu(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		return "admin/formInsertGioiThieu";
	}
	
	
    @PostMapping("/insert_gioi_thieu_process")
    public String insertHangDienThoaiProcess(@RequestParam("title") String title,@RequestParam("post") String post,@RequestParam("loai_dien_thoai") String loaiDienThoai,@RequestParam MultipartFile image) throws IOException {
        Path staticPath = Paths.get("src/main/resources/static/images");
    
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath).resolve(image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }
        String photo = "\\images"+"\\"+image.getOriginalFilename().toString();       
     
        gioiThieuService.insertGioiThieu(title, post, loaiDienThoai, photo);
        return "redirect:/admin_phone/gioi_thieu";
    }
    
	
    @GetMapping("/update_gioi_thieu")
    public String updateGioiThieu(@RequestParam("idGioiThieu") Long idGioiThieu,Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
    	
    	GioiThieu g = gioiThieuService.findGioiThieuById(idGioiThieu);
    	model.addAttribute("gioiThieu", g);
    	
    	return "admin/formUpdateGioiThieu";
    }
   
    
    @GetMapping("/update_gioi_thieu_process")
    public String updateGioiThieuProcess(@RequestParam("title") String title,@RequestParam("post") String post,@RequestParam("loai_dien_thoai") String loaiDienThoai,@RequestParam("id_gioi_thieu") Long idGioiThieu) {
    	gioiThieuService.updateGioiThieu(title, post, loaiDienThoai, idGioiThieu);
    	return "redirect:/admin_phone/gioi_thieu";
    }
    
    @GetMapping("/delete_gioi_thieu")
    public String deleteGioiThieu(@RequestParam("idGioiThieu") Long idGioiThieu) {
    	gioiThieuService.deleteGioiThieu(idGioiThieu);
    	return "redirect:/admin_phone/gioi_thieu";
    }
	
    @GetMapping("/insert_gioi_thieu_phone_product_detail")
    public String insertGioiThieuPhoneProductDetail(@RequestParam("idPhoneProduct") Long idPhoneProduct,@RequestParam("hangDienThoai") String hangDienThoai,Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
    	
    	
    	List<GioiThieu> listGioiThieu = gioiThieuService.displayGioiThieuByTitleAndLoaiDienThoai(hangDienThoai);
    	model.addAttribute("listGioiThieu", listGioiThieu);
    	
    	Long idPhone = idPhoneProduct;
    	model.addAttribute("idPhoneProduct", idPhone);
    	
    	 	
    	return "admin/formInsertGioiThieuPhoneProductDetail";
    }
    
    @GetMapping("/insert_gioi_thieu_phone_product_detail_process")
    public String insertGioiThieuPhoneProductDetailProcess(@RequestParam("id_gioi_thieu") Long idGioiThieu,@RequestParam("id_phone_product") Long idPhoneProduct) {
    	
    	gioiThieuPhoneService.insertGioiThieuPhone(idGioiThieu, idPhoneProduct);
    	return "redirect:/admin_phone/phone_product";
    }
    
    @GetMapping("/delete_gioi_thieu_phone_detail")
    public String deleteGioiThieuPhoneDetail(@RequestParam("id_gioi_thieu_phone") Long idGioiThieuPhone) {
    		gioiThieuPhoneService.deleteGioiThieuPhoneProductDetail(idGioiThieuPhone);
    		return "redirect:/admin_phone/phone_product";
    }
}
