package com.example.demo.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Instructor;
import com.example.demo.domain.StudentImpression;
import com.example.demo.domain.Training;
import com.example.demo.domain.TrainingStudent;
import com.example.demo.domain.WeeklyReport;

@Component
public class Database {
	
	public Training getTraining() {
		LocalDate startDate = LocalDate.of(2020, 1, 1);
		LocalDate endDate = LocalDate.of(2020, 3, 31);
		return new Training(1
				, startDate
				, endDate
				, "Java研修"
				, 1
				, 2
				, 3
				, 4
				, getInstractor()
				, getsubInstractor1()
				, getsubInstractor2()
				, getsubInstractor3()
				, getStudentList()
				, getWeeklyReport()
				);
	}
	public Training getTraining2() {
		LocalDate startDate = LocalDate.of(2020, 4, 1);
		LocalDate endDate = LocalDate.of(2020, 6, 30);
		return new Training(1
				, startDate
				, endDate
				, "Java研修"
				, 1
				, 2
				, 3
				, 4
				, getInstractor()
				, getsubInstractor1()
				, getsubInstractor2()
				, getsubInstractor3()
				, getStudentList()
				, getWeeklyReport()
				);
	}
	
	public List<Training> trainingList(){
		List<Training> list = new ArrayList<>();
		list.add(getTraining());
		list.add(getTraining2());
		return list;
	}
	
	public Instructor getInstractor() {
		return new Instructor(1, "伊賀将之", "イガマサユキ", "masayuki-iga@sample.com", "password", "なし", "伊賀さんです。");
	}
	public Instructor getsubInstractor1() {
		return new Instructor(2, "サブ講師１", "サブコウシ１", "sub1@sample.com", "password", "なし", "サブ講師１です。");
	}
	public Instructor getsubInstractor2() {
		return new Instructor(3, "サブ講師２", "サブコウシ２", "sub2@sample.com", "password", "なし", "サブ講師２です。");
	}
	public Instructor getsubInstractor3() {
		return new Instructor(4, "サブ講師３", "サブコウシ３", "sub3@sample.com", "password", "なし", "サブ講師３です。");
	}
	
	public List<TrainingStudent> getStudentList(){
		List<TrainingStudent> trainingList = new ArrayList<>();
		trainingList.add(new TrainingStudent(1,1));
		trainingList.add(new TrainingStudent(1,2));
		trainingList.add(new TrainingStudent(1,3));
		return trainingList;
	}
	
	public List<WeeklyReport> getWeeklyReport() {
		List<WeeklyReport> weeklyReportList = new ArrayList<>();
		weeklyReportList.add(new WeeklyReport(1, LocalDate.of(2020, 1, 1), "伊賀将之", "内容です", getStudentImpressionList()));
		weeklyReportList.add(new WeeklyReport(2, LocalDate.of(2020, 1, 8), "伊賀将之", "内容です", getStudentImpressionList2()));
		return weeklyReportList;
	}
	
	public List<StudentImpression> getStudentImpressionList(){
		List<StudentImpression> studentImpressionList = new ArrayList<>();
		studentImpressionList.add(new StudentImpression(1, 1, "鈴木貴大", "素晴らしい"));
		studentImpressionList.add(new StudentImpression(2, 1, "鈴木奈央", "不良です。"));
		return studentImpressionList;
	}
	public List<StudentImpression> getStudentImpressionList2(){
		List<StudentImpression> studentImpressionList = new ArrayList<>();
		studentImpressionList.add(new StudentImpression(3, 2, "鈴木貴大", "素晴らしい"));
		studentImpressionList.add(new StudentImpression(4, 2, "鈴木奈央", "不良です。"));
		return studentImpressionList;
	}
	
	
	

}
