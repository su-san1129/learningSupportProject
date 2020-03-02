package com.example.demo.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Admin;
import com.example.demo.domain.AdminResponsibleCompany;
import com.example.demo.form.AdminRegisterForm;
import com.example.demo.service.AdminService;

/**
 * 運営管理者を扱うコントローラー.
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

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
	public String login(Model model, @RequestParam(required = false) Optional<String> error) {
		if (error.isPresent()) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
		}
		return "admin/admin_login";
	}

	/**
	 * 登録ページ.
	 * 
	 * @param form フォーム
	 * @param model モデル
	 * @return 登録ページ
	 */
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

	/**
	 * 運営管理者の登録.
	 * 
	 * @param form フォーム
	 * @param result エラーチェック
	 * @param model モデル
	 * @return 運営管理者の登録確認ページ
	 */
	@RequestMapping("/registerAdmin")
	public String registerAdmin(@Validated AdminRegisterForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return toRegisterAdmin(form, model);
		}
		if (!form.getPassword().equals(form.getPasswordConfirm())) {
			result.rejectValue("password", "", "パスワードが一致しませんでした。");
			return toRegisterAdmin(form, model);
		}
		session.setAttribute("adminPassword", form.getPassword());
		return "admin/admin_register_confirm";
	}

	/**
	 * 登録確認ページ.
	 * 
	 * @return 登録完了ページ
	 */
	@PostMapping("/register_confirm")
	public String toRegisterAdminConfirm() {
		return "admin/admin_register_confirm";
	}

	/**
	 * 登録完了ページ.
	 * 
	 * @param form フォーム
	 * @return 運営管理者一覧ページ
	 */
	@PostMapping("/register_finish")
	public String registerFinish(AdminRegisterForm form) {
		String password = (String) session.getAttribute("adminPassword");
		form.setPassword(password);
		adminService.adminSave(form);
		session.removeAttribute("adminPassword");
		return "redirect:/admin/facility_manager_list";
	}

	/**
	 * 印刷ページ.
	 * 
	 * @return 印刷結果
	 */
	@RequestMapping("/print_detail")
	public String printDetail() {
		return "admin/admin_print_daily_report";
	}

	/**
	 * 企業担当者を登録するページ.
	 * 
	 * @param id ID
	 * @param model モデル
	 * @param form フォーム
	 * @return 企業担当者リスト
	 */
	@RequestMapping("/facility_manager_detail/{id}")
	public String facilityManagerDetail(@PathVariable Integer id,Model model, AdminRegisterForm form) {
		if(form.getName() == null) {
			form = new AdminRegisterForm();
			BeanUtils.copyProperties( adminService.showAdminIncludeCompany(id), form);
			model.addAttribute("adminRegisterForm", form);
		}
		/* ========================================================================================
		 * 運営管理者がもつ企業一覧(バリデーション時にデータが消えることを防ぐため、フォームに持たせない)
		 * adminRegisterFormではなく、adminのドメインをviewに渡せばSQLの発行回数は減らせそう。
		 * ========================================================================================*/
		model.addAttribute("adminHasComnpanies", adminService.showAdminHasCompanies(id));
		// 全企業情報
		model.addAttribute("companies", adminService.showCompanies());
		return "admin/facility_manager_detail";
	}

	@RequestMapping("/facility_manager_list")
	public String facilityManagerList(Model model) {
		List<Admin> adminList = adminService.showAllAdminsIncludeResponsibleCompany();
		model.addAttribute("admins", adminList);
		return "admin/facility_manager_list";
	}
	
	@PostMapping("/facility_manager_edit")
	public String facilityManagerEdit(@Validated AdminRegisterForm form, BindingResult result, Model model) {
		if (!form.getPassword().equals(form.getPasswordConfirm())) {
			result.rejectValue("password", "", "パスワードが一致しませんでした。");
		}
		if(result.hasErrors()) {
			return facilityManagerDetail(form.getId(), model, form);
		}
		// 企業IDを持っていた場合は、運営者担当企業テーブルに保存.
		if(form.getCompanyIdList() != null) {
			adminService.arcDeleteByAdminId(form.getId());
			form.getCompanyIdList().forEach( id -> {
				AdminResponsibleCompany arc = new AdminResponsibleCompany();
				arc.setAdminId(form.getId());
				arc.setCompanyId(id);
				form.setCanShowAllCompany(false);
				adminService.arcSave(arc);
			});
		// 企業IDを持っていない場合はすべての企業を見られる権限を持つ.
		} else {
			form.setCanShowAllCompany(true);
		}
		adminService.adminSave(form);
		return "redirect:/admin/facility_manager_list";
		
	}

	
}
