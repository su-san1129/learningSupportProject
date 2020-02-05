package com.example.demo.domain;

public class CompanyMember {

	/** 従業員ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** フリガナ */
	private String kana;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 企業ID */
	private Integer companyId;

	public CompanyMember() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public CompanyMember(Integer id, String name, String kana, String email, String password, Integer companyId) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.email = email;
		this.password = password;
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "CompanyMember [id=" + id + ", name=" + name + ", kana=" + kana + ", email=" + email + ", password="
				+ password + ", companyId=" + companyId + "]";
	}

}
