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
	
	@Autowired // 企業リポジトリ.
	private CompanyRepository companyRepository;
	
	@Autowired // 企業担当者のリポジトリ.
	private CompanyMemberRepository companyMemberRepository;
	
	
	@Autowired // パスワードをハッシュ化.
	private PasswordEncoder encoder;
	
	/**
	 * 企業情報を保存する.
	 * 
	 * @param form フォーム
	 */
	public void companySave(CompanyForm form) {
		Company company = new Company();
		BeanUtils.copyProperties(form, company);
		companyRepository.save(company);
	}
	
	/**
	 * すべての企業情報.
	 * 
	 * @return すべての企業情報
	 */
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
	
	/**
	 * 企業担当者情報の保存.
	 * 
	 * @param form フォーム
	 */
	public void companyMemberSave(CompanyMemberForm form) {
		CompanyMember member = new CompanyMember();
		BeanUtils.copyProperties(form, member);
		member.setPassword(encoder.encode(form.getPassword()));
		companyMemberRepository.save(member);
	}
	
	/**
	 * 企業担当者の削除.
	 * 
	 * @param id ID
	 */
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
	
	/**
	 * 企業名で企業検索.
	 * 
	 * @param name 企業名
	 * @return 検索された企業情報
	 */
	public Company showCompanyByName(String name) {
		return companyRepository.findByName(name);
	}
	
}
