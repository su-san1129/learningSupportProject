package com.example.demo.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.StudentController;
import com.example.demo.domain.DailyReport;
import com.example.demo.domain.Training;
import com.example.demo.service.DailyReportService;
import com.example.demo.service.TrainingService;

@Controller
@RequestMapping("/admin")
public class AdminViewReportController {

	@Autowired
	private DailyReportService dailyReportService;

	@Autowired
	private TrainingService trainingService;

	/**
	 * 日報リストの表示.
	 * 
	 * @param id    日報Id
	 * @param model モデル
	 * @return 日報リストページ
	 */
	@RequestMapping("/view_daily_report/{id}")
	public String viewDailyReport(@PathVariable Integer id, @RequestParam(required = false) Integer studentId,
			Model model) {
		Training training = trainingService.showTraining(id);
		List<DailyReport> dailyReports = new ArrayList<>();
		if (studentId != null) {
			dailyReports = dailyReportService.showDailyReportByStudentIdANDTrainingId(studentId, training.getId());
		} else {
			if(training.getStudentList().size() != 0) {
				dailyReports = dailyReportService.showDailyReportByStudentIdANDTrainingId(training.getStudentList().get(0).getId(), training.getId());
			}
		}
		model.addAttribute("dailyReports", dailyReports);
		model.addAttribute("training", training);
		model.addAttribute("postStudentId", studentId);
		model.addAttribute("intelligibilityForm", StudentController.intelligibilityForm());
		model.addAttribute("aboutInstructorForm", StudentController.aboutInstructorForm());
		return "admin/admin_view_daily_report";
	}

	/**
	 * 週報ページ.
	 * 
	 * @return 週報ページ
	 */
	@RequestMapping("/view_weekly_report")
	public String weeklyReport() {
		return "admin/admin_view_weekly_report";
	}

}
