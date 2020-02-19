package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Student;
import com.example.demo.domain.TrainingStudent;
import com.example.demo.form.StudentRegisterForm;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TrainingStudentRepository;

@Service
@Transactional
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
	 * @throws SQLException 
	 */
	public void studentSave(StudentRegisterForm form) throws SQLException {
		Student student = new Student();
		BeanUtils.copyProperties(form, student);
		student.setPassword(encoder.encode(student.getPassword()));
		studentRepository.save(student);
	}
	
	/**
	 * 運営管理者が受講生をインポートした際に利用する.
	 * 受講生と、研修テーブルをインサート
	 * @param student 受講生
	 * @throws SQLException 
	 */
	public void studentSaveByStudent(Student student, Integer trainingId) throws PSQLException, DuplicateKeyException {
		student.setPassword(encoder.encode(student.getPassword()));
		Integer studentId = studentRepository.save(student);
		// 研修と受講生の中間テーブルを作成
		TrainingStudent ts = new TrainingStudent();
		// 受講生のIDをセット
		ts.setStudentId(studentId);
		// 研修のIDをセット
		ts.setTrainingId(trainingId);
		tsRepository.save(ts);
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
