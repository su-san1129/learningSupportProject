package com.example.demo.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Training;
import com.example.demo.form.DailyReportForm;
import com.example.demo.form.StudentRegisterForm;
import com.example.demo.security.student.LoginStudent;
import com.example.demo.service.DailyReportService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TrainingService;

/**
 * 受講生の情報を扱うコントローラー.
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private DailyReportService dailyReportService;

	@Autowired
	private TrainingService trainingService;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public DailyReportForm setUpDailyReportForm() {
		return new DailyReportForm();
	}

	/**
	 * ログイン画面.
	 * 
	 * @param model モデル
	 * @param error エラー
	 * @return ログイン画面
	 */
	@GetMapping("/login")
	public String login(Model model, @RequestParam(required = false) String error) {
		if (error != null) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
		}
		return "student/student_login";
	}

	/**
	 * 受講者の登録ページ.
	 * 
	 * @param form  フォーム
	 * @param model モデル
	 * @return 受講者の登録ページ
	 */
	@RequestMapping("/register")
	public String toRegisterStudent(StudentRegisterForm form, Model model) {
		if (form != null) {
			model.addAttribute("studentRegisterForm", form);
		}
		if (session.getAttribute("password") != null) {
			session.removeAttribute("password");
		}
		return "student/student_register";
	}

	@PostMapping("/register/student")
	public String registerStudent(@Validated StudentRegisterForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return toRegisterStudent(form, model);
		}
		if (!form.getPassword().equals(form.getPasswordConfirm())) {
			result.rejectValue("password", "", "パスワードが一致しませんでした。");
			return toRegisterStudent(form, model);
		}
		session.setAttribute("password", form.getPassword());
		return "student/student_register_confirm";
	}

	@PostMapping("/register/confirm")
	public String toRegisterStudentConfirm() {
		return "student/student_register_confirm";
	}

	@PostMapping("/register/finish")
	public String registerFinish(StudentRegisterForm form) {
		String password = (String) session.getAttribute("password");
		form.setPassword(password);
		form.setCompanyId(1);
		session.removeAttribute("password");
		studentService.studentSave(form);
		return "redirect:/students/register_finish";
	}

	/**
	 * 研修リストを表示する.
	 * 
	 * @param model モデル
	 * @return 研修ページ
	 */
	@GetMapping("/training_list")
	public String trainingList(Model model, @AuthenticationPrincipal LoginStudent loginStudent) {
		List<Training> trainingList = trainingService.showTrainingListByStudentId(loginStudent.getStudent().getId());
		model.addAttribute("trainings", trainingList);
		return "student/student_training_list";
	}

	@GetMapping("/view_daily_report/{trainingId}")
	public String index(@PathVariable Integer trainingId, Model model,
			@AuthenticationPrincipal LoginStudent loginStudent) {
		// 研修ID
		model.addAttribute("trainingId", trainingId);
		// 受講生ID
		Integer studentId = loginStudent.getStudent().getId();
		// 日報リスト
		model.addAttribute("dailyReports",
				dailyReportService.showDailyReportByStudentIdANDTrainingId(studentId, trainingId));
		// 理解度に表示するデータ
		model.addAttribute("intelligibilityForm", intelligibilityForm());
		// 講師評価に表示するデータ
		model.addAttribute("aboutInstructorForm", aboutInstructorForm());
		return "student/student_view_daily_report";
	}

	@GetMapping("/register_daily_report/{trainingId}")
	public String registerDailyReport(@PathVariable Integer trainingId, Model model) {
		LocalDate dateNow = LocalDate.now();
		// フォームに表示する時刻
		model.addAttribute("dateNow", dateNow);
		// 理解度に表示するデータ
		model.addAttribute("intelligibilityForm", intelligibilityForm());
		// 講師評価に表示するデータ
		model.addAttribute("aboutInstructorForm", aboutInstructorForm());
		// 研修ID
		model.addAttribute("trainingId", trainingId);
		return "student/student_register_daily_report";
	}

	/**
	 * 日報の登録処理.
	 * 
	 * @param form         フォーム
	 * @param result       バリデーションチェック
	 * @param model        モデル
	 * @param loginStudent ログイン中の受講者情報
	 * @return 研修リストページ
	 */
	@PostMapping("/insert_daily_report")
	public String insertDailyReport(@Validated DailyReportForm form, BindingResult result, Model model,
			@AuthenticationPrincipal LoginStudent loginStudent) {
		if (result.hasErrors()) {
			return registerDailyReport(form.getTrainingId(), model);
		}
		dailyReportService.dailyReportSave(form, loginStudent);
		return "redirect:/students/training_list";
	}

	/** 日報登録に表示する理解度の情報 */
	public static Map<Integer, String> intelligibilityForm() {
		Map<Integer, String> intelligibilityMap = new HashMap<>();
		intelligibilityMap.put(1, "良く理解できた");
		intelligibilityMap.put(2, "概ね理解できた");
		intelligibilityMap.put(3, "普通");
		intelligibilityMap.put(4, "少し難しかった");
		intelligibilityMap.put(5, "とても難しかった");
		return intelligibilityMap;
	}

	/** 日報登録に表示する講師評価の情報 */
	public static Map<Integer, String> aboutInstructorForm() {
		Map<Integer, String> aboutInstructorMap = new HashMap<>();
		aboutInstructorMap.put(1, "とても丁寧だった");
		aboutInstructorMap.put(2, "概ね丁寧だった");
		aboutInstructorMap.put(3, "どちらともいえない");
		aboutInstructorMap.put(4, "やや丁寧ではなかった");
		aboutInstructorMap.put(5, "全く丁寧ではなかった");
		return aboutInstructorMap;
	}
}
