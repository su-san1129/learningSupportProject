package com.example.demo.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {

	private MockMvc mvc;
	
	@InjectMocks
	private StudentController studentController;

	@Autowired
	WebApplicationContext context;
	
//	Authentication authentication;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		/* 
		 * webAppContextSetupがSpringBootの設定ファイルを読み込む
		 * apply()でspringSecurityを許可する設定を入れなければ有効にならない
		 */
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
//		authentication = new TestingAuthenticationToken("user", "password",
//				AuthorityUtils.createAuthorityList("ROLE_STUDENT"));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void ログインページの表示() throws Exception {
		mvc.perform(get("/students/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("student/student_login"));
	}
	
	@Test
	public void 新規登録ページの表示() throws Exception{
		mvc.perform(get("/students/register"))
			.andExpect(status().isOk())
			.andExpect(view().name("student/student_register"));
	}
	
	@Test // パスワードの不一致。エラーを返す。
	public void 受講生のパスワードバリデーションチェック() throws Exception{
		mvc.perform(post("/students/register/student")
				.param("name", "鈴木貴大")
				.param("kana", "スズキタカヒロ")
				.param("email", "sample@sample.com")
				.param("password", "password")
				.param("passwordConfirm", "passwordConfirm")
				.param("companyId", "1")
				.with(SecurityMockMvcRequestPostProcessors.csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
	}
	
	@Test
	@WithMockUser
	public void 登録確認ページにGETはエラーを返すべき() throws Exception{
		mvc.perform(get("/students/register/confirm"))
			.andExpect(status().is4xxClientError());
	}
	@Test
	public void 登録確認ページのPOST処理() throws Exception{
		mvc.perform(post("/students/register/confirm")
				.param("id", "1")
				.param("date", "2020-10-23")
				.param("trainingId", "1")
				.param("studentId", "1")
				.param("content", "hello")
				.param("intelligibility", "1")
				.param("detailIntelligibility", "テストが難しかった")
				.param("aboutInstructor", "1")
				.param("question", "なし").with(SecurityMockMvcRequestPostProcessors.csrf()))
			.andExpect(model().hasNoErrors())
			.andExpect(status().isOk())
			.andExpect(view().name("student/student_register_confirm"));
	}
	@Test
	public void 登録ページのPOSTのNullチェック() throws Exception{
		mvc.perform(post("/students/register/student")
				.param("id", "1")
				.param("studentId", "a")
				.param("aboutInstructor", "1")
				.param("question", "なし").with(SecurityMockMvcRequestPostProcessors.csrf()))
			.andExpect(model().hasErrors())
			.andExpect(status().isOk())
			.andExpect(view().name("student/student_register"));
	}
	

	@Test
	public void 日報に値をセットしていないのでエラーになるべき() throws Exception {
		//DailyReportForm form = database.getDailyReportForm();
		mvc.perform(post("/students/insert_daily_report")
				.with(SecurityMockMvcRequestPostProcessors.csrf()))
			.andExpect(status().is3xxRedirection());
	}
	
	@Test
	@WithMockUser
	public void 研修リストの表示() throws Exception {
		mvc.perform(get("/students/training_list"))
			.andExpect(view().name("student/student_training_list"));
	}
}
