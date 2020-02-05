package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Student;
import com.example.demo.form.StudentRegisterForm;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public Student showStudent(String email) {
		return studentRepository.findByEmail(email);
	}
	
	/**
	 * 管理者の登録を行う.
	 * 
	 * @param form フォーム
	 */
	public void studentSave(StudentRegisterForm form) {
		Student student = new Student();
		BeanUtils.copyProperties(form, student);
		student.setPassword(encoder.encode(student.getPassword()));
		studentRepository.save(student);
	}
	
	/**
	 * 全管理者情報.
	 * 
	 * @return 全管理者
	 */
	public List<Student> showAllStudents(){
		return studentRepository.findAll();
	}

}
