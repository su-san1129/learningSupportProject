package com.example.demo.controller.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Company;
import com.example.demo.domain.Student;
import com.example.demo.domain.Training;
import com.example.demo.form.TrainingRegisterForm;
import com.example.demo.service.CompanyService;
import com.example.demo.service.InstructorService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TrainingService;

/**
 * 運営管理者が研修を扱うコントローラー.
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminTrainingController {

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private TrainingService trainingService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private HttpSession session;

	@ModelAttribute
	public TrainingRegisterForm setUpTrainingForm() {
		return new TrainingRegisterForm();
	}

	/**
	 * 研修受講生のインポートを行うページ.
	 * 
	 * @param students 受講生
	 * @param model    モデル
	 * @return 研修受講生
	 */
	@RequestMapping("/training_import_students/{trainingId}")
	public String trainingImportStudents(@PathVariable Integer trainingId
			, @RequestParam(required = false) List<Student> students
			, Model model) {
		if (students != null) {
			model.addAttribute("students", students);
		}
		model.addAttribute("training", trainingService.showTraining(trainingId));
		return "admin/admin_training_import_students";
	}

	/**
	 * 研修リストに表示する
	 * 
	 * @param file
	 * @param model
	 * @return
	 */
	@RequestMapping("/training_input_student")
	public String trainingInputStudent(MultipartFile file, Integer trainingId, Model model) {
		if (file.isEmpty()) {
			model.addAttribute("fileError", "ファイルを選択してください");
			return trainingImportStudents(trainingId, null, model);
		}
		List<Student> students = new ArrayList<>();
		int tryCount = 2;
		try {
			// ファイルの読み込み
			InputStream fileInput = file.getInputStream();
			// ファイルをリーダーに変換
			Reader reader = new InputStreamReader(fileInput);
			BufferedReader br = new BufferedReader(reader);
			// 一行目
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				// 今回はデータ構造が決まっているため、適当なバリデーション
				if (data.length < 4 || 4 < data.length) {
					throw new IOException();
				}
				// 受講生名
				String name = data[0];
				// 受講生メールアドレス
				String email = data[1];
				// 受講生の所属する企業名
				String companyName = data[2];
				// パスワード
				String password = data[3];
				Company company = companyService.showCompanyByName(companyName);
				if (company == null) {
					// 見つからない場合の例外処理.
					throw new SQLException();
				}
				/** 本当は、パスワードを自動生成して、メールを飛ばして変更させるプロセスのほうがいい */
				Student student = new Student(null, name, null, email, password, company.getId(), company, null);
				students.add(student);
				session.setAttribute("students", students);
				tryCount++;
			}
		} catch (IOException e) {
			model.addAttribute("fileInputError", "ファイルの読み込みに失敗しました。");
			e.printStackTrace();
		} catch (SQLException e) {
			model.addAttribute("noCompanyError", tryCount+"行目:企業が存在しませんでした");
			students = null;
			e.printStackTrace();
		}
		return trainingImportStudents(trainingId, students, model);
	}

	/**
	 * 研修リストを表示.
	 * 
	 * @param model モデル
	 * @return 研修リスト
	 */
	@RequestMapping("/training_list")
	public String trainingList(Model model) {
		List<Training> trainings = trainingService.showAllTraining();
		model.addAttribute("trainings", trainings);

		return "admin/admin_training_list";
	}

	/**
	 * 研修詳細.
	 * 
	 * @param id    ID
	 * @param model モデル
	 * @return 研修詳細ページ
	 */
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
	
	/**
	 * 受講生の保存を行う.
	 * @param flash フラッシュスコープ
	 * @param model モデル
	 * @return 研修リスト
	 */
	@PostMapping("/import/insertStudent")
	public String importInsertStudent(Integer trainingId, RedirectAttributes flash, Model model) {
		@SuppressWarnings("unchecked")
		List<Student> students = (List<Student>) session.getAttribute("students");
		if(students != null) {
			students.forEach(student -> {
				if(student.getKana() == null) {
					student.setKana("ふりがな");
				}
				studentService.studentSaveByStudent(student, trainingId);
			});
		}
		session.removeAttribute("students");
		flash.addFlashAttribute("successInsert", "受講生の登録が完了しました");
		return trainingList(model);
		
	}
}
