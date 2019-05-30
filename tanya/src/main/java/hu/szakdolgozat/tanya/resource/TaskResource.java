package hu.szakdolgozat.tanya.resource;

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

import hu.szakdolgozat.tanya.service.TaskService;
import hu.szakdolgozat.tanya.service.dto.TaskDTO;
import hu.szakdolgozat.tanya.service.dto.TaskEditorDTO;

@RestController
@RequestMapping("/api")
public class TaskResource {

	@Autowired
	private TaskService taskService;

	@PutMapping("/task")
	public ResponseEntity<TaskDTO> create(@Validated @RequestBody TaskEditorDTO dto) {
		return ResponseEntity.ok().body(taskService.save(dto));
	}

	@PostMapping("/task")
	public ResponseEntity<TaskDTO> update(@Validated @RequestBody TaskEditorDTO dto) {
		return ResponseEntity.ok().body(taskService.update(dto));
	}

	@GetMapping("/task/{id}")
	public ResponseEntity<TaskDTO> getTask(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(taskService.getTask(id));
	}
}
