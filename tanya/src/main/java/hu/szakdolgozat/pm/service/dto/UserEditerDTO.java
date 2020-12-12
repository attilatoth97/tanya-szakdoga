package hu.szakdolgozat.pm.service.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditerDTO {

	private Long id;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String password;
	
	private PersonDTO person;
}
