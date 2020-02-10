package com.example.demo.security.instructor;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Instructor;
import com.example.demo.repository.InstructorRepositroy;

@Service
public class UserDetailsServiceImplByInstructor implements UserDetailsService{
	
	@Autowired
	private InstructorRepositroy instructorRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Instructor instructor = instructorRepository.findByEmail(email);
		if (instructor == null) {
			throw new UsernameNotFoundException("そのメールアドレスは登録されていません");
		}

		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"));
		return new LoginInstructor(instructor, authorityList);
	}

}
