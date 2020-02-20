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
import com.example.demo.service.DailyReportService;
import com.example.demo.service.TrainingService;

/**
 * 企業を扱うコントローラー.
 * 
 * @author takahiro.suzuki
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private DailyReportService dailyReportService;

	@Autowired
	private TrainingService trainingService;

	@ModelAttribute
	public CompanyMemberForm setUpCompanyMemberForm() {
		return new CompanyMemberForm();
	}

	/**
	 * ログインを行う.
	 * 
	 * @param model モデル
	 * @param error エラー
	 * @return ログインページ
	 */
	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required = false) Optional<String> error) {
		if (error.isPresent()) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
		}
		return "company/company_login";
	}

	/**
	 * 研修リストを表示.
	 * 
	 * @param model モデル
	 * @param loginComMember ログイン企業メンバー
	 * @return 研修リストページ
	 */
	@RequestMapping("/training_list")
	public String trainingList(Model model, @AuthenticationPrincipal LoginComMember loginComMember) {
		List<Training> trainingList = trainingService
				.showTrainingListByCompanyId(loginComMember.getComMember().getCompanyId());
		model.addAttribute("trainings", trainingList);
		return "company/company_training_list";
	}

	/**
	 * 日報を表示.
	 * 
	 * @param id ID
	 * @param loginComMember ログイン企業担当情報.
	 * @param studentId 受講生ID
	 * @param model モデル
	 * @return 日報詳細ページ
	 */
	@RequestMapping("/view_daily_report/{id}")
	public String trainingDetail(
			  @PathVariable Integer id
			, @AuthenticationPrincipal LoginComMember loginComMember
			, Optional<Integer> studentId // 存在しているかどうか
			, Model model) {
		Training training = trainingService.showTrainingByCompanyId(id, loginComMember.getComMember().getCompanyId());
		studentId.ifPresent(
				stuId -> {
					model.addAttribute("dailyReports", dailyReportService.showDailyReportByStudentIdANDTrainingId(stuId, id));
					model.addAttribute("postStudentId", stuId);
				});
		if(!studentId.isPresent()){ // 受講生IDが存在しないとき.
			if(training.getStudentList().size() > 0) {
				model.addAttribute("dailyReports"
						, dailyReportService.showDailyReportByStudentIdANDTrainingId(
								training.getStudentList().get(0).getId(), training.getId()));
			}
		}
		// 研修
		model.addAttribute("training", training);
		// 理解度情報
		model.addAttribute("intelligibilityForm", StudentController.intelligibilityForm());
		// 講師情報
		model.addAttribute("aboutInstructorForm", StudentController.aboutInstructorForm());
		return "company/company_view_daily_report";
	}

}
