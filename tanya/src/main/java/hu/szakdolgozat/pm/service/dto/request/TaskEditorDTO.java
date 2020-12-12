package hu.szakdolgozat.pm.service.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import hu.szakdolgozat.pm.entity.enumeration.IssueStatus;
import hu.szakdolgozat.pm.entity.enumeration.IssueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskEditorDTO {

	@NotBlank
	private String issueName;

	private String description;

	private Long responsibleUserId;

	@NotNull
	private IssueStatus issueStatus;

	@NotNull
	private IssueType issueType;

	@NotNull
	private Long sprintId;

	@NotNull
	private Long projectId;

	private Integer estimatedTime;

}
