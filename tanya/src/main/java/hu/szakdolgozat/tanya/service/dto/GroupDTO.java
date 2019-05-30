package hu.szakdolgozat.tanya.service.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class GroupDTO {

	private Long id;

	@NotBlank
	private String groupName;

	private String createdUserName;

}
