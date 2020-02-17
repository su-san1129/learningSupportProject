package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Training;
import com.example.demo.form.TrainingRegisterForm;
import com.example.demo.repository.TrainingRepository;

@Service
@Transactional
public class TrainingService {
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	public void trainingSave(TrainingRegisterForm form) {
		Training training = new Training();
		BeanUtils.copyProperties(form, training);
		training.setStartDate(form.toLocalDateStartDate());
		training.setEndDate(form.toLocalDateEndDate());
		trainingRepository.save(training);
	}

	public List<Training> showAllTraining() {
		return trainingRepository.findAll();
	}
	
	public Training showTraining(Integer id) {
		return trainingRepository.load(id);
	}
}
