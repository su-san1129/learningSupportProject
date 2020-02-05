package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/students")
public class StudentController {

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

	@GetMapping("/training_list")
	public String trainingList() {
		return "student/student_training_list";
	}
}
