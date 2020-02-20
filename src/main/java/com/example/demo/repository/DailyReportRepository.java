package com.example.demo.repository;

import java.time.LocalDate;
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

import com.example.demo.domain.DailyReport;
import com.example.demo.domain.Student;

@Repository
public class DailyReportRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Autowired
	private TrainingRepository trainingRepository;

	/** ロギング処理 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DailyReportRepository.class);

	/** 日報のローマッパー */
	private final RowMapper<DailyReport> DAILY_REPORT_ROWMAPPER = (rs, i) -> {

		// 日報のrs
		Integer id = rs.getInt("id");
		LocalDate date = rs.getDate("date").toLocalDate();
		Integer trainingId = rs.getInt("training_id");
		Integer studentId = rs.getInt("student_id");
		String content = rs.getString("content");
		Integer intelligibility = rs.getInt("intelligibility");
		String detailIntelligibility = rs.getString("detail_intelligibility");
		Integer aboutInstructor = rs.getInt("about_instructor");
		String question = rs.getString("question");

		// 受講生リスト
		String name = rs.getString("name");
		String kana = rs.getString("kana");
		String email = rs.getString("email");
		String password = rs.getString("password");
		Integer companyId = rs.getInt("company_id");
		Student student = new Student(studentId, name, kana, email, password, companyId, null, null);

		/*
		 * 日報のインスタンスを返す. 研修インスタンスは情報量が多いため、findしたデータを持たせた。 処理が重くなるようであればSQLで出力する予定。
		 */
		return new DailyReport(id, date, trainingId, studentId, content, intelligibility, detailIntelligibility,
				aboutInstructor, question, trainingRepository.load(trainingId), student);
	};

	/**
	 * 日報の全件検索.
	 * 
	 * @return 日報リスト
	 */
	public List<DailyReport> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM daily_reports d ");
		sql.append("LEFT OUTER JOIN students s ");
		sql.append("ON d.student_id = s.id ");
		sql.append("ORDER BY date");
		LOGGER.info("日報の全件検索を行いました。");
		return template.query(sql.toString(), DAILY_REPORT_ROWMAPPER);
	}

	/**
	 * 日報の保存.
	 * 
	 * @param report 日報
	 */
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

	/**
	 * 研修IDで日報を検索.
	 * 
	 * @param id ID
	 * @return 日報リスト
	 */
	public List<DailyReport> findByTrainingId(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM daily_reports d ");
		sql.append("LEFT OUTER JOIN students s ");
		sql.append("ON d.student_id = s.id ");
		sql.append("WHERE d.training_id = :trainingId ");
		sql.append("ORDER BY date ");
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("trainingId", id);
		return template.query(sql.toString(), paramMap, DAILY_REPORT_ROWMAPPER);
	}

	/**
	 * 受講生Idで日報を検索.
	 * 
	 * @param studentId  受講生ID
	 * @param trainingId 研修ID
	 * @return 受講生リスト
	 */
	public List<DailyReport> findByStudentIdANDTrainingId(Integer studentId, Integer trainingId) {
		StringBuilder sql = new StringBuilder();
		SqlParameterSource paramMap = null;
		sql.append("SELECT * FROM daily_reports d ");
		sql.append("LEFT OUTER JOIN students s ");
		sql.append("ON d.student_id = s.id ");
		sql.append("WHERE d.student_id = :studentId AND d.training_id = :trainingId ");
		sql.append("ORDER BY date ");
		paramMap = new MapSqlParameterSource().addValue("studentId", studentId).addValue("trainingId", trainingId);
		LOGGER.info("日報を受講生IDで検索しました");
		return template.query(sql.toString(), paramMap, DAILY_REPORT_ROWMAPPER);
	}

}
