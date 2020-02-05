package com.example.demo.form;

import java.sql.Date;
import java.time.LocalDate;

public class DailyReportForm {

	/** ID */
	private Integer id;
	/** 日付 */
	private Date date;
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
	private Integer aboutInstructor;
	/** 質問 */
	private String question;

	public DailyReportForm() {
		super();
	}

	public DailyReportForm(Integer id, Date date, Integer trainingId, Integer studentId, String context,
			Integer intelligibility, String detailIntelligibillity, Integer aboutInstructor, String question) {
		super();
		this.id = id;
		this.date = date;
		this.trainingId = trainingId;
		this.studentId = studentId;
		this.context = context;
		this.intelligibility = intelligibility;
		this.detailIntelligibillity = detailIntelligibillity;
		this.aboutInstructor = aboutInstructor;
		this.question = question;
	}

	public LocalDate toLocalDate() {
		return this.date.toLocalDate();
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public Integer getAboutInstructor() {
		return aboutInstructor;
	}

	public void setAboutInstructor(Integer aboutInstructor) {
		this.aboutInstructor = aboutInstructor;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "DailyReportForm [id=" + id + ", date=" + date + ", trainingId=" + trainingId + ", studentId="
				+ studentId + ", context=" + context + ", intelligibility=" + intelligibility
				+ ", detailIntelligibillity=" + detailIntelligibillity + ", aboutInstructor=" + aboutInstructor
				+ ", question=" + question + "]";
	}

}
