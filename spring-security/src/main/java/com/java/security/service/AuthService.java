package com.java.security.service;

import java.util.Optional;

import com.java.security.model.Role;
import org.modelmapper.ModelMapper;
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

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public AuthService(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;
	}

	public UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
		String encodedPassword = this.passwordEncoder.encode(userRegisterRequestDto.getPassword());
		User toBeRegister = this.modelMapper.map(userRegisterRequestDto, User.class);


		Role role=this.roleRepository.findByName(userRegisterRequestDto.getUsername()).get();

		toBeRegister.setPassword(encodedPassword);
		toBeRegister.setEnabled(true);



		toBeRegister.setRole();
		User savedUser = this.userRepository.save(toBeRegister);
		UserRegisterResponseDto registerResponseDto = new UserRegisterResponseDto(savedUser.getUsername(),
				"DATA_SAVED");

		return registerResponseDto;
	}

	public Boolean login(UserRegisterRequestDto registerRequestDto) {
		Optional<User> userOptional = userRepository.findByUsername(registerRequestDto.getUsername());

		User user = userOptional.get();

		String encodedPassword = user.getPassword();

		return passwordEncoder.matches(registerRequestDto.getPassword(), encodedPassword);
	}

}
