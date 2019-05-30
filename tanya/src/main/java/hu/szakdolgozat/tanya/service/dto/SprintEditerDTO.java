package hu.szakdolgozat.tanya.service.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SprintEditerDTO {

	private Long id;

	@NotBlank
	private String sprintName;

	private Instant dateOfStart;

	private Instant dateOfEnd;

	private Long projectId;

}
