package com.jwtsecurity.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtsecurity.entities.User;
import com.jwtsecurity.payload.UserDTO;
import com.jwtsecurity.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	  private final UserService userService;
	    private final ModelMapper modelMapper;

	    public UserController(UserService userService, ModelMapper modelMapper) {
	        this.userService = userService;
	        this.modelMapper = modelMapper;
	    }

	    @PostMapping
	    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
	    	UserDTO createdUser = userService.createUser(userDTO);
	        return new ResponseEntity<>(modelMapper.map(createdUser, UserDTO.class), HttpStatus.CREATED);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
	    	UserDTO user = userService.getUserById(id);
	        return new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.OK);
	    }

	    @GetMapping
	    public ResponseEntity<List<UserDTO>> getAllUsers() {
	        List<UserDTO> users = userService.getAllUsers();
	        return new ResponseEntity<>(users.stream()
	                .map(user -> modelMapper.map(user, UserDTO.class))
	                .toList(), HttpStatus.OK);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
	        
	    	UserDTO updatedUser = userService.updateUser(id, userDTO);
	        return new ResponseEntity<>(modelMapper.map(updatedUser, UserDTO.class), HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	        userService.deleteUser(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
