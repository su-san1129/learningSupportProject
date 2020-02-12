package com.example.demo.domain;

import java.time.LocalDate;

public class WeeklyReport {

	private Integer id;
	private LocalDate startDate;
	private String instructorName;
	private String content;
	private Integer trainingId;

	public WeeklyReport() {
		super();
	}

	public WeeklyReport(Integer id, LocalDate startDate, String instructorName, String content, Integer trainingId) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.instructorName = instructorName;
		this.content = content;
		this.trainingId = trainingId;
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

	@Override
	public String toString() {
		return "WeeklyReport [id=" + id + ", startDate=" + startDate + ", instructorName=" + instructorName
				+ ", content=" + content + ", trainingId=" + trainingId + "]";
	}

}
