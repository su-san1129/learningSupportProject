package com.example.demo.form;

public class AdminResponsibleCompanyForm {

	private Integer id;
	private Integer adminId;
	private Integer companyId;

	public AdminResponsibleCompanyForm() {
		super();
	}

	public AdminResponsibleCompanyForm(Integer id, Integer adminId, Integer companyId) {
		super();
		this.id = id;
		this.adminId = adminId;
		this.companyId = companyId;
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

	@Override
	public String toString() {
		return "AdminResponsibleCompanyForm [id=" + id + ", adminId=" + adminId + ", companyId=" + companyId + "]";
	}

}
