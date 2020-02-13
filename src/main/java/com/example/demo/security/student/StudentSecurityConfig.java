package com.example.demo.security.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * ログイン認証用設定.
 * 
 * @author takahiro.suzuki
 *
 */
@Configuration
@EnableWebSecurity
@Order(4)
public class StudentSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImplByStudent userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**")
				.authorizeRequests()
				.antMatchers( "/students/login", "/students/register/**" )
				.permitAll()
				.anyRequest()
				.authenticated();
		http.formLogin()
				.loginPage("/students/login")
				.loginProcessingUrl("/students/studentLogin")
				.defaultSuccessUrl("/students/training_list", false)
				.usernameParameter("email")
				.passwordParameter("password");
		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/students/logout**"))
				.logoutSuccessUrl("/students/training_list")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
