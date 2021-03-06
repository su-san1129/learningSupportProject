package com.example.demo.domain;

import java.time.LocalDate;
import java.util.List;

public class WeeklyReport {

	private Integer id;
	private LocalDate startDate;
	private String instructorName;
	private String content;
	private Integer trainingId;
	private List<StudentImpression> studentImpressionList;

	public WeeklyReport() {
		super();
	}

	public WeeklyReport(Integer id, LocalDate startDate, String instructorName, String content, Integer trainingId,
			List<StudentImpression> studentImpressionList) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.instructorName = instructorName;
		this.content = content;
		this.trainingId = trainingId;
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

	public Integer getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}

	public List<StudentImpression> getStudentImpressionList() {
		return studentImpressionList;
	}

	public void setStudentImpressionList(List<StudentImpression> studentImpressionList) {
		this.studentImpressionList = studentImpressionList;
	}

	@Override
	public String toString() {
		return "WeeklyReport [id=" + id + ", startDate=" + startDate + ", instructorName=" + instructorName
				+ ", content=" + content + ", trainingId=" + trainingId + ", studentImpressionList="
				+ studentImpressionList + "]";
	}

}
