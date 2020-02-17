package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Company;
import com.example.demo.domain.CompanyMember;

/**
 * 企業情報を管理するリポジトリ.
 * 
 * @author takahiro.suzuki
 *
 */
@Repository
public class CompanyRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/** ロギング処理 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyRepository.class);

	/** ローマッパー */
	private final RowMapper<Company> COMPANY_ROWMAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String kana = rs.getString("kana");
		String remarks = rs.getString("remarks");
		List<CompanyMember> companyMemberList = new ArrayList<>();
		return new Company(id, name, kana, remarks, companyMemberList);
	};

	/** メンバー情報を加えた企業情報 */
	private final ResultSetExtractor<List<Company>> COMPANY_RSE = (rs) -> {
		int preId = 0;
		List<Company> companyList = new ArrayList<>();
		List<CompanyMember> companyMemberList = null;
		while (rs.next()) {
			if (preId != rs.getInt("id")) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String kana = rs.getString("kana");
				String remarks = rs.getString("remarks");
				companyMemberList = new ArrayList<>();
				Company company = new Company(id, name, kana, remarks, companyMemberList);
				preId = id;
				companyList.add(company);
			}
			if (rs.getInt("member_id") != 0) {
				Integer comId = rs.getInt("member_id");
				String memberName = rs.getString("member_name");
				String memberKana = rs.getString("member_kana");
				String email = rs.getString("email");
				String password = rs.getString("password");
				CompanyMember comMember = new CompanyMember(comId, memberName, memberKana, email, password,
						rs.getInt("id"));
				companyMemberList.add(comMember);
			}
		}
		return companyList;
	};

	/**
	 * 企業担当者が入っていない企業の全件検索.
	 * 
	 * @return 企業担当者が入っていない全件検索
	 */
	public List<Company> findAllNonCompanyMember() {
		String sql = "SELECT * FROM companies ORDER BY id";
		LOGGER.info("企業の全件取得を行いました（企業担当者は含みません。）");
		return template.query(sql, COMPANY_ROWMAPPER);
	}

	/**
	 * 企業の全件検索.
	 * 
	 * @return 企業の全件情報
	 */
	public List<Company> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("c.id, c.name, c.kana, c.remarks, ");
		sql.append("cm.id AS member_id, cm.name AS member_name, cm.kana AS member_kana, email, password ");
		sql.append("FROM ");
		sql.append("companies c ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("company_members cm ");
		sql.append("ON c.id = cm.company_id ");
		sql.append("ORDER BY c.id, cm.id");
		LOGGER.info("企業の全件検索を行いました。");
		return template.query(sql.toString(), COMPANY_RSE);
	}

	/**
	 * 企業の一件検索.
	 * 
	 * @param id ID
	 * @return 企業情報
	 */
	public Company load(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("c.id, c.name, c.kana, c.remarks, ");
		sql.append("cm.id AS member_id, cm.name AS member_name, cm.kana AS member_kana, email, password ");
		sql.append("FROM ");
		sql.append("companies c ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("company_members cm ");
		sql.append("ON c.id = cm.company_id ");
		sql.append("WHERE c.id = :id ");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		LOGGER.info("企業の一件検索を行いました。ID:" + id);
		List<Company> companyList = template.query(sql.toString(), param, COMPANY_RSE);
		if (companyList.isEmpty()) {
			LOGGER.info("ID:" + id + "に該当する企業はありませんでした。");
			return null;
		}
		return companyList.get(0);
	}
	
	/**
	 * 企業情報を名前で検索.
	 * 
	 * @param name 名前
	 * @return 名前で検索された企業情報
	 */
	public Company findByName(String name) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("* ");
			sql.append("FROM ");
			sql.append("companies ");
			sql.append("WHERE name = :name ");
			SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
			LOGGER.info("企業の一件検索を行いました。企業名:" + name);
			return template.queryForObject(sql.toString(), param, COMPANY_ROWMAPPER);
		} catch (DataAccessException e) {
			LOGGER.info("企業名:" + name + "に該当する企業はありませんでした。");
			return null;
		}
	}

	/**
	 * 企業情報をDBに保存.
	 * 
	 * @param company 企業情報
	 */
	public void save(Company company) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(company);
		StringBuilder sql = new StringBuilder();
		if (company.getId() == null) {
			sql.append("INSERT INTO companies (name, kana, remarks) ");
			sql.append("VALUES (:name, :kana, :remarks);");
			LOGGER.info("企業の新規登録を行いました。");
		} else {
			sql.append("UPDATE companies SET ");
			sql.append("id = :id, ");
			sql.append("name = :name, ");
			sql.append("kana = :kana, ");
			sql.append("remarks = :remarks ");
			sql.append("WHERE id = :id");
			LOGGER.info("企業情報の更新を行いました。 ID:" + company.getId());
		}
		template.update(sql.toString(), param);
	}

}
