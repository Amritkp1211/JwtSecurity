package com.jwtsecurity.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jwtsecurity.service.UserServiceImpl;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final UserServiceImpl userServiceImpl;

	public SecurityConfig(UserServiceImpl userServiceImpl) {
		super();
		this.userServiceImpl = userServiceImpl;
	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//		httpSecurity.csrf().disable()
//				.authorizeHttpRequests
//				(authz -> authz.requestMatchers("/api/users/**").hasRole("ADMIN")
//						.requestMatchers("/api/users/{id}").hasAnyRole("USER", "ADMIN")
//						.anyRequest().permitAll())
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		return httpSecurity.build();
//	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//	    httpSecurity.csrf().disable()
//	            .authorizeHttpRequests
//	            (authz -> authz.requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")
//	                    .anyRequest().permitAll())
//	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
		httpSecurity.csrf().disable()
	    .authorizeHttpRequests(authz -> authz
//	        .requestMatchers("/api/users/**").hasRole("ADMIN")
//	        .requestMatchers("/api/users/{id}").hasAnyRole("USER", "ADMIN")
	        .requestMatchers("/api/users/**").hasAuthority("ADMIN")
	        .requestMatchers("/api/users/{id}").hasAnyAuthority("USER", "ADMIN")
	        .anyRequest().permitAll())
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//	   
//	httpSecurity.csrf().disable()
//    .authorizeHttpRequests(authz -> authz
//        .requestMatchers("/api/users/**").permitAll() // Temporarily allow all users to create users
//        .anyRequest().permitAll())
//    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return httpSecurity.build();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
