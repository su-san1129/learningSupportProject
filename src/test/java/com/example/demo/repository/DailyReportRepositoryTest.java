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

import com.example.demo.domain.DailyReport;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DailyReportRepositoryTest {
	
	@Autowired
	private DailyReportRepository dailyReportRepository;

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
	public void 日報の一件検索が正しく表示されるか() {
		List<DailyReport> report = dailyReportRepository.findByTrainingId(1);
		System.out.println(report);
		assertEquals(report.size(), 3);
		assertEquals(report.get(0).getStudent().getId(), 1);
		assertEquals(report.get(1).getStudent().getId(), 1);
		assertEquals(report.get(2).getStudent().getId(), 1);
	}

}
