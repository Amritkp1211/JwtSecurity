package com.jwtsecurity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jwtsecurity.payload.UserDTO;


public interface UserService {
	
	   UserDTO createUser(UserDTO userDTO);

	    UserDTO getUserById(Long id);

	    List<UserDTO> getAllUsers();

	    UserDTO updateUser(Long id, UserDTO userDTO);

	    void deleteUser(Long id);

}
