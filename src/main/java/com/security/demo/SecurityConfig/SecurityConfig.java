package com.security.demo.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	
	// Authentication
	
	@Bean
	public UserDetailsService  detailsService( ) {
		
		
//		UserDetails Admin= User.
//				withUsername("robel").
//				password(encoder.encode("12345")).
//				roles("ADMIN").
//				build();
//		
//		
//		UserDetails norlamUser= User.
//				withUsername("xyz").
//				password(encoder.encode("12345678")).
//				roles("USER").
//				build();
			
	
		return new UserInfoUserDetailsService();
		
		
	}
	
	
	// Authtorization
	
	@Bean
	public SecurityFilterChain securityFilterChain( HttpSecurity http) throws Exception  {
		
		
		return 
	     http 
	     .csrf().disable()
		.authorizeHttpRequests().requestMatchers("/adduser").permitAll()
		.and()
		.authorizeHttpRequests().requestMatchers("/hi","/alluser","/listuser").authenticated()
		.and()
		.formLogin()
		.and().build();
		
		
		
	}
	
	
	// Password Encode
	
	
	@Bean
	public PasswordEncoder encoder() {
		
		
		return new BCryptPasswordEncoder();
	}
	
	
	// Authentication Provider
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
		
		authenticationProvider.setUserDetailsService(detailsService());
		authenticationProvider.setPasswordEncoder(encoder());
		
		return authenticationProvider;
	}
	
}
