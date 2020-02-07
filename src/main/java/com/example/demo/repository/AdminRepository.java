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

import com.example.demo.domain.Admin;
import com.example.demo.domain.Company;

/**
 * 管理者のリポジトリ.
 * 
 * @author takahiro.suzuki
 *
 */
@Repository
public class AdminRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	// ロギング処理
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminRepository.class);

	private final RowMapper<Admin> ADMIN_ROWMAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String kana = rs.getString("kana");
		String email = rs.getString("email");
		String password = rs.getString("password");
		boolean canShowAllCompany = rs.getBoolean("can_show_all_company");
		List<Company> companyList = new ArrayList<>();
		return new Admin(id, name, kana, email, password, canShowAllCompany, companyList);
	};

	public void save(Admin admin) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(admin);
		StringBuilder sql = new StringBuilder();
		if (admin.getId() == null) {
			sql.append("INSERT INTO admins (name, kana, email, password, can_show_all_company) ");
			sql.append("VALUES (:name, :kana, :email, :password, :canShowAllCompany);");
		} else {
			sql.append("UPDATE admins SET ");
			sql.append("id = :id, ");
			sql.append("name = :name, ");
			sql.append("kana = :kana, ");
			sql.append("email = :email, ");
			sql.append("password = :password, ");
			sql.append("can_show_all_company = :canShowAllCompany ");
			sql.append("WHERE id = :id");
		}
		template.update(sql.toString(), param);
	}

	/**
	 * メールアドレスで管理者を検索する.
	 * 
	 * 0件の場合はnullを返す
	 * @param email 検索するメールアドレス
	 * @return 該当する管理者
	 */
	public Admin findByEmail(String email) {
		try {
			String sql = "SELECT id, name, kana, email, password, can_show_all_company FROM admins WHERE email = :email";
			SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
			return template.queryForObject(sql, param, ADMIN_ROWMAPPER);
		} catch (DataAccessException e) {
			LOGGER.warn("検索された管理者は存在しません。");
			return null;
		}

	}
	
	public Admin load(Integer id) {
		try {
			String sql = "SELECT id, name, kana, email, password, can_show_all_company FROM admins WHERE id = :id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			return template.queryForObject(sql, param, ADMIN_ROWMAPPER);
		} catch (DataAccessException e) {
			LOGGER.warn("検索された管理者は存在しません。");
			return null;
		}
		
	}
	
	/**
	 * 管理者の全件検索.
	 * @return 管理者
	 */
	public List<Admin> findAll(){
		String sql = "SELECT * FROM admins ORDER BY id";
		return template.query(sql, ADMIN_ROWMAPPER);
	}

}
