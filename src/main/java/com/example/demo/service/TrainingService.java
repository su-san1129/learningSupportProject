package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Training;
import com.example.demo.form.TrainingRegisterForm;
import com.example.demo.repository.TrainingRepository;

/**
 * 研修情報を扱うサービスクラス.
 * 
 * @author takahiro.suzuki
 *
 */
@Service
@Transactional
public class TrainingService {
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	/**
	 * 研修情報を保存.
	 * 
	 * @param form フォーム
	 */
	public void trainingSave(TrainingRegisterForm form) {
		Training training = new Training();
		BeanUtils.copyProperties(form, training);
		training.setStartDate(form.toLocalDateStartDate());
		training.setEndDate(form.toLocalDateEndDate());
		trainingRepository.save(training);
	}

	/**
	 * すべての研修情報を取得.
	 * 
	 * @return すべての研修情報
	 */
	public List<Training> showAllTraining() {
		return trainingRepository.findAll();
	}
	
	/**
	 * 研修情報の一件検索.
	 * 
	 * @param id ID
	 * @return IDで検索された研修情報
	 */
	public Training showTraining(Integer id) {
		return trainingRepository.load(id);
	}
	
	/**
	 * 研修情報を受講生IDで検索.
	 * 
	 * @param studentId 受講生ID
	 * @return 受講生IDで検索された研修情報
	 */
	public List<Training> showTrainingListByStudentId(Integer studentId){
		return trainingRepository.findByStudentId(studentId);
	}
}
