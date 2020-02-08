package com.example.demo.security.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(2)
public class CompanySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImplByCompany userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/company/**")
				.authorizeRequests()
				.antMatchers("/company/login").permitAll()
				.antMatchers("/company/**").hasRole("COMPANY")
				.anyRequest().authenticated();
		http.formLogin()
				.loginPage("/company/login")
				.loginProcessingUrl("/company/companyLogin")
				.defaultSuccessUrl("/company/training_list", true)
				.usernameParameter("email")
				.passwordParameter("password");
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout**")).logoutSuccessUrl("/company/login")
				.deleteCookies("JSESSIONID").invalidateHttpSession(true);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	

}
