package hu.szakdolgozat.tanya.service.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDTO {

	@NotBlank
	private String userName;

	@NotBlank
	private String password;
}
