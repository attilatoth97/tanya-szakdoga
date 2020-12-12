package hu.szakdolgozat.pm.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hu.szakdolgozat.pm.service.TaskService;
import hu.szakdolgozat.pm.service.dto.TaskDTO;
import hu.szakdolgozat.pm.service.dto.request.TaskEditorDTO;
import hu.szakdolgozat.pm.service.dto.TaskMiniDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/task")
public class TaskResource {

	@Autowired
	private TaskService taskService;

	@PutMapping
	public ResponseEntity<TaskDTO> create(@Valid @RequestBody TaskEditorDTO dto) {
		return ResponseEntity.ok().body(taskService.save(dto));
	}

	@PostMapping
	public ResponseEntity<TaskDTO> update(@RequestParam Long id, @Valid @RequestBody TaskEditorDTO dto) {
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
