package com.jwtsecurity.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtsecurity.entities.Role;
import com.jwtsecurity.entities.User;
import com.jwtsecurity.exception.ResourceNotFound;
import com.jwtsecurity.payload.UserDTO;
import com.jwtsecurity.repository.UserRepository;
import com.jwtsecurity.service.UserService;

@Service
public class UserServiceImpldto implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;

	public UserServiceImpldto(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.valueOf(userDTO.getRole().toUpperCase())); // Convert String to Role enum
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserDTO.class);
	}

	@Override
	public UserDTO getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("User not found with id: " + id));
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
	}

	@Override
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("User not found with id: " + id));

		// Update fields
		existingUser.setUsername(userDTO.getUsername());
		existingUser.setEmail(userDTO.getEmail());
		if (!userDTO.getPassword().equals(existingUser.getPassword())) {
			existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		}
		existingUser.setRole(Role.valueOf(userDTO.getRole().toUpperCase()));

		User updatedUser = userRepository.save(existingUser);
		return modelMapper.map(updatedUser, UserDTO.class);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
