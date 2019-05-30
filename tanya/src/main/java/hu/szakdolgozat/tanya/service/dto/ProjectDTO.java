package hu.szakdolgozat.tanya.service.dto;

import lombok.Data;

@Data
public class ProjectDTO {

	private Long id;
	private String projectName;
	private String description;
	private String createrUsername;
	private long sprintNumber;
	private long taskNumber;
}
