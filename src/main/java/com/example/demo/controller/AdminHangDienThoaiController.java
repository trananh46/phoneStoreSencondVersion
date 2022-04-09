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
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.service.HangDienThoaiService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin_phone")
public class AdminHangDienThoaiController {
	@Autowired
	private HangDienThoaiService hangDienThoaiService;
	
	@Autowired
	private StaffService staffService;
	
	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	
	@GetMapping("/hang_dien_thoai")
	public String displayHangDienThoai(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<HangDienThoai> listHangDienThoai = hangDienThoaiService.displayHangDienThoai();
		model.addAttribute("listHangDienThoai", listHangDienThoai);
		
		return "admin/hangDienThoai";
	}
		
	@GetMapping("/insert_hang_dien_thoai")
	public String insertHangDienThoai(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);		
		return "admin/formInsertHangDienThoai";
	}
	
    
    @PostMapping("/insert_hang_dien_thoai_process")
    public String insertHangDienThoaiProcess(@RequestParam("name_hang_dien_thoai") String nameHangDienThoai,@RequestParam MultipartFile image) throws IOException {
        Path staticPath = Paths.get("src/main/resources/static/images");
    
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath).resolve(image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }
        String photo = "\\images"+"\\"+image.getOriginalFilename().toString();       
        hangDienThoaiService.insertHangDienThoai(nameHangDienThoai, photo);
        
        return "redirect:/admin_phone/hang_dien_thoai";
    }
    
	
    @GetMapping("/delete_hang_dien_thoai")
    public String deleteHangDienThoai(@RequestParam("idHangDienThoai") Long idHangDienThoai) {
    	hangDienThoaiService.deleteHangDienThoai(idHangDienThoai);
    	return "redirect:/admin_phone/hang_dien_thoai";
    }
   
    @GetMapping("/update_hang_dien_thoai")
    public String updateHangDienThoai(@RequestParam("idHangDienThoai") Long idHangDienThoai,Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
    	
    	HangDienThoai h = hangDienThoaiService.findHangDienThoaiById(idHangDienThoai);
    	model.addAttribute("hangDienThoai", h);
    	return "admin/formUpdateHangDienThoai";
    }
    
    
    @GetMapping("/update-hang_dien_thoai_process")
    public String updateHangDienThoaiProcess(@RequestParam("id_hang_dien_thoai") Long idHangDienThoai,@RequestParam("name_hang_dien_thoai") String nameHangDienThoai)  {
    	hangDienThoaiService.updateHangDienThoai(nameHangDienThoai, idHangDienThoai);
    	return "redirect:/admin_phone/hang_dien_thoai";
    }
	

    
}
