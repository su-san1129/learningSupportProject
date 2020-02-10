package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.TrainingRegisterForm;

@Controller
@RequestMapping("/admin")
public class AdminTrainingController {
	
	@ModelAttribute
	public TrainingRegisterForm setUpTrainingForm() {
		return new TrainingRegisterForm();
	}

	@RequestMapping("/training_import_students")
	public String trainingImportStudents() {
		return "admin/admin_training_import_students";
	}

	@RequestMapping("/training_list")
	public String trainingList() {
		
		return "admin/admin_training_list";
	}

	@RequestMapping("/training_detail")
	public String trainingDetail() {
		
		return "admin/admin_training_detail";
	}
}
