package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Student;
import com.example.demo.domain.TrainingStudent;
import com.example.demo.form.StudentRegisterForm;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TrainingStudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TrainingStudentRepository tsRepository;
	
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
	 * 運営管理者が受講生をインポートした際に利用する.
	 * 受講生と、研修テーブルをインサート
	 * @param student 受講生
	 */
	public void studentSaveByStudent(Student student, Integer trainingId) {
		student.setPassword(encoder.encode(student.getPassword()));
		// 研修と受講生の中間テーブルを作成
		TrainingStudent ts = new TrainingStudent();
		// 受講生のIDをセット
		ts.setStudentId(student.getId());
		// 研修のIDをセット
		ts.setTrainingId(trainingId);
		tsRepository.save(ts);
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
