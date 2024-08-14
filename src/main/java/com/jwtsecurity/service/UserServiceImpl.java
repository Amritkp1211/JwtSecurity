package com.jwtsecurity.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtsecurity.entities.User;
import com.jwtsecurity.entities.UserDetailsImpl;
import com.jwtsecurity.exception.ResourceNotFound;
import com.jwtsecurity.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
             
		 User user = userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFound("user not found"));
	//syso
		return new UserDetailsImpl(user);
	}

}
