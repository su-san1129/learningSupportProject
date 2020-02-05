package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * ログイン認証用設定.
 * 
 * @author takahiro.suzuki
 *
 */
@Configuration
@EnableWebSecurity
@Order(3)
public class StudentSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/students/**").permitAll();
//		http.formLogin().loginPage("/login").loginProcessingUrl("/userLogin").defaultSuccessUrl("/successPath", true)
//				.usernameParameter("email").passwordParameter("password");
//		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout**")).logoutSuccessUrl("/")
//				.deleteCookies("JSESSIONID").invalidateHttpSession(true);

	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//	}

}
