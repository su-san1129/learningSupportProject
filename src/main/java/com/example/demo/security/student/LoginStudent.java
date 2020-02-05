package com.example.demo.security.student;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.domain.Student;

public class LoginStudent extends User {

	private static final long serialVersionUID = 1L;
	private final Student student;

	public LoginStudent(Student student, Collection<GrantedAuthority> authorityList) {
		super(student.getEmail(), student.getPassword(), authorityList);
		this.student = student;
	}

	public Student getAdmin() {
		return student;
	}

}
