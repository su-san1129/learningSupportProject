package com.example.demo.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.AdminResponsibleCompany;

@Repository
public class AdminResponsibleCompanyRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	// ロギング処理
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminResponsibleCompanyRepository.class);
	
//	private final RowMapper<AdminResponsibleCompany> ARC_ROWMAPPER = (rs, i) -> {
//		Integer id = rs.getInt("id");
//		Integer adminId = rs.getInt("admin_id");
//		Integer companyId = rs.getInt("company_id");
//		return new AdminResponsibleCompany(id, adminId, companyId);
//	};

	
	/**
	 * 運営者の担当企業を追加する.
	 * 
	 * @param arc 運営者の担当企業
	 */
	public void save(AdminResponsibleCompany arc) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(arc);
		StringBuilder sql = new StringBuilder();
		if (arc.getId() == null) {
			sql.append("INSERT INTO  admin_responsible_companies (admin_id, company_id) ");
			sql.append("VALUES (:adminId, :companyId);");
			LOGGER.info("運営者の担当企業を追加しました。");
		} else {
			sql.append("UPDATE  admin_responsible_companies SET ");
			sql.append("id = :id, ");
			sql.append("admin_id = :adminId, ");
			sql.append("company_id = :companyId, ");
			sql.append("WHERE id = :id");
			LOGGER.info("運営者の担当企業を更新しました。");
		}
		template.update(sql.toString(), param);
	}
	
	/**
	 * 一件削除.
	 * 
	 * @param id 削除するid
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM admin_responsible_companies WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

}
