package com.example.demo.form;

import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotBlank(message = "内容を入力してください")
	private String content;
	/** 理解度 */
	@NotNull(message = "理解度を入力してください")
	private Integer intelligibility;
	/** 理解度詳細 */
	@NotBlank(message = "理解度詳細を入力してください")
	private String detailIntelligibility;
	/** 講師について */
	private Integer aboutInstructor;
	/** 質問 */
	private String question;

	public DailyReportForm() {
		super();
	}

	public DailyReportForm(Integer id, Date date, Integer trainingId, Integer studentId,
			@NotBlank(message = "内容を入力してください") String content,
			@NotNull(message = "理解度を入力してください") Integer intelligibility,
			@NotBlank(message = "理解度詳細を入力してください") String detailIntelligibility, Integer aboutInstructor,
			String question) {
		super();
		this.id = id;
		this.date = date;
		this.trainingId = trainingId;
		this.studentId = studentId;
		this.content = content;
		this.intelligibility = intelligibility;
		this.detailIntelligibility = detailIntelligibility;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIntelligibility() {
		return intelligibility;
	}

	public void setIntelligibility(Integer intelligibility) {
		this.intelligibility = intelligibility;
	}

	public String getDetailIntelligibility() {
		return detailIntelligibility;
	}

	public void setDetailIntelligibility(String detailIntelligibility) {
		this.detailIntelligibility = detailIntelligibility;
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
				+ studentId + ", content=" + content + ", intelligibility=" + intelligibility
				+ ", detailIntelligibility=" + detailIntelligibility + ", aboutInstructor=" + aboutInstructor
				+ ", question=" + question + "]";
	}

}
