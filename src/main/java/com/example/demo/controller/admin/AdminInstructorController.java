package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.InstructorForm;

@Controller
@RequestMapping("/admin")
public class AdminInstructorController {
	
	@ModelAttribute
	public InstructorForm setUpInstructorForm() {
		return new InstructorForm();
	}
	
	@RequestMapping("/instructor_detail")
	public String instructorDetail() {
		return "admin/instructor_detail";
	}

	@RequestMapping("/instructor_list")
	public String instructorList() {
		return "admin/instructor_list";
	}
	
	@PostMapping("/instructor_insert")
	public String instructorInsert(
			@Validated InstructorForm form
			, BindingResult result
			, RedirectAttributes flash
			) {
		if(result.hasErrors()) {
			return instructorDetail();
		}
		if (!form.getPassword().equals(form.getPasswordConfirm())) {
			result.rejectValue("password", "", "パスワードが一致しませんでした。");
		}
		flash.addFlashAttribute("success", form);
		return "redirect:/admin/instructor_list";
	}


}
