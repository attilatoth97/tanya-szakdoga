package hu.szakdolgozat.tanya.service.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class CalendarDTO {

	private Long id;
	
	private String title;
		
	private String projectName;
	
	private Instant date;
}
