package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Admin;
import com.example.demo.form.AdminRegisterForm;
import com.example.demo.repository.AdminRepository;

/**
 * 管理者のサービスクラス.
 * 
 * @author takahiro.suzuki
 *
 */
@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminReposiory;
	
	@Autowired
	private PasswordEncoder encoder;
	
	/**
	 * メールアドレスで検索された管理者.
	 * 
	 * @param email 管理者のメールアドレス
	 * @return メールアドレスに該当する管理者
	 */
	public Admin showAdmin(String email) {
		return adminReposiory.findByEmail(email);
	}
	
	/**
	 * 管理者の登録を行う.
	 * 
	 * @param form フォーム
	 */
	public void adminSave(AdminRegisterForm form) {
		Admin admin = new Admin();
		BeanUtils.copyProperties(form, admin);
		admin.setPassword(encoder.encode(admin.getPassword()));
		adminReposiory.save(admin);
	}
	
	/**
	 * 全管理者情報.
	 * 
	 * @return 全管理者
	 */
	public List<Admin> showAllAdmins(){
		return adminReposiory.findAll();
	}

}
