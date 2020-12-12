package hu.szakdolgozat.pm.service.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SprintEditerDTO {

	private Long id;

	@NotBlank
	private String sprintName;

	private Instant dateOfStart;

	private Instant dateOfEnd;

	private Long projectId;

}
