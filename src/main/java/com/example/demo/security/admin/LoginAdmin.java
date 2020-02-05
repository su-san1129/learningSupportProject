package com.example.demo.security.admin;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.domain.Admin;

public class LoginAdmin extends User{
	
	private static final long serialVersionUID = 1L;
	
	private final Admin admin;
	
	public LoginAdmin(Admin admin, Collection<GrantedAuthority> authorityList) {
		super(admin.getEmail(), admin.getPassword(), authorityList);
		this.admin = admin;
	}
	
	public Admin getAdmin() {
		return admin;
	}

}
