package com.example.demo.security.instructor;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.domain.Instructor;

public class LoginInstructor extends User {

	private static final long serialVersionUID = 1L;
	private final Instructor instructor;

	public LoginInstructor(Instructor instructor, Collection<GrantedAuthority> authorityList) {
		super(instructor.getEmail(), instructor.getPassword(), authorityList);
		this.instructor = instructor;
	}

	public Instructor getStudent() {
		return instructor;
	}

}
