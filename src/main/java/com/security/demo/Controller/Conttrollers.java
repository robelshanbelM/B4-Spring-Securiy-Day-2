package com.security.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.demo.Model.UserInfo;
import com.security.demo.Repository.UserRepository;

@RestController
public class Conttrollers {

	
	@Autowired
	UserRepository repository;
	
	@GetMapping("/homepage")
	public String hello() {
		
		
		return "Hello this is spring security class";
	}
	
	@GetMapping("/hi")
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String hi() {
		
		
		return "hi this is spring security class";
	}
	
	@GetMapping("/alluser")
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	
	public String fetchuserdata() {
		
		
		return "user information";
	}
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/adduser")
	
	public ResponseEntity<?>saveuser(@RequestBody UserInfo info){
		
		
		info.setPassword(encoder.encode(info.getPassword()) );
		
		repository.save(info);
		return new ResponseEntity<UserInfo>(info,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/listuser")
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	
	public ResponseEntity<?>listofuser(){
		
		
		return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
	}
	
}
