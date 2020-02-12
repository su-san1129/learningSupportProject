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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Sql("/DDL.sql")
	@Sql("/testData.sql")
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
	
	
	

}
