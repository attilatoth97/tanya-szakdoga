package hu.szakdolgozat.pm.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectEditerDTO {

	private Long id;

	@NotBlank
	private String projectName;

	private String description;

	@NotNull
	private Long groupId;

}
