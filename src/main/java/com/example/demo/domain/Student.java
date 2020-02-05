package com.example.demo.domain;

import java.util.List;

/**
 * 受講者を表すドメイン.
 * 
 * @author takahiro.suzuki
 *
 */
public class Student {

	/** 受講者ID */
	private Integer id;
	/** 受講者名 */
	private String name;
	/** フリガナ */
	private String kana;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 企業Id */
	private Integer companyId;
	/** 企業情報 */
	private Company company;
	/***/
	private List<TrainingStudent> trainingList;

	public Student() {
		super();
	}

	public Student(Integer id, String name, String kana, String email, String password, Integer companyId,
			Company company, List<TrainingStudent> trainingList) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.email = email;
		this.password = password;
		this.companyId = companyId;
		this.company = company;
		this.trainingList = trainingList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<TrainingStudent> getTrainingList() {
		return trainingList;
	}

	public void setTrainingList(List<TrainingStudent> trainingList) {
		this.trainingList = trainingList;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", kana=" + kana + ", email=" + email + ", password=" + password
				+ ", companyId=" + companyId + ", company=" + company + ", trainingList=" + trainingList + "]";
	}

}
