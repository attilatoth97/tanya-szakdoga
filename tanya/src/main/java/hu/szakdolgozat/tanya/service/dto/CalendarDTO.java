package hu.szakdolgozat.tanya.service.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalendarDTO {

	private Long id;
	
	private String title;
		
	private String projectName;
	
	private Instant date;
}
