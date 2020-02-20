package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Company;
import com.example.demo.domain.Student;
import com.example.demo.domain.TrainingStudent;

/**
 * 受講生を扱うリポジトリ.
 * 
 * @author takahiro.suzuki
 *
 */
@Repository
public class StudentRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/** ロギング処理 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminRepository.class);

	/** ローマッパー */
	private final RowMapper<Student> STUDENT_ROWMAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String kana = rs.getString("kana");
		String email = rs.getString("email");
		String password = rs.getString("password");
		Integer companyId = rs.getInt("company_id");
		Company company = new Company();
		List<TrainingStudent> trainingList = new ArrayList<>();
		return new Student(id, name, kana, email, password, companyId, company, trainingList);
	};

	/**
	 * 全件検索.
	 * 
	 * @return 全件検索結果
	 */
	public List<Student> findAll() {
		String sql = "SELECT * FROM students ORDER BY id";
		return template.query(sql, STUDENT_ROWMAPPER);
	}

	/**
	 * メールアドレスで受講生を検索.
	 * 
	 * Securityのユーザー認証で使う
	 * @param email メールアドレス
	 * @return 受講生情報
	 */
	public Student findByEmail(String email) {
		try {
			String sql = "SELECT * FROM students WHERE email = :email";
			SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
			return template.queryForObject(sql, param, STUDENT_ROWMAPPER);
		} catch (DataAccessException e) {
			LOGGER.warn("検索された受講者は存在しません。");
			return null;
		}
	}

	/**
	 * 研修IDで受講生を検索.
	 * @param trainingId 受講生ID
	 * @return 受講生リスト
	 */
	public List<Student> findByTrainingId(Integer trainingId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("s.id, name, kana, email, password, company_id ");
		sql.append("FROM ");
		sql.append("students s ");
		sql.append("JOIN ");
		sql.append("training_student ts ");
		sql.append("ON ");
		sql.append("s.id = ts.student_id ");
		sql.append("WHERE ");
		sql.append("ts.training_id = :trainingId");
		SqlParameterSource param = new MapSqlParameterSource().addValue("trainingId", trainingId);
		return template.query(sql.toString(), param, STUDENT_ROWMAPPER);
	}
	
	/**
	 * 企業、研修IDにて受講生を検索.
	 * 
	 * @param trainingId 研修ID
	 * @param companyId 企業ID
	 * @return 受講生一覧
	 */
	public List<Student> findByTrainingIdAndCompanyId(Integer trainingId, Integer companyId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("s.id, name, kana, email, password, company_id ");
		sql.append("FROM ");
		sql.append("students s ");
		sql.append("JOIN ");
		sql.append("training_student ts ");
		sql.append("ON ");
		sql.append("s.id = ts.student_id ");
		sql.append("WHERE ");
		sql.append("ts.training_id = :trainingId AND s.company_id = :companyId ");
		SqlParameterSource param = new MapSqlParameterSource().addValue("trainingId", trainingId).addValue("companyId", companyId);
		return template.query(sql.toString(), param, STUDENT_ROWMAPPER);
	}

	/**
	 * 受講生情報の保存.
	 * 
	 * 重複キーエラーが出た場合は、エラーを投げる
	 * @param student 受講生
	 * @return 受講生のID
	 * @throws DuplicateKeyException 重複キーエラー
	 */
	public Integer save(Student student) throws DuplicateKeyException{
		SqlParameterSource param = new BeanPropertySqlParameterSource(student);
		StringBuilder sql = new StringBuilder();
		if (student.getId() == null) {
			sql.append("INSERT INTO students (name, kana, email, password, company_id) ");
			sql.append("VALUES (:name, :kana, :email, :password, :companyId) RETURNING id;");	
			return template.queryForObject(sql.toString(), param, Integer.class);
		} else {
			sql.append("UPDATE students SET ");
			sql.append("id = :id, ");
			sql.append("name = :name, ");
			sql.append("kana = :kana, ");
			sql.append("email = :email, ");
			sql.append("password = :password, ");
			sql.append("company_id = :companyId ");
			sql.append("WHERE id = :id");
			template.update(sql.toString(), param);
			return student.getId();
		}
	}

}
