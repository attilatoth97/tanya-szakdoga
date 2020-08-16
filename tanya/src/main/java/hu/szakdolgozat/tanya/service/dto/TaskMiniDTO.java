package hu.szakdolgozat.tanya.service.dto;

import hu.szakdolgozat.tanya.entity.enumeration.IssueStatus;
import hu.szakdolgozat.tanya.entity.enumeration.IssueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskMiniDTO {

	private Long id;

	private String issueName;

	private String createUserName;

	private String responsibleUserName;

	private IssueStatus issueStatus;

	private IssueType issueType;
}
