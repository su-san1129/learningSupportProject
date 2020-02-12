package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.WeeklyReport;

/**
 * 週報を表すリポジトリ.
 * 
 * @author takahiro.suzuki
 *
 */
@Repository
public class WeeklyReportRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeeklyReportRepository.class);
	
	
	private final RowMapper<WeeklyReport> WEEKLY_REPORT_ROWMAPPER = (rs, i) -> {
		 Integer id = rs.getInt("id");
		 LocalDate startDate = rs.getDate("start_date").toLocalDate();
		 String instructorName = rs.getString("instructor_name");
		 String content = rs.getString("content");
		 Integer trainingId = rs.getInt("training_id");
		 return new WeeklyReport(id, startDate, instructorName, content, trainingId);
	};
	
	/**
	 * 研修IDで週報を検索.
	 * @param id ID
	 * @return 週報
	 */
	public List<WeeklyReport> findByTrainingId(Integer id){
		String sql = "SELECT * FROM weekly_reports WHERE training_id = :trainingId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("trainingId", id);
		LOGGER.info("研修ID:"+id+"の週報を検索しました");
		return template.query(sql, param, WEEKLY_REPORT_ROWMAPPER);
	}
	
	/**
	 * 週報の一件検索.
	 * 
	 * @param id 週報ID
	 * @return
	 */
	public WeeklyReport load(Integer id){
		try {
			String sql = "SELECT * FROM weekly_reports WHERE id = :id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			return template.queryForObject(sql, param, WEEKLY_REPORT_ROWMAPPER);
		} catch (DataAccessException e) {
			LOGGER.info("ID:"+id+"の週報は存在しませんでした");
			return null;
		}
	}

}
