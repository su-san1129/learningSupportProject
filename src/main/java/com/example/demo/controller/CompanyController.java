package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Training;
import com.example.demo.form.CompanyMemberForm;
import com.example.demo.security.Company.LoginComMember;
import com.example.demo.service.CompanyService;
import com.example.demo.service.TrainingService;


@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private TrainingService trainingService;
	
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
	public String trainingList(Model model, @AuthenticationPrincipal LoginComMember loginComMember) {
		List<Training> trainingList = trainingService.showTrainigListByCompanyId(loginComMember.getComMember().getCompanyId());
		model.addAttribute("trainings", trainingList);
		return "company/company_training_list";
	}
	
	@RequestMapping("/view_daily_report/{id}")
	public String trainingDetail(@PathVariable Integer id) {
		Training training = trainingService.showTrainigListByCompanyId(companyId);
		return "";
	}
	

}
