package hu.szakdolgozat.tanya.service.dto;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data
public class SprintDTO {

	private Long id;

	private String sprintName;

	private Instant dateOfStart;

	private Instant dateOfEnd;

	private List<TaskMiniDTO> tasks;
}
