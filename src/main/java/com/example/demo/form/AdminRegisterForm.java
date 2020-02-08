package com.example.demo.form;

import java.util.List;

import javax.validation.constraints.NotBlank;


/**
 * 管理者の登録を行うフォーム.
 * 
 * @author takahiro.suzuki
 *
 */
public class AdminRegisterForm {

	/** 管理者ID */
	private Integer id;
	/** 管理者名 */
	@NotBlank(message = "名前を入力してください。")
	private String name;
	/** フリガナ */
	@NotBlank(message = "フリガナを入力してください。")
	private String kana;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください。")
	private String email;
	/** パスワード */
	@NotBlank(message = "パスワードを入力してください。")
	private String password;
	@NotBlank(message = "確認用パスワードを入力してください。")
	private String passwordConfirm;
	/** すべての企業情報を見る権限 */
	private boolean canShowAllCompany;
	/** 企業情報 */
	private List<Integer> companyIdList;

	public AdminRegisterForm() {
		super();
	}

	public AdminRegisterForm(Integer id, String name, String kana, String email, String password,
			String passwordConfirm, boolean canShowAllCompany, List<Integer> companyIdList) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.canShowAllCompany = canShowAllCompany;
		this.companyIdList = companyIdList;
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

	public boolean isCanShowAllCompany() {
		return canShowAllCompany;
	}

	public void setCanShowAllCompany(boolean canShowAllCompany) {
		this.canShowAllCompany = canShowAllCompany;
	}

	public List<Integer> getCompanyIdList() {
		return companyIdList;
	}

	public void setCompanyIdList(List<Integer> companyIdList) {
		this.companyIdList = companyIdList;
	}

	@Override
	public String toString() {
		return "AdminRegisterForm [id=" + id + ", name=" + name + ", kana=" + kana + ", email=" + email + ", password="
				+ password + ", passwordConfirm=" + passwordConfirm + ", canShowAllCompany=" + canShowAllCompany
				+ ", companyIdList=" + companyIdList + "]";
	}

}
