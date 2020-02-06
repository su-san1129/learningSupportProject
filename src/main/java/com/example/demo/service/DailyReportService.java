package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.Database;
import com.example.demo.domain.DailyReport;
import com.example.demo.form.DailyReportForm;
import com.example.demo.repository.DailyReportRepository;

@Service
public class DailyReportService {
	
	@Autowired
	private Database database;
	
	@Autowired
	private DailyReportRepository dailyReportRepository;
	
	public List<DailyReport> showDailyReportList(){
		return dailyReportRepository.findAll();
	}
	
	public void dailyReportSave(DailyReportForm form) {
		DailyReport report = new DailyReport();
		BeanUtils.copyProperties(form, report);
		report.setDate(form.toLocalDate());
		dailyReportRepository.save(report);
	}
	
	public List<DailyReport> showDailyReportByTrainingId(Integer id){
		List<DailyReport> dailyReportList = new ArrayList<>();
		dailyReportList.add(database.getDailyReport());
		dailyReportList.add(database.getDailyReport());
		return dailyReportList;
	}

}
