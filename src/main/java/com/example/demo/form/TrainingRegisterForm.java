package com.example.demo.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.domain.Instructor;
import com.example.demo.domain.TrainingStudent;
import com.example.demo.domain.WeeklyReport;

public class TrainingRegisterForm {

	/** ID */
	private Integer id;
	/** 開始日 */
	@NotBlank(message="開始日を入力してください")
	private String startDate;
	/** 終了日 */
	@NotBlank(message="終了日を入力してください")
	private String endDate;
	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/** 講師ID */
	@NotNull(message = "講師を選択してください")
	private Integer instructorId;
	/** サブ講師1ID */
	private Integer subInstructor1Id;
	/** サブ講師2ID */
	private Integer subInstructor2Id;
	/** サブ講師3ID */
	private Integer subInstructor3Id;
	/** 講師 */
	private Instructor instructor;
	/** サブ講師1 */
	private Instructor subInstructor1;
	/** サブ講師2 */
	private Instructor subInstructor2;
	/** サブ講師3 */
	private Instructor subInstructor3;
	/** 受講生リスト */
	private List<TrainingStudent> studentList;
	/** 週報リスト */
	private List<WeeklyReport> weeklyReport;

	public TrainingRegisterForm() {
		super();
	}
	

	public TrainingRegisterForm(Integer id, String startDate, String endDate, String name, Integer instructorId,
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
	
	/** 開始日をLocalDate型に変換 */
	public LocalDate toLocalDateStartDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(this.getStartDate(), formatter);
	}
	/** 終了日をLocalDate型に変換 */
	public LocalDate toLocalDateEndDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(this.getEndDate(), formatter);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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
		return "TrainingRegisterForm [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", name=" + name
				+ ", instructorId=" + instructorId + ", subInstructor1Id=" + subInstructor1Id + ", subInstructor2Id="
				+ subInstructor2Id + ", subInstructor3Id=" + subInstructor3Id + ", instructor=" + instructor
				+ ", subInstructor1=" + subInstructor1 + ", subInstructor2=" + subInstructor2 + ", subInstructor3="
				+ subInstructor3 + ", studentList=" + studentList + ", weeklyReport=" + weeklyReport + "]";
	}

}
