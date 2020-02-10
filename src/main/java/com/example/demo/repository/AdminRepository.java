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

import com.example.demo.domain.Admin;
import com.example.demo.domain.Company;
import com.example.demo.domain.CompanyMember;

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

	private final ResultSetExtractor<List<Admin>> ADMIN_RSE = rs -> {
		List<Admin> adminList = new ArrayList<>();
		List<Company> companyList = null;
		List<CompanyMember> companyMemberList = null;
		Integer preId = 0;
		Integer preCompanyId = 0;
		Integer preCompanyMemberId = 0;
		while (rs.next()) {
			if (rs.getInt("id") != preId) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String kana = rs.getString("kana");
				String email = rs.getString("email");
				String password = rs.getString("password");
				boolean canShowAllCompany = rs.getBoolean("can_show_all_company");
				companyList = new ArrayList<>();
				Admin admin = new Admin(id, name, kana, email, password, canShowAllCompany, companyList);
				adminList.add(admin);
				preId = id;
			}
			Integer companyId = rs.getInt("company_id");
			if (companyId != 0 && companyId != preCompanyId) {
				String companyName = rs.getString("company_name");
				String companyKana = rs.getString("company_kana");
				String remarks = rs.getString("remarks");
				companyMemberList = new ArrayList<>();
				Company company = new Company(companyId, companyName, companyKana, remarks, companyMemberList);
				companyList.add(company);
				preCompanyId = companyId;
			}
			Integer companyMemberId = rs.getInt("cm_id");
			if (companyMemberId != 0 && preCompanyMemberId != companyMemberId) {
				String cmName = rs.getString("cm_name");
				String cmKana = rs.getString("cm_kana");
				String cmEmail = rs.getString("cm_email");
				String cmPw = rs.getString("cm_password");
				CompanyMember cm = new CompanyMember(companyMemberId, cmName, cmKana, cmEmail, cmPw, companyId);
				companyMemberList.add(cm);
				preCompanyMemberId = companyMemberId;
			}
		}
		return adminList;
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
	 * 
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

	public Admin loadIncludeCompanyList(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("a.id, a.name, a.kana, a.email, a.password, a.can_show_all_company, ");
		sql.append("c.id AS company_id, ");
		sql.append("c.name AS company_name, ");
		sql.append("c.kana AS company_kana, ");
		sql.append("remarks, ");
		sql.append("cm.id AS cm_id, ");
		sql.append("cm.name AS cm_name, ");
		sql.append("cm.kana AS cm_kana, ");
		sql.append("cm.email AS cm_email, ");
		sql.append("cm.password AS cm_password ");
		sql.append("FROM admins a LEFT OUTER JOIN ");
		sql.append("admin_responsible_companies ac ON a.id = ac.admin_id  LEFT OUTER JOIN ");
		sql.append("companies c ON ac.company_id = c.id  LEFT OUTER JOIN ");
		sql.append("company_members cm ON c.id = cm.company_id  ");
		sql.append("WHERE a.id = :id  ");
		sql.append("ORDER BY a.id, c.id, cm.id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Admin> admins = template.query(sql.toString(), param, ADMIN_RSE);
		if (admins.size() == 0) {
			LOGGER.warn("検索された管理者は存在しません。");
			return null;
		}
		return admins.get(0);
	}

	/**
	 * 管理者の全件検索.
	 * 
	 * @return 管理者
	 */
	public List<Admin> findAll() {
		String sql = "SELECT * FROM admins ORDER BY id";
		return template.query(sql, ADMIN_ROWMAPPER);
	}

	public List<Admin> findAllIncludeCompanyList() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("a.id, a.name, a.kana, a.email, a.password, a.can_show_all_company, ");
		sql.append("c.id AS company_id, ");
		sql.append("c.name AS company_name, ");
		sql.append("c.kana AS company_kana, ");
		sql.append("remarks, ");
		sql.append("cm.id AS cm_id, ");
		sql.append("cm.name AS cm_name, ");
		sql.append("cm.kana AS cm_kana, ");
		sql.append("cm.email AS cm_email, ");
		sql.append("cm.password AS cm_password ");
		sql.append("FROM admins a LEFT OUTER JOIN ");
		sql.append("admin_responsible_companies ac ON a.id = ac.admin_id  LEFT OUTER JOIN ");
		sql.append("companies c ON ac.company_id = c.id  LEFT OUTER JOIN ");
		sql.append("company_members cm ON c.id = cm.company_id  ");
		sql.append("ORDER BY a.id, c.id, cm.id");
		return template.query(sql.toString(), ADMIN_RSE);
	}

}
