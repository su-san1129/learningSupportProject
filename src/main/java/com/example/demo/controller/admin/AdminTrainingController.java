package com.example.demo.controller.admin;

import java.time.format.DateTimeFormatter;
import java.util.List;
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

import com.example.demo.domain.Training;
import com.example.demo.form.TrainingRegisterForm;
import com.example.demo.service.InstructorService;
import com.example.demo.service.TrainingService;

@Controller
@RequestMapping("/admin")
public class AdminTrainingController {
	
	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private TrainingService trainingService;
	
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
		List<Training> trainings = trainingService.showAllTraining();
		model.addAttribute("trainings", trainings);
		
		return "admin/admin_training_list";
	}

	@RequestMapping("/training_detail")
	public String trainingDetail(@RequestParam(required = false) Optional<Integer> id,Model model) {
		id.ifPresent(trainingId -> {
			System.out.println(trainingId);
			TrainingRegisterForm form = new TrainingRegisterForm();
			Training training = trainingService.showTraining(trainingId);
			BeanUtils.copyProperties(training, form);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			form.setStartDate(training.getStartDate().format(formatter));
			form.setEndDate(training.getEndDate().format(formatter));
			model.addAttribute("trainingRegisterForm", trainingService.showTraining(trainingId));
		});
		model.addAttribute("instructors", instructorService.showAllInstructor());
		return "admin/admin_training_detail";
	}
	
	/**
	 * 研修の登録.
	 * 
	 * @param form フォーム
	 * @param result 結果
	 * @param model モデル
	 * @return 研修一覧ページ
	 */
	@PostMapping("/training_register")
	public String trainingRegister(@Validated TrainingRegisterForm form, BindingResult result, Model model) {
		if(form.getInstructorId() == 0) {
			result.rejectValue("instructorId", "", "講師を選択してください");
		}
		if(result.hasErrors()) {
			return trainingDetail(null, model);
		}
		trainingService.trainingSave(form);
		return "redirect:/admin/training_list";
	}
}
