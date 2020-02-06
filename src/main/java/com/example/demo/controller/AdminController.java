package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Company;
import com.example.demo.form.AdminRegisterForm;
import com.example.demo.form.CompanyForm;
import com.example.demo.service.AdminService;
import com.example.demo.service.CompanyService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public AdminRegisterForm setUpAdminRegisterForm() {
		return new AdminRegisterForm();
	}

	/**
	 * ログインページの表示.
	 * 
	 * @param model モデル
	 * @param error ログインエラー
	 * @return ログインページ
	 */
	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required = false) String error) {
		if (error != null) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
		}
		return "admin/admin_login";
	}

	@RequestMapping("/register")
	public String toRegisterAdmin(AdminRegisterForm form, Model model) {
		if (form != null) {
			model.addAttribute("adminRegisterForm", form);
		}
		if (session.getAttribute("password") != null) {
			session.removeAttribute("password");
		}
		return "admin/admin_register";
	}

	@RequestMapping("/registerAdmin")
	public String registerAdmin(@Validated AdminRegisterForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return toRegisterAdmin(form, model);
		}
		if (!form.getPassword().equals(form.getPasswordConfirm())) {
			result.rejectValue("password", "", "パスワードが一致しませんでした。");
			return toRegisterAdmin(form, model);
		}
		session.setAttribute("password", form.getPassword());
		return "admin/admin_register_confirm";
	}

	@RequestMapping("/register_confirm")
	public String toRegisterAdminConfirm() {
		return "admin/admin_register_confirm";
	}

	@RequestMapping("/register_finish")
	public String registerFinish(AdminRegisterForm form) {
		String password = (String) session.getAttribute("password");
		form.setPassword(password);
		session.removeAttribute("password");
		adminService.adminSave(form);
		return "redirect:/admin/register_finish";
	}

	@RequestMapping("/print_detail")
	public String printDetail() {
		return "admin/admin_print_daily_report";
	}

	@RequestMapping("/training_import_students")
	public String trainingImportStudents() {
		return "admin/admin_training_import_students";
	}

	@RequestMapping("/training_list")
	public String trainingList() {
		return "admin/admin_training_list";
	}

	@RequestMapping("/view_daily_report")
	public String viewDailyReport() {
		return "admin/admin_view_daily_report";
	}

	@RequestMapping("/view_weekly_report")
	public String weeklyReport() {
		return "admin/admin_view_weekly_report";
	}

	@RequestMapping("/training_detail")
	public String trainingDetail() {
		return "admin/admin_training_detail";
	}

	@RequestMapping("/company_list")
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

	@RequestMapping("/facility_manager_detail")
	public String facilityManagerDetail() {
		return "admin/facility_manager_detail";
	}

	@RequestMapping("/facility_manager_list")
	public String facilityManagerList(Model model) {
		List<Admin> adminList = adminService.showAllAdmins();
		model.addAttribute("admins", adminList);
		return "admin/facility_manager_list";
	}

	@RequestMapping("/instructor_detail")
	public String instructorDetail() {
		return "admin/instructor_detail";
	}

	@RequestMapping("/instructor_list")
	public String instructorList() {
		return "admin/instructor_list";
	}

	@RequestMapping("/register_company")
	public String registerCompany() {
		return "admin/register_company";
	}

}
