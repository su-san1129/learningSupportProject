package com.example.demo.form;

public class CompanyForm {

	/** 企業ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** フリガナ */
	private String kana;
	/** 備考 */
	private String remarks;

	public CompanyForm() {
		super();
	}

	public CompanyForm(Integer id, String name, String kana, String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "CompanyForm [id=" + id + ", name=" + name + ", kana=" + kana + ", remarks=" + remarks + "]";
	}

}
