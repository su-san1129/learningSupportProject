package com.example.demo.domain;

public class TrainingStudent {

	private Integer id;
	private Integer trainingId;
	private Integer studentId;

	public TrainingStudent() {
		super();
	}

	public TrainingStudent(Integer id, Integer trainingId, Integer studentId) {
		super();
		this.id = id;
		this.trainingId = trainingId;
		this.studentId = studentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "TrainingStudent [id=" + id + ", trainingId=" + trainingId + ", studentId=" + studentId + "]";
	}

}
