package hu.szakdolgozat.tanya.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.szakdolgozat.tanya.service.CalendarService;
import hu.szakdolgozat.tanya.service.dto.CalendarDTO;
import hu.szakdolgozat.tanya.service.dto.CalendarEditorDTO;

@RestController
@RequestMapping("/api")
public class CalendarResource {

	@Autowired
	private CalendarService calendarService;
	
	@PutMapping("/calendar")
	public ResponseEntity<CalendarDTO> create(@Validated @RequestBody CalendarEditorDTO dto) {
		return ResponseEntity.ok().body(calendarService.save(dto));
	}
	
	@PostMapping("/calendar")
	public ResponseEntity<CalendarDTO> update(@Validated @RequestBody CalendarEditorDTO dto) {
		return ResponseEntity.ok().body(calendarService.update(dto));
	}
	
	@GetMapping("/calendar/project/{id}")
	public ResponseEntity<List<CalendarDTO>> getCalendarDates(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(calendarService.findAllProjectCalendars(id));
	}
	
	@GetMapping("/calendar/{id}")
	public ResponseEntity<CalendarDTO>getCalendarDate(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(calendarService.getCalendarDate(id));
	}
	 
}
