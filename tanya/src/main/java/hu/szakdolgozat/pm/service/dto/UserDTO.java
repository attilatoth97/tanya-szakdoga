package hu.szakdolgozat.pm.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

	private Long id;
	
	private String userName;
	
	private PersonDTO person;
}
