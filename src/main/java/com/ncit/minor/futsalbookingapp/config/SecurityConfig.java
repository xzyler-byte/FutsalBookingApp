package com.ncit.minor.futsalbookingapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ncit.minor.futsalbookingapp.service.impl.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailService userDetailsService;
	private static final String[] PUBLIC_MATCHERS = { "/assets/**", "/css/**", "/images/**", "/", "/login/","/user/add/","/user/add/**","/dologin","/dologin/**"};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/book/**","/book").hasAuthority("USER")
				.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated().and().formLogin()
				.permitAll().loginPage("/login").loginProcessingUrl("/dologin").defaultSuccessUrl("/authenticated",true).and().logout()
				.permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/dologout", "POST")).logoutSuccessUrl("/");
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
}
