package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.api.Database;
import com.example.demo.domain.Training;
import com.example.demo.form.StudentRegisterForm;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private Database database;

	@GetMapping("/view_daily_report")
	public String index() {
		return "student/student_view_daily_report";
	}

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
		return "redirect:student/student_register_finish";
	}

	@GetMapping("/training_list")
	public String trainingList(Model model) {
		List<Training> trainingList = database.trainingList();
		model.addAttribute("trainings", trainingList);
		return "student/student_training_list";
	}
	
	@RequestMapping("/register_daily_report")
	public String registerDailyReport() {
		return "student/student_register_daily_report";
	}
}
