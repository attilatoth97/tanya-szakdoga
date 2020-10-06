package hu.szakdolgozat.tanya.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import hu.szakdolgozat.tanya.service.TaskService;
import hu.szakdolgozat.tanya.service.dto.TaskDTO;
import hu.szakdolgozat.tanya.service.dto.request.TaskEditorDTO;
import hu.szakdolgozat.tanya.service.dto.TaskMiniDTO;

@RestController
@RequestMapping("/api/task")
public class TaskResource {

	@Autowired
	private TaskService taskService;

	@PutMapping
	public ResponseEntity<TaskDTO> create(@Validated @RequestBody TaskEditorDTO dto) {
		return ResponseEntity.ok().body(taskService.save(dto));
	}

	@PostMapping
	public ResponseEntity<TaskDTO> update(@RequestParam Long id, @Validated @RequestBody TaskEditorDTO dto) {
		return ResponseEntity.ok().body(taskService.update(id, dto));
	}

	@GetMapping
	public ResponseEntity<TaskDTO> getTask(@RequestParam Long id) {
		return ResponseEntity.ok().body(taskService.getTask(id));
	}

	@GetMapping("/created")
	public ResponseEntity<List<TaskMiniDTO>> getAllOwnCreatedTask() {
		return ResponseEntity.ok().body(taskService.getAllOwnCreatedTask());
	}

	@GetMapping("/responsibled")
	public ResponseEntity<List<TaskMiniDTO>> getAllOwnResponsibledTask() {
		return ResponseEntity.ok().body(taskService.getAllOwnResponsibledTask());
	}

	@GetMapping("/project")
	public ResponseEntity<List<TaskMiniDTO>> getTasksByProjectId(@RequestParam Long projectId) {
		return ResponseEntity.ok(taskService.getTasksByProjectId(projectId));
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestParam Long id) {
		 taskService.delete(id);
		 return ResponseEntity.noContent().build();
	}
}
