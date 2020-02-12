package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Training;
import com.example.demo.form.TrainingRegisterForm;
import com.example.demo.repository.TrainingRepository;

@Service
public class TrainingService {
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	public void save(TrainingRegisterForm form) {
		Training training = new Training();
		BeanUtils.copyProperties(form, training);
		trainingRepository.save(training);
	}

	public List<Training> showAllTraining() {
		return trainingRepository.findAll();
	}
}
