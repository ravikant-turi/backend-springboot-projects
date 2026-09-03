package com.java.security.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.security.dto.UserRegisterRequestDto;
import com.java.security.dto.UserRegisterResponseDto;
import com.java.security.model.User;
import com.java.security.repository.RoleRepository;
import com.java.security.repository.UserRepository;

@Service
public class AuthService {

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private ModelMapper modelMapper;

	
	private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

	public AuthService(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;
	}

	public UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
		String encodedPassword = this.passwordEncoder.encode(userRegisterRequestDto.getPassword());
		User toBeRegister = this.modelMapper.map(userRegisterRequestDto, User.class);

		toBeRegister.setPassword(encodedPassword);
		toBeRegister.setEnabled(true);
		User savedUser = this.userRepository.save(toBeRegister);
		UserRegisterResponseDto registerResponseDto=new UserRegisterResponseDto(savedUser.getUsername(),"DATA_SAVED");

		return registerResponseDto;
	}

}
