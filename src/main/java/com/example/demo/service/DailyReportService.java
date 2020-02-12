package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.DailyReport;
import com.example.demo.form.DailyReportForm;
import com.example.demo.repository.DailyReportRepository;
import com.example.demo.security.student.LoginStudent;

@Service
@Transactional
public class DailyReportService {
	
	@Autowired
	private DailyReportRepository dailyReportRepository;
	
	public List<DailyReport> showDailyReportList(){
		return dailyReportRepository.findAll();
	}
	
	public void dailyReportSave(DailyReportForm form, @AuthenticationPrincipal LoginStudent loginStudent) {
		DailyReport report = new DailyReport();
		BeanUtils.copyProperties(form, report);
		// 日付をLocalDateに変更
		report.setDate(form.toLocalDate());
		// 受講生のIDを付与
		report.setStudentId(loginStudent.getStudent().getId());
		dailyReportRepository.save(report);
	}
	
	public List<DailyReport> showDailyReportByTrainingId(Integer id){
		return null;
	}

}
