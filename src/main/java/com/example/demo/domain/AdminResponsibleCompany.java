package com.example.demo.domain;


/**
 * 運営者が担当する企業を表すドメイン.
 * 
 * @author takahiro.suzuki
 *
 */
public class AdminResponsibleCompany {

	/** ID */
	private Integer id;
	/** 運営管理者ID */
	private Integer adminId;
	/** 企業ID */
	private Integer companyId;
	/** 企業リスト */
	private Company company;

	public AdminResponsibleCompany() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public AdminResponsibleCompany(Integer id, Integer adminId, Integer companyId, Company company) {
		super();
		this.id = id;
		this.adminId = adminId;
		this.companyId = companyId;
		this.company = company;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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

	@Override
	public String toString() {
		return "AdminResponsibleCompany [id=" + id + ", adminId=" + adminId + ", companyId=" + companyId + ", company="
				+ company + "]";
	}

}
