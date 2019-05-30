package hu.szakdolgozat.tanya.service.dto;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	
	private String userName;
	
	private PersonDTO person;
}
