package com.example.demo.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.AdminResponsibleCompany;
import com.example.demo.domain.Company;

@Repository
public class AdminResponsibleCompanyRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/** ロギング処理 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminResponsibleCompanyRepository.class);

	/** ローマッパー */
	private final RowMapper<AdminResponsibleCompany> FIND_COMPANY_ARC_RM = (rs, i) -> {
		Integer id = rs.getInt("id");
		Integer adminId = rs.getInt("admin_id");
		Integer companyId = rs.getInt("company_id");
		String name = rs.getString("name");
		String kana = rs.getString("kana");
		String remarks = rs.getString("remarks");
		Company company = new Company(companyId, name, kana, remarks, null);
		AdminResponsibleCompany arc = new AdminResponsibleCompany(id, adminId, companyId, company);

		return arc;
	};

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
	
	/**
	 * 運営管理者Idで担当企業をすべて削除.
	 * 
	 * @param adminId 運営管理者ID
	 */
	public void deleteAllByAdminId(Integer adminId) {
		template.update(
				"DELETE FROM admin_responsible_companies WHERE admin_id = :adminId"
				, new MapSqlParameterSource().addValue("adminId", adminId)
				);
		LOGGER.info("運営管理者ID:"+adminId+"の担当企業を削除しました");
	}

	/**
	 * 運営管理者がもつ企業の全件検索.
	 * 
	 * @param id 運営者ID
	 * @return 運営管理者の企業リスト
	 */
	public List<AdminResponsibleCompany> findByAdminId(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ac.id, ac.admin_id, ac.company_id, c.name, kana, remarks ");
		sql.append("FROM admin_responsible_companies ac ");
		sql.append("LEFT OUTER JOIN companies c ON ac.company_id = c.id  ");
		sql.append("WHERE admin_id = :id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.query(sql.toString(), param, FIND_COMPANY_ARC_RM);
	}

}
