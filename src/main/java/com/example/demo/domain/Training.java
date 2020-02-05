package com.example.demo.domain;

import java.time.LocalDate;
import java.util.List;

public class Training {

	private Integer id;
	private LocalDate startDate;
	private LocalDate endDate;
	private String name;
	private Integer instructorId;
	private Integer subInstructor1Id;
	private Integer subInstructor2Id;
	private Integer subInstructor3Id;
	private Instructor instructor;
	private Instructor subInstructor1;
	private Instructor subInstructor2;
	private Instructor subInstructor3;
	private List<TrainingStudent> studentList;
	private List<WeeklyReport> weeklyReport;

	public Training() {
		super();
	}

	public Training(Integer id, LocalDate startDate, LocalDate endDate, String name, Integer instructorId,
			Integer subInstructor1Id, Integer subInstructor2Id, Integer subInstructor3Id, Instructor instructor,
			Instructor subInstructor1, Instructor subInstructor2, Instructor subInstructor3,
			List<TrainingStudent> studentList, List<WeeklyReport> weeklyReport) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.name = name;
		this.instructorId = instructorId;
		this.subInstructor1Id = subInstructor1Id;
		this.subInstructor2Id = subInstructor2Id;
		this.subInstructor3Id = subInstructor3Id;
		this.instructor = instructor;
		this.subInstructor1 = subInstructor1;
		this.subInstructor2 = subInstructor2;
		this.subInstructor3 = subInstructor3;
		this.studentList = studentList;
		this.weeklyReport = weeklyReport;
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

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}

	public Integer getSubInstructor1Id() {
		return subInstructor1Id;
	}

	public void setSubInstructor1Id(Integer subInstructor1Id) {
		this.subInstructor1Id = subInstructor1Id;
	}

	public Integer getSubInstructor2Id() {
		return subInstructor2Id;
	}

	public void setSubInstructor2Id(Integer subInstructor2Id) {
		this.subInstructor2Id = subInstructor2Id;
	}

	public Integer getSubInstructor3Id() {
		return subInstructor3Id;
	}

	public void setSubInstructor3Id(Integer subInstructor3Id) {
		this.subInstructor3Id = subInstructor3Id;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Instructor getSubInstructor1() {
		return subInstructor1;
	}

	public void setSubInstructor1(Instructor subInstructor1) {
		this.subInstructor1 = subInstructor1;
	}

	public Instructor getSubInstructor2() {
		return subInstructor2;
	}

	public void setSubInstructor2(Instructor subInstructor2) {
		this.subInstructor2 = subInstructor2;
	}

	public Instructor getSubInstructor3() {
		return subInstructor3;
	}

	public void setSubInstructor3(Instructor subInstructor3) {
		this.subInstructor3 = subInstructor3;
	}

	public List<TrainingStudent> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<TrainingStudent> studentList) {
		this.studentList = studentList;
	}

	public List<WeeklyReport> getWeeklyReport() {
		return weeklyReport;
	}

	public void setWeeklyReport(List<WeeklyReport> weeklyReport) {
		this.weeklyReport = weeklyReport;
	}

	@Override
	public String toString() {
		return "Training [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", name=" + name
				+ ", instructorId=" + instructorId + ", subInstructor1Id=" + subInstructor1Id + ", subInstructor2Id="
				+ subInstructor2Id + ", subInstructor3Id=" + subInstructor3Id + ", instructor=" + instructor
				+ ", subInstructor1=" + subInstructor1 + ", subInstructor2=" + subInstructor2 + ", subInstructor3="
				+ subInstructor3 + ", studentList=" + studentList + ", weeklyReport=" + weeklyReport + "]";
	}

}
