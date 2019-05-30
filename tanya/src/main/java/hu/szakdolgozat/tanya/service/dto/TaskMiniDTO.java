package hu.szakdolgozat.tanya.service.dto;

import hu.szakdolgozat.tanya.entity.enumeration.IssueStatus;
import hu.szakdolgozat.tanya.entity.enumeration.IssueType;
import lombok.Data;

@Data
public class TaskMiniDTO {

	private Long id;

	private String issueName;

	private String createUserName;

	private String responsibleUserName;

	private IssueStatus issueStatus;

	private IssueType issueType;
}
