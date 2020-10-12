package hu.szakdolgozat.tanya.web.resource;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.szakdolgozat.tanya.service.ProjectService;
import hu.szakdolgozat.tanya.service.dto.ProjectDTO;
import hu.szakdolgozat.tanya.service.dto.ProjectEditerDTO;
import hu.szakdolgozat.tanya.service.dto.ProjectMapDTO;
import hu.szakdolgozat.tanya.service.dto.UserMiniDTO;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ProjectResource {

	@Autowired
	private ProjectService projectService;

	@PutMapping("/project")
	public ResponseEntity<ProjectDTO> create(@RequestBody ProjectEditerDTO dto) {
		return ResponseEntity.ok().body(projectService.save(dto));
	}

	@PostMapping("/project")
	public ResponseEntity<ProjectDTO> update(@RequestBody ProjectEditerDTO dto) {
		return ResponseEntity.ok().body(projectService.update(dto));
	}

	@GetMapping("/project/{id}")
	public ResponseEntity<Set<ProjectDTO>> getProjectsInGroup(@PathVariable("id") Long groupId) {
		return ResponseEntity.ok().body(projectService.getProjectsInGroup(groupId));
	}

// kell?
	@GetMapping("/project/name/{id}")
	public ResponseEntity<Set<String>> getProjectNamesInGroup(@PathVariable("id") Long groupId) {
		return ResponseEntity.ok().body(projectService.getProjectNamesInGroup(groupId));
	}

	@GetMapping("/project/{id}/user")
	public ResponseEntity<List<UserMiniDTO>> getMiniUserInGroup(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(projectService.getAllMiniUserInGroup(id));
	}
	
	@GetMapping("/project/map")
	public ResponseEntity<List<ProjectMapDTO>> getProjectMiniDTOOwn() {
		return ResponseEntity.ok().body(projectService.getProjectMiniDTOOwn());
	}


}
