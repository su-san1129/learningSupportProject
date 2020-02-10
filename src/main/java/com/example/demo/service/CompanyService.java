package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Company;
import com.example.demo.domain.CompanyMember;
import com.example.demo.form.CompanyForm;
import com.example.demo.form.CompanyMemberForm;
import com.example.demo.repository.CompanyMemberRepository;
import com.example.demo.repository.CompanyRepository;

/**
 * 企業情報を扱うサービスクラス.
 * 
 * @author takahiro.suzuki
 *
 */
@Service
@Transactional
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyMemberRepository companyMemberRepository;
	
	
	@Autowired
	private PasswordEncoder encoder;
	
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
	
	public void companyMemberSave(CompanyMemberForm form) {
		CompanyMember member = new CompanyMember();
		BeanUtils.copyProperties(form, member);
		member.setPassword(encoder.encode(form.getPassword()));
		companyMemberRepository.save(member);
	}
	
	public void deleteMember(Integer id) {
		companyMemberRepository.deleteById(id);
	}
	
	/**
	 * 企業担当者の一件検索.
	 * 
	 * @param id 企業担当者のID
	 * @return 検索された担当者
	 */
	public CompanyMember showCompanyMember(Integer id) {
		return companyMemberRepository.load(id);
	}
	
}
