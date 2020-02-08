package com.example.demo.controller.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Company;
import com.example.demo.form.CompanyForm;
import com.example.demo.form.CompanyMemberForm;
import com.example.demo.service.CompanyService;

/**
 * 運営者が操作する企業情報のコントローラー.
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminCompanyController {

	@Autowired
	private CompanyService companyService;

	@ModelAttribute
	public CompanyMemberForm setUpForm() {
		return new CompanyMemberForm();
	}

	@GetMapping("/company_list")
	public String companyList(Model model) {
		model.addAttribute("companies", companyService.showAllCompany());
		return "admin/company_list";
	}

	@RequestMapping("/company_detail/{companyId}")
	public String companyDetail(@PathVariable Integer companyId, Model model) {
		Company company = companyService.showCompany(companyId);
		CompanyForm form = new CompanyForm();
		BeanUtils.copyProperties(company, form);
		model.addAttribute("companyForm", form);
		return "admin/company_detail";
	}

	@RequestMapping("/insertCompany")
	public String insertCompany(CompanyForm form) {
		companyService.companySave(form);
		return "redirect:/admin/company_list";
	}

	@RequestMapping("/company_register_charge/{companyId}")
	public String companyRegisterCharge(@Validated CompanyMemberForm form, BindingResult result,
			@PathVariable Integer companyId, Model model) {
		model.addAttribute("company", companyService.showCompany(companyId));
		return "admin/company_register_charge";
	}

	@RequestMapping("/register_company")
	public String registerCompany() {
		return "admin/register_company";
	}
	
	@PostMapping("/registerCompanyMember")
	public String registerCompanyMember(CompanyMemberForm form, RedirectAttributes flash) {
		companyService.companyMemberSave(form);
		flash.addFlashAttribute("flag", true);
		return "redirect:/admin/company_register_charge/"+form.getCompanyId();
	}
	
	@PostMapping("/delete_member/{id}")
	public String deleteMember(@PathVariable Integer id, Integer companyId, RedirectAttributes flash) {
		companyService.deleteMember(id);
		flash.addFlashAttribute("deleteFlag", true);
		return "redirect:/admin/company_register_charge/"+companyId;
	}

}
