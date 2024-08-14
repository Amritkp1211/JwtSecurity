package com.jwtsecurity.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	    @NotBlank(message = "Username is mandatory")
	    private String username;

	    @Email(message = "Email should be valid")
	    private String email;

	    @NotBlank(message = "Password is mandatory")
	    private String password;

	    private String role;  // Can be "USER" or "ADMIN"
	
}
