package com.example.demo.form;

import javax.validation.constraints.NotBlank;

public class InstructorForm {

	/** ID */
	private Integer id;
	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/** かな */
	@NotBlank(message = "フリガナを入力してください")
	private String kana;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;
	/** パスワード */
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力してください")
	private String passwordConfirm;
	/** 所属 */
	private String affiliation;
	/** 備考 */
	private String remarks;

	public InstructorForm() {
		super();
	}

	public InstructorForm(Integer id, String name, String kana, String email, String password, String passwordConfirm,
			String affiliation, String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String password) {
		this.passwordConfirm = password;
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
		return "InstructorForm [id=" + id + ", name=" + name + ", kana=" + kana + ", email=" + email + ", password="
				+ password +", passwordConfirm=" + passwordConfirm + ", affiliation=" + affiliation + ", remarks=" + remarks + "]";
	}

}
