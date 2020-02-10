package com.example.demo.form;

import javax.validation.constraints.NotBlank;

public class CompanyMemberForm {

	/** 従業員ID */
	private Integer id;
	/** 名前 */
	@NotBlank(message="企業担当者名を入力してください")
	private String name;
	/** フリガナ */
	@NotBlank(message="企業担当者名(フリガナ)を入力してください")
	private String kana;
	/** メールアドレス */
	@NotBlank(message="メールアドレスを入力してください")
	private String email;
	/** パスワード */
	@NotBlank(message="パスワードを入力してください")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message="確認用パスワードを入力してください")
	private String passwordConfirm;
	/** 企業ID */
	private Integer companyId;

	public CompanyMemberForm() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public CompanyMemberForm(Integer id, String name, String kana, String email, String password,
			String passwordConfirm, Integer companyId) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.companyId = companyId;
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

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "CompanyMemberForm [id=" + id + ", name=" + name + ", kana=" + kana + ", email=" + email + ", password="
				+ password + ", passwordConfirm=" + passwordConfirm + ", companyId=" + companyId + "]";
	}

}
