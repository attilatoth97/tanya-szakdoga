package hu.szakdolgozat.pm.service.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {

	@NotBlank
	private String userName;

	@NotBlank
	private String password;
}
