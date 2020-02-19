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

/**
 * 日報を操作するサービスクラス.
 * 
 * @author takahiro.suzuki
 *
 */
@Service
@Transactional
public class DailyReportService {
	
	@Autowired
	private DailyReportRepository dailyReportRepository;
	
	/**
	 * 日報の全件検索.
	 * 
	 * @return 日報リスト
	 */
	public List<DailyReport> showDailyReportList(){
		return dailyReportRepository.findAll();
	}
	
	/**
	 * 日報の保存.
	 * 
	 * @param form フォーム
	 * @param loginStudent ログイン中の受講生
	 */
	public void dailyReportSave(DailyReportForm form, @AuthenticationPrincipal LoginStudent loginStudent) {
		DailyReport report = new DailyReport();
		BeanUtils.copyProperties(form, report);
		// 日付をLocalDateに変更
		report.setDate(form.toLocalDate());
		// 受講生のIDを付与
		report.setStudentId(loginStudent.getStudent().getId());
		dailyReportRepository.save(report);
	}
	
	/**
	 * 研修IDで日報を検索.
	 * @param id 研修ID
	 * @return 日報
	 */
	public List<DailyReport> showDailyReportByTrainingId(Integer id){
		return dailyReportRepository.findByTrainingId(id);
	}
	
	/**
	 * 受講生IDで日報を検索.
	 * 
	 * @param id ID
	 * @return 日報
	 */
	public List<DailyReport> showDailyReportByStudentIdANDTrainingId(Integer studentId, Integer trainingId){
		return dailyReportRepository.findByStudentIdANDTrainingId(studentId, trainingId);
	}
	
	

}
