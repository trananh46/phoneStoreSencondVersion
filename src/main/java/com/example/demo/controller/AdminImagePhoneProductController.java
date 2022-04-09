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

import com.example.demo.model.HangDienThoai;
import com.example.demo.model.ImageProduct;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.model.PhoneProduct;
import com.example.demo.service.HangDienThoaiService;
import com.example.demo.service.ImagePhoneProductService;
import com.example.demo.service.ImageProductService;
import com.example.demo.service.PhoneProductService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin_phone")
public class AdminImagePhoneProductController {

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private ImageProductService imageProductService;
	
	@Autowired
	private PhoneProductService phoneProductService;
	
	@Autowired
	private ImagePhoneProductService imagePhoneProductService;
	
	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	
	
	@GetMapping("/form_insert_image_phone_product")
	public String displayFormInsertImagePhoneProduct(@RequestParam("idPhoneProduct") Long idPhoneProduct,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		Long idPhone = idPhoneProduct;
		model.addAttribute("idPhoneProduct", idPhone);
		return "admin/formInsertAnhChiTiet";
	}
	
	
 
    @PostMapping("/insert_image_phone_product")
    public String insertHangDienThoaiProcess(@RequestParam("id_phone_product") Long idPhoneProduct,@RequestParam MultipartFile image,Model model) throws IOException {
        Path staticPath = Paths.get("src/main/resources/static/images");
    
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath).resolve(image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }
        String photo = "\\images"+"\\"+image.getOriginalFilename().toString();       
              
        imageProductService.insertImageProduct(idPhoneProduct,photo);
        ImageProduct i = imageProductService.findImageByNameAndIdProduct(idPhoneProduct, photo);
        PhoneProduct p = phoneProductService.findPhoneProductById(idPhoneProduct);
        imagePhoneProductService.insertImagePhoneProduct(p, i);
             
        return "redirect:/admin_phone/phone_product";
    }
    
	
   
   
    
    
   
	

    
}
