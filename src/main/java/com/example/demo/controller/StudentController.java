package com.example.demo.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.api.Database;
import com.example.demo.domain.Training;
import com.example.demo.form.DailyReportForm;
import com.example.demo.form.StudentRegisterForm;
import com.example.demo.service.DailyReportService;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private DailyReportService dailyReportService;

	@Autowired
	private HttpSession session;

	@Autowired
	private Database database;

	@GetMapping("/login")
	public String login(Model model, @RequestParam(required = false) String error) {
		if (error != null) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
		}
		return "student/student_login";
	}

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

	@RequestMapping("/register/student")
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

	@RequestMapping("/register/confirm")
	public String toRegisterStudentConfirm() {
		return "student/student_register_confirm";
	}

	@RequestMapping("/register/finish")
	public String registerFinish(StudentRegisterForm form) {
		String password = (String) session.getAttribute("password");
		form.setPassword(password);
		form.setCompanyId(1);
		session.removeAttribute("password");
		studentService.studentSave(form);
		return "redirect:/students/register_finish";
	}

	@GetMapping("/training_list")
	public String trainingList(Model model) {
		List<Training> trainingList = database.trainingList();
		model.addAttribute("trainings", trainingList);
		return "student/student_training_list";
	}

	@GetMapping("/view_daily_report/{trainingId}")
	public String index(@PathVariable Integer trainingId, Model model) {
		model.addAttribute("trainingId", trainingId);
		model.addAttribute("dailyReportList", dailyReportService.showDailyReportByTrainingId(trainingId));
		return "student/student_view_daily_report";
	}

	@RequestMapping("/register_daily_report/{trainingId}")
	public String registerDailyReport(@PathVariable Integer trainingId, Model model) {
		LocalDate dateNow = LocalDate.now();
		// フォームに表示する時刻
		model.addAttribute("dateNow", dateNow);
		// 理解度に表示するデータ
		model.addAttribute("intelligibilityForm", intelligibilityForm());
		// 講師評価に表示するデータ
		model.addAttribute("aboutInstructorForm", aboutInstructorForm());
		model.addAttribute("trainingId", trainingId);
		return "student/student_register_daily_report";
	}

	@PostMapping("/insert_daily_report")
	public String insertDailyReport(DailyReportForm form) {
		System.out.println(form);

		return "redirect:/students/training_list";
	}

	/** 日報登録に表示する理解度の情報 */
	private Map<Integer, String> intelligibilityForm() {
		Map<Integer, String> intelligibilityMap = new HashMap<>();
		intelligibilityMap.put(1, "良く理解できた");
		intelligibilityMap.put(2, "概ね理解できた");
		intelligibilityMap.put(3, "普通");
		intelligibilityMap.put(4, "少し難しかった");
		intelligibilityMap.put(5, "とても難しかった");
		return intelligibilityMap;
	}

	/** 日報登録に表示する講師評価の情報 */
	private Map<Integer, String> aboutInstructorForm() {
		Map<Integer, String> aboutInstructorMap = new HashMap<>();
		aboutInstructorMap.put(1, "とても丁寧だった");
		aboutInstructorMap.put(2, "概ね丁寧だった");
		aboutInstructorMap.put(3, "どちらともいえない");
		aboutInstructorMap.put(4, "やや丁寧ではなかった");
		aboutInstructorMap.put(5, "全く丁寧ではなかった");
		return aboutInstructorMap;
	}
}
