package hu.szakdolgozat.tanya.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProjectEditerDTO {

	private Long id;

	@NotBlank
	private String projectName;

	private String description;

	@NotNull
	private Long groupId;

}
