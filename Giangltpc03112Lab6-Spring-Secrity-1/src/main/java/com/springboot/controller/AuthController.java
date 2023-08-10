package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping(value = "/auth/login/form")
	public String form() {
		System.out.println("Vào form login");
		return "/auth/login";
	}
	
	@GetMapping(value = "/auth/login/success")
	public String success(Model model) {
		System.out.println("login thành công");
		model.addAttribute("message","Đăng nhập thành công");
		return "forward:/auth/login/form";
	}
	
	@GetMapping(value = "/auth/login/error")
	public String error(Model model) {
		System.out.println("login thất bại");
		model.addAttribute("message","Đăng nhập thất bại");
		return "forward:/auth/login/form";
	}
	
	@GetMapping("/auth/logoff/success")
	public String logoff(Model model) {
		System.out.println("Đăng xuất thành công");
		model.addAttribute("message","Đăng xuất thành công");
		return "/auth/login";
	}
	
	@GetMapping("/auth/access/denied")
	public String denied(Model model) {
		System.out.println("không có quyền truy cập");
		model.addAttribute("message","không có quyền truy cập");
		return "/auth/login";
	}
	
}
