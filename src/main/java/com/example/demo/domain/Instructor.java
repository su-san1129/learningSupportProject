package com.example.demo.domain;

public class Instructor {

	private Integer id;
	private String name;
	private String kana;
	private String email;
	private String password;
	private String affiliation;
	private String remarks;

	public Instructor() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Instructor(Integer id, String name, String kana, String email, String password, String affiliation,
			String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.email = email;
		this.password = password;
		this.affiliation = affiliation;
		this.remarks = remarks;
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

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", name=" + name + ", kana=" + kana + ", email=" + email + ", password="
				+ password + ", affiliation=" + affiliation + ", remarks=" + remarks + "]";
	}

}
