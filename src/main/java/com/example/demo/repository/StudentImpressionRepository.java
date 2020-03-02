package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.StudentImpression;

@Repository
public class StudentImpressionRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	RowMapper<StudentImpression> rowMapper = new BeanPropertyRowMapper<StudentImpression>(StudentImpression.class);
	
	public StudentImpression load(Integer id) {
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject("SELECT * FROM student_impressions WHERE id = :id", paramMap, rowMapper);
	}

}
