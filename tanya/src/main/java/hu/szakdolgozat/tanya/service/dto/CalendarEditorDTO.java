package hu.szakdolgozat.tanya.service.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalendarEditorDTO {
	
	private String title;
	
	private String description;
	
	private Long projectId;
	
	private Instant date;
}
