package hu.szakdolgozat.tanya.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.szakdolgozat.tanya.service.SprintService;
import hu.szakdolgozat.tanya.service.dto.SprintDTO;
import hu.szakdolgozat.tanya.service.dto.SprintEditerDTO;
import hu.szakdolgozat.tanya.service.dto.SprintMapDTO;

@RestController
@RequestMapping("/api")
public class SprintResource {


	@Autowired
	private SprintService sprintService;

	@PutMapping("/sprint")
	@ResponseBody
	public ResponseEntity<SprintDTO> create(@Valid @RequestBody SprintEditerDTO dto) {
		return ResponseEntity.ok().body(sprintService.save(dto));
	}

	@GetMapping("/sprint/{id}")
	public ResponseEntity<List<SprintDTO>> getTheProjectsSprints(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(sprintService.getSprintByProjectId(id));
	}

	@GetMapping("/sprint/map/{id}")
	public ResponseEntity<List<SprintMapDTO>> getTheProjectsMapSprints(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(sprintService.getAllSprint(id));
	}

}
