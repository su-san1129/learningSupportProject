package com.example.demo.domain;

import java.time.LocalDate;
import java.util.List;

public class Training {

	private Integer id;
	private LocalDate startDate;
	private LocalDate endDate;
	private String name;
	private Integer subInstructorId1;
	private Integer subInstructorId2;
	private Integer subInstructorId3;
	private Instructor instructor;
	private Instructor subInstructor1;
	private Instructor subInstructor2;
	private Instructor subInstructor3;
	private List<TrainingStudent> studentList;
	private List<WeeklyReport> weeklyReport;

	public Training() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Training(Integer id, LocalDate startDate, LocalDate endDate, String name, Integer subInstructorId1,
			Integer subInstructorId2, Integer subInstructorId3, Instructor instructor, Instructor subInstructor1,
			Instructor subInstructor2, Instructor subInstructor3, List<TrainingStudent> studentList,
			List<WeeklyReport> weeklyReport) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.name = name;
		this.subInstructorId1 = subInstructorId1;
		this.subInstructorId2 = subInstructorId2;
		this.subInstructorId3 = subInstructorId3;
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

	public Integer getSubInstructorId1() {
		return subInstructorId1;
	}

	public void setSubInstructorId1(Integer subInstructorId1) {
		this.subInstructorId1 = subInstructorId1;
	}

	public Integer getSubInstructorId2() {
		return subInstructorId2;
	}

	public void setSubInstructorId2(Integer subInstructorId2) {
		this.subInstructorId2 = subInstructorId2;
	}

	public Integer getSubInstructorId3() {
		return subInstructorId3;
	}

	public void setSubInstructorId3(Integer subInstructorId3) {
		this.subInstructorId3 = subInstructorId3;
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
				+ ", subInstructorId1=" + subInstructorId1 + ", subInstructorId2=" + subInstructorId2
				+ ", subInstructorId3=" + subInstructorId3 + ", instructor=" + instructor + ", subInstructor1="
				+ subInstructor1 + ", subInstructor2=" + subInstructor2 + ", subInstructor3=" + subInstructor3
				+ ", studentList=" + studentList + ", weeklyReport=" + weeklyReport + "]";
	}

}
