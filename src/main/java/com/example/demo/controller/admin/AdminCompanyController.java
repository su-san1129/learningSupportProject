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
import com.example.demo.domain.CompanyMember;
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

	/**
	 * 企業リストを表示する.
	 * 
	 * @param model モデル
	 * @return 企業リスト
	 */
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
	public String companyRegisterCharge(@PathVariable Integer companyId, Model model) {
		model.addAttribute("company", companyService.showCompany(companyId));
		return "admin/company_register_charge";
	}

	@RequestMapping("/register_company")
	public String registerCompany() {
		return "admin/register_company";
	}

	@PostMapping("/registerCompanyMember")
	public String registerCompanyMember(@Validated CompanyMemberForm form, BindingResult result,
			RedirectAttributes flash, Model model) {
		if(result.hasErrors()) {
			return companyRegisterCharge(form.getCompanyId(), model);
		}
		if(!form.getPassword().equals(form.getPasswordConfirm())) {
			result.rejectValue("password", "", "パスワードが一致しません");
			return companyRegisterCharge(form.getCompanyId(), model);
		}
		companyService.companyMemberSave(form);
		if(form.getId() != null ) {
			flash.addFlashAttribute("updateFlag", true);
		} else {
			flash.addFlashAttribute("flag", true);
		}
		return "redirect:/admin/company_register_charge/" + form.getCompanyId();
	}

	@PostMapping("/delete_member/{id}")
	public String deleteMember(@PathVariable Integer id, Integer companyId, RedirectAttributes flash) {
		companyService.deleteMember(id);
		flash.addFlashAttribute("deleteFlag", true);
		return "redirect:/admin/company_register_charge/" + companyId;
	}
	
	/**
	 * メンバーの編集ページ
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/company_member_edit/{id}")
	public String companyMemberEdit(@PathVariable Integer id, Model model) {
		CompanyMember member = companyService.showCompanyMember(id);
		CompanyMemberForm form = new CompanyMemberForm();
		BeanUtils.copyProperties(member, form);
		form.setPasswordConfirm(form.getPassword());
		model.addAttribute("companyMemberForm", form);
		return companyRegisterCharge(member.getCompanyId(), model);
	}
	
	

}
