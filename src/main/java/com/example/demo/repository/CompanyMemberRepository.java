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

import com.example.demo.domain.CompanyMember;

@Repository
public class CompanyMemberRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/** ロギング処理 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyMemberRepository.class);

	/** 企業担当者のローマッパー */
	private final RowMapper<CompanyMember> COMPANY_MEMBER_ROWMAPPER = (rs, i) -> {

		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String kana = rs.getString("kana");
		String email = rs.getString("email");
		String password = rs.getString("password");
		Integer companyId = rs.getInt("company_id");
		return new CompanyMember(id, name, kana, email, password, companyId);
	};

	
	/**
	 * 企業担当者をメールアドレスで検索.
	 * 
	 * @param email メールアドレス
	 * @return 検索された企業担当者
	 */
	public CompanyMember findByEmail(String email) {
		try {
			String sql = "SELECT * FROM company_members WHERE email = :email";
			SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
			return template.queryForObject(sql, param, COMPANY_MEMBER_ROWMAPPER);
		} catch (DataAccessException e) {
			LOGGER.info("検索された管理者は存在しません。");
			return null;
		}

	}

	public List<CompanyMember> findAll() {
		String sql = "SELECT * FROM company_members ORDER BY id";
		return template.query(sql, COMPANY_MEMBER_ROWMAPPER);
	}

	public void save(CompanyMember member) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(member);
		StringBuilder sql = new StringBuilder();
		if (member.getId() == null) {
			sql.append("INSERT INTO company_members (name, kana, email, password, company_id) ");
			sql.append("VALUES (:name, :kana, :email, :password, :companyId);");
			LOGGER.info("企業担当者の新規登録を行いました。");
		} else {
			sql.append("UPDATE company_members SET ");
			sql.append("id = :id, ");
			sql.append("name = :name, ");
			sql.append("kana = :kana, ");
			sql.append("email = :email, ");
			sql.append("password = :password, ");
			sql.append("company_id = :companyId ");
			sql.append("WHERE id = :id");
			LOGGER.info("企業担当者の更新を行いました。ID:"+member.getId());
		}
		template.update(sql.toString(), param);

	}
	
	public void deleteById(Integer id) {
		String sql = "DELETE FROM company_members WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
		LOGGER.info("ID:"+ id + "の企業担当者の削除を行いました。");
	}
	
	/**
	 * 企業担当者の一件検索.
	 * 
	 * @param id ID
	 * @return 検索された企業担当者
	 */
	public CompanyMember load(Integer id) {
		String sql = "SELECT * FROM company_members WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, COMPANY_MEMBER_ROWMAPPER);
	}

}
