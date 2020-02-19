package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Company;
import com.example.demo.domain.CompanyMember;
import com.example.demo.domain.Student;
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
	
	@Autowired
	private HttpSession session;
	
	
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
	
	/**
	 * CSVの読み込みと受講生クラスへのマッピングを行う.
	 * 
	 * @param file 読み込むファイル.
	 * @param model モデル
	 * @return 受講生リスト
	 */
	public List<Student> csvInputAndMappingStudent(MultipartFile file, Model model) {
		List<Student> students = new ArrayList<>();
		int tryCount = 2;
		try {
			// ファイルの読み込み
			InputStream fileInput = file.getInputStream();
			// ファイルをリーダーに変換
			Reader reader = new InputStreamReader(fileInput);
			BufferedReader br = new BufferedReader(reader);
			// 一行目
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				// 今回はデータ構造が決まっているため、適当なバリデーション
				if (data.length < 4 || 4 < data.length) {
					throw new IOException();
				}
				// 受講生名
				String name = data[0];
				// 受講生メールアドレス
				String email = data[1];
				// 受講生の所属する企業名
				String companyName = data[2];
				// パスワード
				String password = data[3];
				Company company = showCompanyByName(companyName);
				if (company == null) {
					// 見つからない場合の例外処理.
					throw new SQLException();
				}
				/** 本当は、パスワードを自動生成して、メールを飛ばして変更させるプロセスのほうがいい */
				Student student = new Student(null, name, null, email, password, company.getId(), company, null);
				students.add(student);
				session.setAttribute("students", students);
				tryCount++;
			}
		} catch (IOException e) {
			model.addAttribute("fileInputError", "ファイルの読み込みに失敗しました。");
			e.printStackTrace();
		} catch (SQLException e) {
			model.addAttribute("noCompanyError", tryCount + "行目:企業が存在しませんでした");
			students = null;
			e.printStackTrace();
		}
		return students;
	}
	
}
