package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.TrainingRegisterForm;
import com.example.demo.service.InstructorService;

@Controller
@RequestMapping("/admin")
public class AdminTrainingController {
	
	@Autowired
	private InstructorService instructorService;
	
//	@Autowired
//	private TrainingService trainingService;
	
	@ModelAttribute
	public TrainingRegisterForm setUpTrainingForm() {
		return new TrainingRegisterForm();
	}

	@RequestMapping("/training_import_students")
	public String trainingImportStudents() {
		return "admin/admin_training_import_students";
	}

	@RequestMapping("/training_list")
	public String trainingList(Model model) {
		
		return "admin/admin_training_list";
	}

	@RequestMapping("/training_detail")
	public String trainingDetail(Model model) {
		model.addAttribute("instructors", instructorService.showAllInstructor());
		return "admin/admin_training_detail";
	}
	
	@PostMapping("/training_register")
	public String trainingRegister(@Validated TrainingRegisterForm form, BindingResult result) {
		
		
		return "";
	}
}
