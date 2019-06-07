package hu.szakdolgozat.tanya.service.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class CalendarEditorDTO {

	private Long id;
	
	private String title;
	
	private String description;
	
	private Long projectId;
	
	private Instant date;
}
