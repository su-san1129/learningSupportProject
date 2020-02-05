package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Company;
import com.example.demo.domain.Student;
import com.example.demo.domain.TrainingStudent;

@Repository
public class StudentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	// ロギング処理
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminRepository.class);

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
	
	public List<Student> findAll(){
		String sql = "SELECT * FROM students ORDER BY id";
		return template.query(sql, STUDENT_ROWMAPPER);
	}
	
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
	
	public void save(Student student) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(student);
		StringBuilder sql = new StringBuilder();
		if (student.getId() == null) {
			sql.append("INSERT INTO students (name, kana, email, password, company_id) ");
			sql.append("VALUES (:name, :kana, :email, :password, :companyId);");
		} else {
			sql.append("UPDATE students SET ");
			sql.append("id = :id, ");
			sql.append("name = :name, ");
			sql.append("kana = :kana, ");
			sql.append("email = :email, ");
			sql.append("password = :password, ");
			sql.append("company_id = :companyId ");
			sql.append("WHERE id = :id");
		}
		template.update(sql.toString(), param);
	}

	
	
	
	

}
