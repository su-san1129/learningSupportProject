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
	private static final Logger LOGGER = LoggerFactory.getLogger(DailyReportRepository.class);

	private final RowMapper<DailyReport> DAILY_REPORT_ROWMAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		LocalDate date = rs.getDate("date").toLocalDate();
		Integer trainingId = rs.getInt("training_id");
		Integer studentId = rs.getInt("student_id");
		String content = rs.getString("content");
		Integer intelligibility = rs.getInt("intelligibility");
		String detailIntelligibility = rs.getString("detail_intelligibility");
		Integer aboutInstructor = rs.getInt("about_instructor");
		String question = rs.getString("question");
		return new DailyReport(id, date, trainingId, studentId, content, intelligibility, detailIntelligibility,
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
			sql.append("INSERT INTO daily_reports (date, training_id, student_id, content, ");
			sql.append("intelligibility, detail_intelligibility, about_instructor, question) ");
			sql.append("VALUES (:date, :trainingId, :studentId, :content, ");
			sql.append(":intelligibility, :detailIntelligibility, :aboutInstructor, :question);");
			LOGGER.info("日報の新規登録を行いました。");
		} else {
			sql.append("UPDATE students SET ");
			sql.append("id = :id, ");
			sql.append("date = :date, ");
			sql.append("training_id = :trainingId, ");
			sql.append("student_id = :studentId, ");
			sql.append("content = :content, ");
			sql.append("intelligibility = :intelligibility ");
			sql.append("detail_intelligibility = :detailIntelligibility");
			sql.append("about_instructor = :aboutInstructor");
			sql.append("question = :question");
			sql.append("WHERE id = :id");
			LOGGER.info("日報の更新を行いました。ID:" + report.getId());
		}
		template.update(sql.toString(), param);
	}


}
