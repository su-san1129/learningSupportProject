package com.example.demo.security.Company;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.domain.CompanyMember;

public class LoginComMember extends User {

	private static final long serialVersionUID = 1L;
	private final CompanyMember comMember;

	public LoginComMember(CompanyMember comMember, Collection<GrantedAuthority> authorityList) {
		super(comMember.getEmail(), comMember.getPassword(), authorityList);
		this.comMember = comMember;
	}

	public CompanyMember getStudent() {
		return comMember;
	}

}
