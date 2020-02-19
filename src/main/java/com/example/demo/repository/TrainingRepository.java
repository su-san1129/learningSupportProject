package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Instructor;
import com.example.demo.domain.Student;
import com.example.demo.domain.Training;
import com.example.demo.domain.WeeklyReport;

@Repository
public class TrainingRepository {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private WeeklyReportRepository weeklyReportRepository;

	@Autowired
	private NamedParameterJdbcTemplate template;

	// ロギング処理
	private static final Logger LOGGER = LoggerFactory.getLogger(TrainingRepository.class);

	private final RowMapper<Training> TRAINING_ROWMAPPER = (rs, i) -> {

		Instructor subInstructor1 = null;
		Instructor subInstructor2 = null;
		Instructor subInstructor3 = null;

		Integer id = rs.getInt("id");
		LocalDate startDate = rs.getDate("start_date").toLocalDate();
		LocalDate endDate = rs.getDate("end_date").toLocalDate();
		String name = rs.getString("name");

		// メイン講師
		Integer instructorId = rs.getInt("instructor_id");
		String instructorName = rs.getString("i_name");
		String instructorKana = rs.getString("i_kana");
		String instructorEmail = rs.getString("i_email");
		String instructorPassword = rs.getString("i_password");
		String instructorAffiliation = rs.getString("i_affiliation");
		String instructorRemarks = rs.getString("i_remarks");
		Instructor instructor = new Instructor(instructorId, instructorName, instructorKana, instructorEmail,
				instructorPassword, instructorAffiliation, instructorRemarks);

		// サブ講師１
		Integer subInstructor1Id = rs.getInt("sub_instructor_id1");
		if (subInstructor1Id != 0) {
			String subInstructorName = rs.getString("s1_name");
			String subInstructorKana = rs.getString("s1_kana");
			String subInstructorEmail = rs.getString("s1_email");
			String subInstructorPassword = rs.getString("s1_password");
			String subInstructorAffiliation = rs.getString("s1_affiliation");
			String subInstructorRemarks = rs.getString("s1_remarks");
			subInstructor1 = new Instructor(subInstructor1Id, subInstructorName, subInstructorKana, subInstructorEmail,
					subInstructorPassword, subInstructorAffiliation, subInstructorRemarks);
		}
		// サブ講師２
		Integer subInstructor2Id = rs.getInt("sub_instructor_id2");
		if (subInstructor2Id != 0) {
			String subInstructorName2 = rs.getString("s2_name");
			String subInstructorKana2 = rs.getString("s2_kana");
			String subInstructorEmail2 = rs.getString("s2_email");
			String subInstructorPassword2 = rs.getString("s2_password");
			String subInstructorAffiliation2 = rs.getString("s2_affiliation");
			String subInstructorRemarks2 = rs.getString("s2_remarks");
			subInstructor2 = new Instructor(subInstructor2Id, subInstructorName2, subInstructorKana2,
					subInstructorEmail2, subInstructorPassword2, subInstructorAffiliation2, subInstructorRemarks2);
		}
		// サブ講師３
		Integer subInstructor3Id = rs.getInt("sub_instructor_id3");
		if (subInstructor3Id != 0) {
			String subInstructorName3 = rs.getString("s3_name");
			String subInstructorKana3 = rs.getString("s3_kana");
			String subInstructorEmail3 = rs.getString("s3_email");
			String subInstructorPassword3 = rs.getString("s3_password");
			String subInstructorAffiliation3 = rs.getString("s3_affiliation");
			String subInstructorRemarks3 = rs.getString("s3_remarks");
			subInstructor3 = new Instructor(subInstructor3Id, subInstructorName3, subInstructorKana3,
					subInstructorEmail3, subInstructorPassword3, subInstructorAffiliation3, subInstructorRemarks3);
		}

		// 受講生リスト
		List<Student> studentList = studentRepository.findByTrainingId(id);
		// 週報リスト
		List<WeeklyReport> weeklyReportList = weeklyReportRepository.findByTrainingId(id);

		Training training = new Training(id, startDate, endDate, name, instructorId, subInstructor1Id, subInstructor2Id,
				subInstructor3Id, instructor, subInstructor1, subInstructor2, subInstructor3, studentList,
				weeklyReportList);
		return training;
	};

	/**
	 * 
	 * 研修リストの全件検索.
	 * 
	 * @return 研修リスト
	 */
	public List<Training> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t.id, t.start_date, t.end_date, t.name, t.instructor_id ");
		sql.append(", t.sub_instructor_id1, t.sub_instructor_id2, t.sub_instructor_id3 ");
		sql.append(", i.id AS i_id, i.name AS i_name, i.kana AS i_kana, i.email AS i_email");
		sql.append(", i.password AS i_password, i.affiliation AS i_affiliation, i.remarks AS i_remarks ");
		sql.append(", s1.id AS s1_id, s1.name AS s1_name, s1.kana AS s1_kana, s1.email AS s1_email");
		sql.append(", s1.password AS s1_password, s1.affiliation AS s1_affiliation, s1.remarks AS s1_remarks ");
		sql.append(", s2.id AS s2_id, s2.name AS s2_name, s2.kana AS s2_kana, s2.email AS s2_email");
		sql.append(", s2.password AS s2_password, s2.affiliation AS s2_affiliation, s2.remarks AS s2_remarks ");
		sql.append(", s3.id AS s3_id, s3.name AS s3_name, s3.kana AS s3_kana, s3.email AS s3_email");
		sql.append(", s3.password AS s3_password, s3.affiliation AS s3_affiliation, s3.remarks AS s3_remarks ");
		sql.append("FROM trainings t ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("instructors i ");
		sql.append("ON t.instructor_id = i.id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("instructors s1 ");
		sql.append("ON t.sub_instructor_id1 = s1.id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("instructors s2 ");
		sql.append("ON t.sub_instructor_id2 = s2.id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("instructors s3 ");
		sql.append("ON t.sub_instructor_id3 = s3.id ");
		sql.append("ORDER BY t.id, i.id, s1.id, s2.id, s3.id");
		LOGGER.info("研修テーブルの全件検索を行いました");
		return template.query(sql.toString(), TRAINING_ROWMAPPER);
	}

	/**
	 * 研修情報の保存.
	 * 
	 * @param training
	 */
	public void save(Training training) {
		StringBuilder sql = new StringBuilder();
		SqlParameterSource param = new BeanPropertySqlParameterSource(training);
		if (training.getId() == null) {
			sql.append("INSERT INTO trainings ( start_date, end_date, name, instructor_id");
			sql.append(", sub_instructor_id1, sub_instructor_id2, sub_instructor_id3)");
			sql.append(
					" VALUES ( :startDate, :endDate, :name, :instructorId, :subInstructor1Id, :subInstructor2Id, :subInstructor3Id)");
			LOGGER.info("研修レコードを新規登録しました");
			template.update(sql.toString(), param);
		} else {
			sql.append("UPDATE trainings SET ");
			sql.append("id = :id, ");
			sql.append("start_date = :startDate, ");
			sql.append("end_date = :endDate, ");
			sql.append("name = :name, ");
			sql.append("instructor_id = :instructorId, ");
			sql.append("sub_instructor_id1 = :subInstructor1Id, ");
			sql.append("sub_instructor_id2 = :subInstructor2Id, ");
			sql.append("sub_instructor_id3 = :subInstructor3Id ");
			sql.append("WHERE id = :id;");
			LOGGER.info("ID:" + training.getId() + "の研修レコードを更新しました");
			template.update(sql.toString(), param);
		}
	}

	public Training load(Integer id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT t.id, t.start_date, t.end_date, t.name, t.instructor_id ");
			sql.append(", t.sub_instructor_id1, t.sub_instructor_id2, t.sub_instructor_id3 ");
			sql.append(", i.id AS i_id, i.name AS i_name, i.kana AS i_kana, i.email AS i_email");
			sql.append(", i.password AS i_password, i.affiliation AS i_affiliation, i.remarks AS i_remarks ");
			sql.append(", s1.id AS s1_id, s1.name AS s1_name, s1.kana AS s1_kana, s1.email AS s1_email");
			sql.append(", s1.password AS s1_password, s1.affiliation AS s1_affiliation, s1.remarks AS s1_remarks ");
			sql.append(", s2.id AS s2_id, s2.name AS s2_name, s2.kana AS s2_kana, s2.email AS s2_email");
			sql.append(", s2.password AS s2_password, s2.affiliation AS s2_affiliation, s2.remarks AS s2_remarks ");
			sql.append(", s3.id AS s3_id, s3.name AS s3_name, s3.kana AS s3_kana, s3.email AS s3_email");
			sql.append(", s3.password AS s3_password, s3.affiliation AS s3_affiliation, s3.remarks AS s3_remarks ");
			sql.append("FROM trainings t ");
			sql.append("LEFT OUTER JOIN ");
			sql.append("instructors i ");
			sql.append("ON t.instructor_id = i.id ");
			sql.append("LEFT OUTER JOIN ");
			sql.append("instructors s1 ");
			sql.append("ON t.sub_instructor_id1 = s1.id ");
			sql.append("LEFT OUTER JOIN ");
			sql.append("instructors s2 ");
			sql.append("ON t.sub_instructor_id2 = s2.id ");
			sql.append("LEFT OUTER JOIN ");
			sql.append("instructors s3 ");
			sql.append("ON t.sub_instructor_id3 = s3.id ");
			sql.append("WHERE t.id = :id ");
			sql.append("ORDER BY t.id, i.id, s1.id, s2.id, s3.id");
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			LOGGER.info("ID:" + id + "の研修レコードを検索しました");
			return template.queryForObject(sql.toString(), param, TRAINING_ROWMAPPER);
		} catch (DataAccessException e) {
			LOGGER.info("ID:" + id + "の研修レコードは存在しませんでした");
			return null;
		}
	}

	/**
	 * 受講生IDで研修リストを表示する.
	 * 
	 * @param studentId 受講生ID
	 * @return 研修リスト
	 */
	public List<Training> findByStudentId(Integer studentId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t.id, t.start_date, t.end_date, t.name, t.instructor_id ");
		sql.append(", t.sub_instructor_id1, t.sub_instructor_id2, t.sub_instructor_id3 ");
		sql.append(", i.id AS i_id, i.name AS i_name, i.kana AS i_kana, i.email AS i_email");
		sql.append(", i.password AS i_password, i.affiliation AS i_affiliation, i.remarks AS i_remarks ");
		sql.append(", s1.id AS s1_id, s1.name AS s1_name, s1.kana AS s1_kana, s1.email AS s1_email");
		sql.append(", s1.password AS s1_password, s1.affiliation AS s1_affiliation, s1.remarks AS s1_remarks ");
		sql.append(", s2.id AS s2_id, s2.name AS s2_name, s2.kana AS s2_kana, s2.email AS s2_email");
		sql.append(", s2.password AS s2_password, s2.affiliation AS s2_affiliation, s2.remarks AS s2_remarks ");
		sql.append(", s3.id AS s3_id, s3.name AS s3_name, s3.kana AS s3_kana, s3.email AS s3_email");
		sql.append(", s3.password AS s3_password, s3.affiliation AS s3_affiliation, s3.remarks AS s3_remarks ");
		sql.append("FROM trainings t ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("instructors i ");
		sql.append("ON t.instructor_id = i.id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("instructors s1 ");
		sql.append("ON t.sub_instructor_id1 = s1.id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("instructors s2 ");
		sql.append("ON t.sub_instructor_id2 = s2.id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("instructors s3 ");
		sql.append("ON t.sub_instructor_id3 = s3.id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("training_student ts ");
		sql.append("ON t.id = ts.training_id ");
		sql.append("WHERE ts.student_id = :studentId ");
		sql.append("ORDER BY t.id, i.id, s1.id, s2.id, s3.id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("studentId", studentId);
		LOGGER.info("ID:" + studentId + "の研修レコードを検索しました");
		return template.query(sql.toString(), param, TRAINING_ROWMAPPER);
	}

}
