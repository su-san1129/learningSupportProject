package com.example.demo.repository;

import static org.junit.Assert.*;

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

import com.example.demo.domain.WeeklyReport;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WeeklyReportRepositoryTest {
	
	@Autowired
	private WeeklyReportRepository weeklyReportRepository;

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
	public void 週報を研修IDで検索するテスト() {
		List<WeeklyReport> weekly = weeklyReportRepository.findByTrainingId(1);
		List<WeeklyReport> weekly2 = weeklyReportRepository.findByTrainingId(2);
		List<WeeklyReport> weekly3 = weeklyReportRepository.findByTrainingId(3);
		assertEquals(weekly.size(), 3);
		assertEquals(weekly2.size(), 3);
		assertEquals(weekly3.size(), 3);
	}

}
