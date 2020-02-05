package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@RequestMapping("/login")
	public String login() {
		return "company/company_login";
	}
	
	@RequestMapping("/training_list")
	public String trainingList() {
		return "company/company_training_list";
	}

}
