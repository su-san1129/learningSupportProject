package com.example.demo.repository;

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

import com.example.demo.domain.Instructor;

@Repository
public class InstructorRepositroy {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final Logger LOGGER = LoggerFactory.getLogger(InstructorRepositroy.class);

	private final RowMapper<Instructor> INSTRUCTOR_ROWMAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String kana = rs.getString("kana");
		String email = rs.getString("email");
		String password = rs.getString("password");
		String affiliation = rs.getString("affiliation");
		String remarks = rs.getString("remarks");
		return new Instructor(id, name, kana, email, password, affiliation, remarks);
	};

	/**
	 * 講師情報の全件検索.
	 * 
	 * @return 講師情報
	 */
	public List<Instructor> findAll() {
		String sql = "SELECT * FROM instructors ORDER BY id";
		return template.query(sql, INSTRUCTOR_ROWMAPPER);
	}

	/**
	 * 講師の一件検索.
	 * 
	 * @param id ID
	 * @return 講師情報.
	 */
	public Instructor load(Integer id) {
		try {
			String sql = "SELECT * FROM instructors WHERE id = :id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			return template.queryForObject(sql, param, INSTRUCTOR_ROWMAPPER);
		} catch (DataAccessException e) {
			LOGGER.info("ID:" + id + "の講師は見つかりませんでした。");
			return null;
		}
	}

	/**
	 * 講師情報をDBに保存.
	 * 
	 * @param instructor 保存する講師情報
	 */
	public void save(Instructor instructor) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(instructor);
		StringBuilder sql = new StringBuilder();
		if (instructor.getId() == null) {
			sql.append("INSERT INTO instructors (name, kana, email, password, affiliation, remarks) ");
			sql.append("VALUES (:name, :kana, :email, :password, :affiliation, :remarks);");
			LOGGER.info("講師の新規登録を行いました。");
		} else {
			sql.append("UPDATE instructors SET ");
			sql.append("id = :id, ");
			sql.append("name = :name, ");
			sql.append("kana = :kana, ");
			sql.append("email = :email, ");
			sql.append("password = :password, ");
			sql.append("affiliation = :affiliation ");
			sql.append("remarks = :remarks ");
			sql.append("WHERE id = :id");
			LOGGER.info("講師の更新を行いました。ID:" + instructor.getId());
		}
		template.update(sql.toString(), param);
	}

	/**
	 * メールアドレスで講師情報を取得.
	 * 
	 * @param email メールアドレス
	 * @return メールアドレスで検索された講師情報
	 */
	public Instructor findByEmail(String email) {
		try {
			String sql = "SELECT * FROM instructors WHERE email = :email";
			SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
			return template.queryForObject(sql, param, INSTRUCTOR_ROWMAPPER);
		} catch (DataAccessException e) {
			LOGGER.info("検索された管理者は存在しません。");
			return null;
		}

	}

}
