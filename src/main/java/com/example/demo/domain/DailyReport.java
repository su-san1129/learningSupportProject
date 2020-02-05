package com.example.demo.domain;

import java.time.LocalDate;

public class DailyReport {

	/** ID */
	private Integer id;
	/** 日付 */
	private LocalDate date;
	/** 研修ID */
	private Integer trainingId;
	/** 受講生ID */
	private Integer studentId;
	/** 内容 */
	private String context;
	/** 理解度 */
	private Integer intelligibility;
	/** 理解度詳細 */
	private String detailIntelligibillity;
	/** 講師について */
	private Integer absoutInstructor;
	/** 質問 */
	private String question;
	/** 研修 */
	private Training training;
	/** 受講生 */
	private Student student;

	public DailyReport() {
		super();
	}

	public DailyReport(Integer id, LocalDate date, Integer trainingId, Integer studentId, String context,
			Integer intelligibility, String detailIntelligibillity, Integer absoutInstructor, String question,
			Training training, Student student) {
		super();
		this.id = id;
		this.date = date;
		this.trainingId = trainingId;
		this.studentId = studentId;
		this.context = context;
		this.intelligibility = intelligibility;
		this.detailIntelligibillity = detailIntelligibillity;
		this.absoutInstructor = absoutInstructor;
		this.question = question;
		this.training = training;
		this.student = student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getIntelligibility() {
		return intelligibility;
	}

	public void setIntelligibility(Integer intelligibility) {
		this.intelligibility = intelligibility;
	}

	public String getDetailIntelligibillity() {
		return detailIntelligibillity;
	}

	public void setDetailIntelligibillity(String detailIntelligibillity) {
		this.detailIntelligibillity = detailIntelligibillity;
	}

	public Integer getAbsoutInstructor() {
		return absoutInstructor;
	}

	public void setAbsoutInstructor(Integer absoutInstructor) {
		this.absoutInstructor = absoutInstructor;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "DailyReport [id=" + id + ", date=" + date + ", trainingId=" + trainingId + ", studentId=" + studentId
				+ ", context=" + context + ", intelligibility=" + intelligibility + ", detailIntelligibillity="
				+ detailIntelligibillity + ", absoutInstructor=" + absoutInstructor + ", question=" + question
				+ ", training=" + training + ", student=" + student + "]";
	}

}
