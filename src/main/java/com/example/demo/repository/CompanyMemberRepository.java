package com.example.demo.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.CompanyMember;

@Repository
public class CompanyMemberRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	// ロギング処理
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyMemberRepository.class);

	private final RowMapper<CompanyMember> COMPANY_MEMBER_ROWMAPPER = (rs, i) -> {

		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String kana = rs.getString("kana");
		String email = rs.getString("email");
		String password = rs.getString("password");
		Integer companyId = rs.getInt("company_id");
		return new CompanyMember(id, name, kana, email, password, companyId);
	};
	
	public CompanyMember findByEmail(String email) {
		try {
			String sql = "SELECT * FROM company_members WHERE email = :email";
			SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
			return template.queryForObject(sql, param, COMPANY_MEMBER_ROWMAPPER);
		} catch (DataAccessException e) {
			LOGGER.warn("検索された管理者は存在しません。");
			return null;
		}

	}
	
	public List<CompanyMember> findAll(){
		String sql = "SELECT * FROM company_members ORDER BY id";
		return template.query(sql, COMPANY_MEMBER_ROWMAPPER);
	}
	

}