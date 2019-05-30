package hu.szakdolgozat.tanya.service.dto;

import javax.validation.constraints.NotBlank;

import hu.szakdolgozat.tanya.entity.enumeration.IssueStatus;
import hu.szakdolgozat.tanya.entity.enumeration.IssueType;
import lombok.Data;

@Data
public class TaskEditorDTO {

	private Long id;

	@NotBlank
	private String issueName;

	private String description;

	private Long responsibleUserId;

	@NotBlank
	private IssueStatus issueStatus;

	@NotBlank
	private IssueType issueType;

	private Long sprintId;

	private Long projectId;

}
