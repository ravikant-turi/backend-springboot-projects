package com.java.security.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegisterRequestDto {

	@Length(max = 100 , min =10, message = "it should be between 10 to 100")
	@NotNull
	private String username;
	@NotNull
	private String password;

}
