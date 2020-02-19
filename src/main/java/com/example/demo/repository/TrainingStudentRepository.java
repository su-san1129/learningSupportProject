package com.example.demo.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.TrainingStudent;

@Repository
public class TrainingStudentRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final Logger LOGGER = LoggerFactory.getLogger(TrainingStudentRepository.class);
	
//	private final RowMapper<TrainingStudent> TS_ROWMAPPER = (rs, i) -> {
//		Integer id = rs.getInt("id");
//		Integer trainingId = rs.getInt("training_id");
//		Integer studentId = rs.getInt("student_id");
//		return new TrainingStudent(id, trainingId, studentId);
//	};

	/**
	 * 研修に受講生を保存.
	 * 
	 * @param ts 研修受講生テーブル.
	 */
	public void save(TrainingStudent ts) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(ts);
		StringBuilder sql = new StringBuilder();
		if(ts.getId() == null ) {
			sql.append("INSERT INTO training_student (training_id, student_id ) ");
			sql.append("VALUES (:trainingId, :studentId) ");
			LOGGER.info("研修ID:"+ ts.getTrainingId() + "に受講生を登録します");
		} else {
			sql.append("UPDATE training_student SET ");
			sql.append("id = :id, ");
			sql.append("training_id = :trainingId, ");
			sql.append("student_id = :studentId ");
			sql.append("WHERE id = :id ");
			LOGGER.info("研修ID:"+ ts.getTrainingId() + "の受講生を更新します");
		}
		template.update(sql.toString(), param);
	}

}
