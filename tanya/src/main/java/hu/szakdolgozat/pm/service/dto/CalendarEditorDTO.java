package hu.szakdolgozat.pm.service.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalendarEditorDTO {

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@NotNull
	private Long projectId;

	@NotNull
	private Instant date;
}
