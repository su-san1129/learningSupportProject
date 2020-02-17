package com.example.demo.controller.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 * @param id 日報Id
	 * @param model モデル
	 * @return 日報リストページ
	 */
	@RequestMapping("/view_daily_report/{id}")
	public String viewDailyReport(@PathVariable Integer id, Model model) {
		model.addAttribute("displayDate", setDisplayDate());
		Training training = trainingService.showTraining(id);
		//List<DailyReport> dailyReports = dailyReportService.showDailyReportByTrainingId(id);
		//model.addAttribute("dailyReports", dailyReports);
		model.addAttribute("training", training);
		return "admin/admin_view_daily_report";
	}


	@RequestMapping("/view_weekly_report")
	public String weeklyReport() {
		return "admin/admin_view_weekly_report";
	}
	
	/** 日報一覧に表示する日付 */
	private List<LocalDate> setDisplayDate() {
		List<LocalDate> displayDate = new ArrayList<>();
		LocalDateTime startDate = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
		LocalDateTime endDate = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());
		while(startDate.isBefore(endDate)) {
			displayDate.add(startDate.toLocalDate());
			startDate = startDate.plusDays(1);
		}
		return displayDate;
	}
	

	
	

}
