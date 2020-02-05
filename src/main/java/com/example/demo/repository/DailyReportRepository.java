package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.DailyReport;

@Repository
public class DailyReportRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	// ロギング処理
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminRepository.class);

	private final RowMapper<DailyReport> DAILY_REPORT_ROWMAPPER = (rs, i) -> {

		Integer id = rs.getInt("id");
		LocalDate date = rs.getDate("date").toLocalDate();
		Integer trainingId = rs.getInt("training_id");
		Integer studentId = rs.getInt("student_id");
		String context = rs.getString("context");
		Integer intelligibility = rs.getInt("intelligibility");
		String detailIntelligibillity = rs.getString("detail_intelligibillity");
		Integer aboutInstructor = rs.getInt("about_instructor");
		String question = rs.getString("question");
		return new DailyReport(id, date, trainingId, studentId, context, intelligibility, detailIntelligibillity,
				aboutInstructor, question, null, null);
	};
	
	public List<DailyReport> findAll(){
		String sql = "SELECT * FROM daily_reports ORDER BY date";
		LOGGER.info("日報の全件検索を行いました。");
		return template.query(sql, DAILY_REPORT_ROWMAPPER);
	}
	public void save(DailyReport report) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(report);
		StringBuilder sql = new StringBuilder();
		if (report.getId() == null) {
			sql.append("INSERT INTO daily_reports (date, training_id, student_id, context, ");
			sql.append("intelligibility, detail_intelligibillity, about_instructor, question) ");
			sql.append("VALUES (:date, :trainingId, :studentId, :context, ");
			sql.append(":intelligibility, :detailIntelligibillity, :aboutInstructor, :question);");
		} else {
			sql.append("UPDATE students SET ");
			sql.append("id = :id, ");
			sql.append("date = :date, ");
			sql.append("training_id = :trainingId, ");
			sql.append("student_id = :studentId, ");
			sql.append("context = :context, ");
			sql.append("intelligibility = :intelligibility ");
			sql.append("detail_intelligibillity = :detailIntelligibillity");
			sql.append("about_instructor = :aboutInstructor");
			sql.append("question = :question");
			sql.append("WHERE id = :id");
		}
		template.update(sql.toString(), param);
	}


}
