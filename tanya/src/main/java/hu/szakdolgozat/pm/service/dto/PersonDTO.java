package hu.szakdolgozat.pm.service.dto;

import java.time.Instant;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonDTO {

	private Long id;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	private Instant dateOfBirth;

	private String placeOfBirth;

	@Column(name = "phone_number")
	private String phoneNumber;

	@NotBlank
	@Email
	private String email;

}
