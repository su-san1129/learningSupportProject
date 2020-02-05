package com.example.demo.domain;

import java.time.LocalDate;
import java.util.List;

public class WeeklyReport {

	private Integer id;
	private LocalDate startDate;
	private String instructorName;
	private String content;
	private List<StudentImpression> studentImpressionList;

	public WeeklyReport() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public WeeklyReport(Integer id, LocalDate startDate, String instructorName, String content,
			List<StudentImpression> studentImpressionList) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.instructorName = instructorName;
		this.content = content;
		this.studentImpressionList = studentImpressionList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<StudentImpression> getStudentImpressionList() {
		return studentImpressionList;
	}

	public void setStudentImpressionList(List<StudentImpression> studentImpressionList) {
		this.studentImpressionList = studentImpressionList;
	}

}
