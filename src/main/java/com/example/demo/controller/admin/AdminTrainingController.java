package com.example.demo.controller.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Student;
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
	public String trainingImportStudents(@RequestParam(required = false) List<Student> students, Model model) {
		if (students != null) {
			model.addAttribute("students", students);
		}
		return "admin/admin_training_import_students";
	}

	/**
	 * 研修リストに表示する
	 * @param file
	 * @param model
	 * @return
	 */
	@RequestMapping("/training_input_student")
	public String trainingInputStudent(MultipartFile file, Model model) {
		if(file.isEmpty()) {
			model.addAttribute("fileError", "ファイルを選択してください");
			return trainingImportStudents(null, model);
		}
		List<Student> students = new ArrayList<>();
		try {
			InputStream fileInput = file.getInputStream();
			Reader reader = new InputStreamReader(fileInput);
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if(data.length < 4 || 4 < data.length) {
					throw new IOException();
				}
				String name = data[0];
				String email = data[1];
				String companyName = data[2];
				String password = data[3];
				Student student = new Student(null, name, null, email, password, null, null, null);
				students.add(student);
			}
		} catch (IOException e) {
			model.addAttribute("fileInputError", "ファイルの読み込みに失敗しました。");
			e.printStackTrace();
		}
		return trainingImportStudents(students, model);

	}

	@RequestMapping("/training_list")
	public String trainingList(Model model) {
		List<Training> trainings = trainingService.showAllTraining();
		model.addAttribute("trainings", trainings);

		return "admin/admin_training_list";
	}

	@RequestMapping("/training_detail")
	public String trainingDetail(@RequestParam(required = false) Optional<Integer> id, Model model) {
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
	 * @param form   フォーム
	 * @param result 結果
	 * @param model  モデル
	 * @return 研修一覧ページ
	 */
	@PostMapping("/training_register")
	public String trainingRegister(@Validated TrainingRegisterForm form, BindingResult result, Model model) {
		if (form.getInstructorId() == 0) {
			result.rejectValue("instructorId", "", "講師を選択してください");
		}
		if (result.hasErrors()) {
			return trainingDetail(null, model);
		}
		trainingService.trainingSave(form);
		return "redirect:/admin/training_list";
	}
}
