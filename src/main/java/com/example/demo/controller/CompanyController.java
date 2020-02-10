package com.example.demo.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.CompanyMemberForm;
import com.example.demo.service.CompanyService;


@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@ModelAttribute
	public CompanyMemberForm setUpCompanyMemberForm() {
		return new CompanyMemberForm();
	}
	
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
