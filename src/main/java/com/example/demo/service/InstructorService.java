package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Instructor;
import com.example.demo.form.InstructorForm;
import com.example.demo.repository.InstructorRepositroy;

/**
 * 講師を扱うサービスクラス.
 * 
 * @author takahiro.suzuki
 *
 */
@Service
public class InstructorService {
	
	@Autowired
	private InstructorRepositroy instructorRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	/**
	 * 講師の全件検索.
	 * 
	 * @return 全講師情報
	 */
	public List<Instructor> showAllInstructor(){
		return instructorRepository.findAll();
	}
	
	public Instructor showInstructor(Integer id) {
		return instructorRepository.load(id);
	}
	
	/**
	 * 講師情報の保存.
	 * 
	 * @param form フォーム
	 */
	public void instructorSave(InstructorForm form) {
		Instructor instructor = new Instructor();
		form.setPassword(encoder.encode(form.getPassword()));
		BeanUtils.copyProperties(form, instructor);
		instructorRepository.save(instructor);
	}

}
