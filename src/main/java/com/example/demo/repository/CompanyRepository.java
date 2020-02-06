package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Company;
import com.example.demo.domain.CompanyMember;

@Repository
public class CompanyRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	// ロギング処理
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyRepository.class);

//	private final RowMapper<Company> COMPANY_ROWMAPPER = (rs, i) -> {
//		Integer id = rs.getInt("id");
//		String name = rs.getString("name");
//		String kana = rs.getString("kana");
//		String remarks = rs.getString("remarks");
//		List<CompanyMember> companyMemberList = new ArrayList<>();
//		return new Company(id, name, kana, remarks, companyMemberList);
//	};

	private final ResultSetExtractor<List<Company>> COMPANY_RSE = (rs) -> {
		int preId = 0;
		List<Company> companyList = new ArrayList<>();
		List<CompanyMember> companyMemberList = new ArrayList<>();
		while (rs.next()) {
			if (preId != rs.getInt("id")) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String kana = rs.getString("kana");
				String remarks = rs.getString("remarks");
				Company company = new Company(id, name, kana, remarks, companyMemberList);
				preId = id;
				companyList.add(company);
			}
			if (rs.getInt("member_id") != 0) {
				Integer comId = rs.getInt("member_id");
				String memberName = rs.getString("member_name");
				String memberKana = rs.getString("member_kame");
				String email = rs.getString("email");
				String password = rs.getString("password");
				CompanyMember comMember = new CompanyMember(comId, memberName, memberKana, email, password, rs.getInt("id"));
				companyMemberList.add(comMember);
			}
		}
		return companyList;
	};
	
	public List<Company> findAll(){
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
	public Company load(Integer id){
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
		sql.append("ORDER BY c.id, cm.id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		LOGGER.info("企業の一件検索を行いました。ID:"+ id);
		List<Company> companyList = template.query(sql.toString(), param, COMPANY_RSE);
		if(companyList.isEmpty()) {
			LOGGER.info("ID:"+id+"に該当する企業はありませんでした。");
			return null;
		}
		return companyList.get(0);
	}
	
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
			LOGGER.info("企業情報の更新を行いました。 ID:"+ company.getId());
		}
		template.update(sql.toString(), param);
	}

}
