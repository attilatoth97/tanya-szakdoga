package hu.szakdolgozat.pm.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import hu.szakdolgozat.pm.service.CalendarService;
import hu.szakdolgozat.pm.service.dto.CalendarDTO;
import hu.szakdolgozat.pm.service.dto.CalendarEditorDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CalendarResource {

	@Autowired
	private CalendarService calendarService;
	
	@PutMapping("/calendar")
	public ResponseEntity<CalendarDTO> create(@Valid @RequestBody CalendarEditorDTO dto) {
		return ResponseEntity.ok().body(calendarService.save(dto));
	}
	
	@PostMapping("/calendar")
	public ResponseEntity<CalendarDTO> update(@RequestParam Long id, @Valid @RequestBody CalendarEditorDTO dto) {
		return ResponseEntity.ok().body(calendarService.update(id, dto));
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
