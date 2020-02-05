package com.example.demo.domain;

import java.util.List;

/**
 * 企業を表すドメイン.
 * 
 * @author takahiro.suzuki
 *
 */
public class Company {

	/** 企業ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** フリガナ */
	private String kana;
	/** 備考 */
	private String remarks;
	/** 企業のメンバー一覧 */
	private List<CompanyMember> companyMemberList;

	public Company() {
		super();
	}

	public Company(Integer id, String name, String kana, String remarks, List<CompanyMember> companyMemberList) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.remarks = remarks;
		this.companyMemberList = companyMemberList;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<CompanyMember> getCompanyMemberList() {
		return companyMemberList;
	}

	public void setCompanyMemberList(List<CompanyMember> companyMemberList) {
		this.companyMemberList = companyMemberList;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", kana=" + kana + ", remarks=" + remarks
				+ ", companyMemberList=" + companyMemberList + "]";
	}

}
