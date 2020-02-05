package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {

	@GetMapping("/view_daily_report")
	public String index() {
		return "student/student_view_daily_report";
	}

	@GetMapping("/login")
	public String login() {
		return "student/student_login";
	}
	
	@GetMapping("/training_list")
	public String trainingList() {
		return "student/student_training_list";
	}
}
