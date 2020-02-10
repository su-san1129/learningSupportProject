package com.example.demo.controller.admin;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Instructor;
import com.example.demo.form.InstructorForm;
import com.example.demo.service.InstructorService;

@Controller
@RequestMapping("/admin")
public class AdminInstructorController {

	@Autowired
	private InstructorService instructorService;

	@ModelAttribute
	public InstructorForm setUpInstructorForm() {
		return new InstructorForm();
	}

	@RequestMapping("/instructor_detail")
	public String instructorDetail(Model model, @RequestParam(required = false) Optional<Integer> id) {
		id.ifPresent( instructorId -> {
			System.out.println(instructorId);
			Instructor instructor = instructorService.showInstructor(instructorId);
			InstructorForm form = new InstructorForm();
			BeanUtils.copyProperties(instructor, form);
			model.addAttribute("instructorForm", form);
		});
		return "admin/instructor_detail";

	}

	@RequestMapping("/instructor_list")
	public String instructorList(Model model) {
		model.addAttribute("instructors", instructorService.showAllInstructor());
		return "admin/instructor_list";
	}

	@PostMapping("/instructor_insert")
	public String instructorInsert(@Validated InstructorForm form, BindingResult result, RedirectAttributes flash,
			Model model) {
		if (result.hasErrors()) {
			return instructorDetail(model, null);
		}
		if (!form.getPassword().equals(form.getPasswordConfirm())) {
			result.rejectValue("password", "", "パスワードが一致しませんでした。");
		}
		instructorService.instructorSave(form);
		flash.addFlashAttribute("success", form);
		return "redirect:/admin/instructor_list";
	}

}
