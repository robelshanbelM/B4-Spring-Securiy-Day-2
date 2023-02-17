package com.security.demo.SecurityConfig;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.demo.Model.UserInfo;
import com.security.demo.Repository.UserRepository;

public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<UserInfo>userinfo  = repository.findByName(username);
		
		
		return userinfo.map(UserInfoUserDetails :: new).orElseThrow(()->new UsernameNotFoundException("user is not found"));
	}

}
