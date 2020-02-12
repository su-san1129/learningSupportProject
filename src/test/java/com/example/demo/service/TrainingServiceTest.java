package com.example.demo.service;

import static org.junit.Assert.fail;
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

import com.example.demo.domain.Training;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TrainingServiceTest {
	
	@Autowired
	private TrainingService trainingService;

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
	public void 研修の全件検索テスト() {
		List<Training> trainingList = trainingService.showAllTraining();
		assertEquals(trainingList.size(), 10);
		
		assertEquals(trainingList.get(0).getId(), 1);
		assertEquals(trainingList.get(0).getStudentList().size(), 10);
		assertEquals(trainingList.get(0).getWeeklyReport().size(), 3);
		
		assertEquals(trainingList.get(2).getId(), 3);
		assertEquals(trainingList.get(2).getStudentList().size(), 10);
		assertEquals(trainingList.get(2).getWeeklyReport().size(), 3);
		
		assertEquals(trainingList.get(3).getId(), 4);
		assertEquals(trainingList.get(3).getStudentList().size(), 0);
		assertEquals(trainingList.get(3).getWeeklyReport().size(), 3);
		
		assertEquals(trainingList.get(9).getId(), 10);
		assertEquals(trainingList.get(9).getStudentList().size(), 0);
		assertEquals(trainingList.get(9).getWeeklyReport().size(), 3);
		
	}

	@Test
	public void testShowAllTraining() {
		fail("まだ実装されていません");
	}

}
