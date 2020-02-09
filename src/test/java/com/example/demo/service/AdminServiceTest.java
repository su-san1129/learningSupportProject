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

import com.example.demo.domain.Admin;
import com.example.demo.domain.Company;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminServiceTest {

	@Autowired
	private AdminService adminService;

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
	public void testShowAdminByEmail() {
		fail("まだ実装されていません");
	}

	@Test
	public void testAdminSave() {
		fail("まだ実装されていません");
	}

	@Test
	@Sql("/DDL.sql")
	@Sql("/testData.sql")
	public void testShowAllAdmins() {
		List<Admin> admins = adminService.showAllAdmins();
		assertEquals(admins.size(), 10);
		assertEquals(admins.get(0).getName(), "Madelyn");
	}

	@Test
	@Sql("/DDL.sql")
	@Sql("/testData.sql")
	public void 運営者担当企業込みの全件検索で担当企業は三件であるべき() {
		List<Admin> admins = adminService.showAllAdminsIncludeResponsibleCompany();
		assertEquals(admins.size(), 10);
		
		// 担当企業は3件であるべき
		assertEquals(admins.get(0).getCompanyList().size(), 3);
		assertEquals(admins.get(1).getCompanyList().size(), 3);
		assertEquals(admins.get(2).getCompanyList().size(), 3);
		assertEquals(admins.get(5).getCompanyList().size(), 3);
		
		// 担当企業の企業担当者は3件であるべき
		assertEquals(admins.get(0).getCompanyList().get(0).getCompanyMemberList().size(), 3);
		assertEquals(admins.get(0).getCompanyList().get(1).getCompanyMemberList().size(), 3);
		assertEquals(admins.get(0).getCompanyList().get(2).getCompanyMemberList().size(), 3);
		
		assertEquals(admins.get(1).getCompanyList().get(0).getCompanyMemberList().size(), 3);
		assertEquals(admins.get(1).getCompanyList().get(1).getCompanyMemberList().size(), 3);
		assertEquals(admins.get(1).getCompanyList().get(2).getCompanyMemberList().size(), 3);
		
		assertEquals(admins.get(2).getCompanyList().get(0).getCompanyMemberList().size(), 3);
		assertEquals(admins.get(2).getCompanyList().get(1).getCompanyMemberList().size(), 3);
		assertEquals(admins.get(2).getCompanyList().get(2).getCompanyMemberList().size(), 3);
		
		assertEquals(admins.get(5).getCompanyList().get(0).getCompanyMemberList().size(), 3);
		assertEquals(admins.get(5).getCompanyList().get(1).getCompanyMemberList().size(), 3);
		assertEquals(admins.get(5).getCompanyList().get(2).getCompanyMemberList().size(), 3);
	}

	@Test
	@Sql("/DDL.sql")
	@Sql("/testData.sql")
	public void 運営管理者の一件検索で正しい情報が表示されるべき() {
		Admin admin = adminService.showAdmin(1);
		assertEquals(admin.getId(), 1);
		assertEquals(admin.getName(), "Madelyn");
		Admin admin2 = adminService.showAdmin(10);
		assertEquals(admin2.getId(), 10);
		assertEquals(admin2.getName(), "Dexter");
	}

	@Test
	@Sql("/DDL.sql")
	@Sql("/testData.sql")
	public void 企業の全件検索は10件表示されるべき() {
		List<Company> companies = adminService.showCompanies();
		assertEquals(companies.size(), 10);
	}

	@Test
	public void testArcSave() {
		fail("まだ実装されていません");
	}

	@Test
	public void testArcDelete() {
		fail("まだ実装されていません");
	}

}
