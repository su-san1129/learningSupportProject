package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Company;
import com.example.demo.form.CompanyForm;
import com.example.demo.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public void companySave(CompanyForm form) {
		Company company = new Company();
		BeanUtils.copyProperties(form, company);
		companyRepository.save(company);
	}
	
	public List<Company> showAllCompany() {
		return companyRepository.findAll();
	}
	
	/**
	 * 企業の一件検索.
	 * 
	 * @param id ID 
	 * @return 検索された企業
	 */
	public Company showCompany(Integer id) {
		return companyRepository.load(id);
	}
	

}
