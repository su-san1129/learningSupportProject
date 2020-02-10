package com.example.demo.security.instructor;

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
@Order(4)
public class InstructorSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImplByInstructor userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/instructor/**")
				.authorizeRequests()
				.antMatchers("/instructor/login").permitAll()
				.antMatchers("/instructor/**").hasRole("INSTRUCTOR")
				.anyRequest().authenticated();
		http.formLogin()
				.loginPage("/instructor/login")
				.loginProcessingUrl("/instructor/instructorLogin")
				.defaultSuccessUrl("/instructor/training_list", true)
				.usernameParameter("email")
				.passwordParameter("password");
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/instructor/logout**")).logoutSuccessUrl("/company/login")
				.deleteCookies("JSESSIONID").invalidateHttpSession(true);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	

}
