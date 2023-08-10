package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
	@GetMapping(value = "/home/index")
	private String index(Model model) {
		model.addAttribute("message", "đây là index");
		return "home/index";
	}
	
	@GetMapping(value = "/home/about")
	private String about(Model model ) {
		model.addAttribute("message", "đây là about");
		return "home/index";
	}

	@GetMapping(value = "/home/admins")
	private String admins(Model model) {
		model.addAttribute("message","Xin chào quản trị viên ");
		return "home/index";
	}
	
	@GetMapping(value = "/home/users")
	private String users(Model model) {
		model.addAttribute("message","Xin chào thằng nhân viên");
		return "home/index";
	}
	
	@GetMapping(value = "/home/authenticated")
	private String authenticated(Model model) {
		model.addAttribute("message","Xin chào thằng đã đăng nhập");
		return "home/index";
	}
	
	@GetMapping(value = "/home/thymeleaf1")
	private String thymeleaf1(Model model) {
		model.addAttribute("message","Thymeleaf bình thường");
		return "home/thymeleaf1";
	}
	
	@GetMapping(value = "/home/thymeleaf2")
	private String thymeleaf2(Model model) {
		model.addAttribute("message","Thymeleaf có thư viện mở rộng");
		return "home/thymeleaf2";
	}
}
