package hu.szakdolgozat.tanya.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.szakdolgozat.tanya.service.GroupService;
import hu.szakdolgozat.tanya.service.dto.GroupDTO;

@RestController
@RequestMapping("/api")
public class GroupResource {

	@Autowired
	private GroupService groupService;

	@PutMapping("/group")
	public ResponseEntity<GroupDTO> create(@RequestBody GroupDTO dto) {
		return ResponseEntity.ok().body(groupService.save(dto));
	}

	@PostMapping("/group")
	public ResponseEntity<GroupDTO> update(@RequestBody GroupDTO dto) {
		return ResponseEntity.ok().body(groupService.save(dto));
	}

	@GetMapping("/group/{id}")
	public ResponseEntity<GroupDTO> getGroup(@PathVariable Long id) {
		return ResponseEntity.ok().body(groupService.getGroup(id));
	}

	@DeleteMapping("/group/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		groupService.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/group/created")
	public ResponseEntity<List<GroupDTO>> getUserCreatedGroups() {
		return ResponseEntity.ok().body(groupService.getUserCreatedGroups());
	}

	@GetMapping("/group/attendanted")
	public ResponseEntity<List<GroupDTO>> getGroupsWhereUserAttendant() {
		return ResponseEntity.ok().body(groupService.getGroupsWhereUserAttendant());
	}

	@GetMapping("/group/{id}/user/{username}")
	public ResponseEntity<Void> addUserForGroup(@PathVariable("id") Long groupId,
			@PathVariable("username") String username) {
		groupService.addNewMemberForGroup(groupId, username);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/group/{gId}/user/{id}")
	public ResponseEntity<Void> kickFromGroup(@PathVariable("gId") Long groupId, @PathVariable("id") Long userId) {
		groupService.kickUserFromGroup(groupId, userId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/group/users/{id}")
	public ResponseEntity<List<String>> getUserNameinGroup(@PathVariable("id") Long groupId) {
		return ResponseEntity.ok().body(groupService.getUserNameinGroup(groupId));
	}
}
