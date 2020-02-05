package com.example.demo.domain;

import java.util.List;

/**
 * 管理者を表すドメイン.
 * 
 * @author takahiro.suzuki
 *
 */
public class Admin {

	/** 管理者ID */
	private Integer id;
	/** 管理者名 */
	private String name;
	/** フリガナ */
	private String kana;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** すべての企業情報を見る権限 */
	private boolean canShowAllCompany;
	/** 企業情報 */
	private List<Company> companyList;

	public Admin() {
		super();
	}

	public Admin(Integer id, String name, String kana, String email, String password, boolean canShowAllCompany,
			List<Company> companyList) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.email = email;
		this.password = password;
		this.canShowAllCompany = canShowAllCompany;
		this.companyList = companyList;
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

	public boolean isCanShowAllCompany() {
		return canShowAllCompany;
	}

	public void setCanShowAllCompany(boolean canShowAllCompany) {
		this.canShowAllCompany = canShowAllCompany;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", kana=" + kana + ", email=" + email + ", password=" + password
				+ ", canShowAllCompany=" + canShowAllCompany + ", companyList=" + companyList + "]";
	}

}
