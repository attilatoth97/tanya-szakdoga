package hu.szakdolgozat.pm.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {

	private Long id;
	private String projectName;
	private String description;
	private String createrUsername;
	private long sprintNumber;
	private long taskNumber;
}
