package com.security.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.demo.Model.UserInfo;

public interface UserRepository  extends JpaRepository<UserInfo, Integer>{

	
	  Optional<UserInfo> findByName( String name  );
	
}
