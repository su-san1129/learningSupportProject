package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql("/DDL.sql")
@Sql("/testData.sql")
public class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository studentRepository;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		/*
		 * 企業10件 
		 *  - 3人ずつ受講生がいる。 
		 *  - 3人ずつ企業担当者がいる。
		 * 受講生 30人
		 * 研修 10件
		 *  - 研修ID1~3が10人ずつ生徒を保有している.
		 * */
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void 受講生の研修IDでの検索結果() {
		List<Student> student1 = studentRepository.findByTrainingId(1);
		List<Student> student2 = studentRepository.findByTrainingId(2);
		List<Student> student3 = studentRepository.findByTrainingId(3);
		List<Student> student4 = studentRepository.findByTrainingId(4);
		
		assertEquals(student1.get(0).getId(), 1);
		assertEquals(student1.get(9).getId(), 10); // 境界値
		
		assertEquals(student2.get(0).getId(), 1);
		assertEquals(student2.get(9).getId(), 10);
		
		assertEquals(student3.get(0).getId(), 1);
		assertEquals(student3.get(9).getId(), 10);
		
		assertEquals(student1.size(), 10);
		assertEquals(student2.size(), 10);
		assertEquals(student3.size(), 10); //10件存在
		assertEquals(student4.size(), 0); // 0件であるべき
	}
	
	@Test
	public void 企業ID研修IDで受講生を正しく検索できるかどうか() {
		List<Student> students = studentRepository.findByTrainingIdAndCompanyId(1, 1);
		assertEquals(students.size(), 1);
		List<Student> students2 = studentRepository.findByTrainingIdAndCompanyId(2, 1);
		assertEquals(students2.size(), 1);
		List<Student> students3 = studentRepository.findByTrainingIdAndCompanyId(3, 1);
		assertEquals(students3.size(), 1);
	}
	
	
	

}
