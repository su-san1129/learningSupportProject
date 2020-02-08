package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required = false) Optional<String> error) {
		if(error.isPresent()) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
		}
		return "company/company_login";
	}
	
	@RequestMapping("/training_list")
	public String trainingList() {
		return "company/company_training_list";
	}

}
