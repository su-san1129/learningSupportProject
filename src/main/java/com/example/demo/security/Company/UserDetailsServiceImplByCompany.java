package com.example.demo.security.Company;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.CompanyMember;
import com.example.demo.repository.CompanyMemberRepository;

@Service
public class UserDetailsServiceImplByCompany implements UserDetailsService{
	
	@Autowired
	private CompanyMemberRepository companyMemberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		CompanyMember comMember = companyMemberRepository.findByEmail(email);
		if (comMember == null) {
			throw new UsernameNotFoundException("そのメールアドレスは登録されていません");
		}

		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_COMPANY"));
		return new LoginComMember(comMember, authorityList);
	}

}
