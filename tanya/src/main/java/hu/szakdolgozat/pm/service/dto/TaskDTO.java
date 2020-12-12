package hu.szakdolgozat.pm.service.dto;

import java.time.Instant;

import hu.szakdolgozat.pm.entity.enumeration.IssueStatus;
import hu.szakdolgozat.pm.entity.enumeration.IssueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDTO {

	private Long id;

	private String issueName;

	private Instant dateOfCreate;

	private String createUserName;

	private String description;

	private Long responsibleUserId;

	private String responsibleUserName;

	private Boolean isClose;

	private IssueStatus issueStatus;

	private IssueType issueType;

	private Long sprintId;

	private Long projectId;

	private String projectName;

	private String sprintName;

	private Instant dateOfLastRevisal;

	private Integer estimatedTime;
}
