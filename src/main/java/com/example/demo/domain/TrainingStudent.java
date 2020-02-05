package com.example.demo.domain;

public class TrainingStudent {

	private Integer trainingId;
	private Integer studentId;

	public TrainingStudent() {
		super();
	}

	public TrainingStudent(Integer trainingId, Integer studentId) {
		super();
		this.trainingId = trainingId;
		this.studentId = studentId;
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
		return "TrainingStudent [trainingId=" + trainingId + ", studentId=" + studentId + "]";
	}

}
