package hu.szakdolgozat.pm.service.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SprintDTO {

	private Long id;

	private String sprintName;

	private Instant dateOfStart;

	private Instant dateOfEnd;

	private List<TaskMiniDTO> tasks;
}
