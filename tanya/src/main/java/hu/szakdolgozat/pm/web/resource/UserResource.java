package hu.szakdolgozat.pm.web.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.szakdolgozat.pm.security.UserUtil;
import hu.szakdolgozat.pm.service.UserService;
import hu.szakdolgozat.pm.service.dto.UserDTO;
import hu.szakdolgozat.pm.service.dto.UserEditerDTO;

@RestController
public class UserResource {

	private static final String API_PREFIX = "/api";

	@Autowired
	private UserService userService;

	@PutMapping("/registration")
	@ResponseBody
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserEditerDTO dto) {
		return ResponseEntity.ok().body(userService.save(dto));
	}

	@PostMapping(API_PREFIX + "/user")
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserEditerDTO dto) {
		return ResponseEntity.ok().body(userService.update(dto));
	}

	@GetMapping(API_PREFIX + "/user/{id}")
	public ResponseEntity<UserDTO> getUserDTO(@PathVariable Long id) {
		return ResponseEntity.ok().body(userService.getUserDTO(id));
	}

	@GetMapping(API_PREFIX + "/user")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		return ResponseEntity.ok().body(userService.getAllUser());
	}

	@GetMapping(API_PREFIX + "/logged-user")
	public ResponseEntity<UserEditerDTO> getLoggedUser() {
		return ResponseEntity.ok().body(userService.getLoggedEditerUser());
	}

	@GetMapping(API_PREFIX + "/user-logged")
	public ResponseEntity<Boolean> getUserLoggedIn() {
		return ResponseEntity.ok().body(userService.userLogged());
	}

	@GetMapping(API_PREFIX + "/getusername")
	public ResponseEntity<?> getUserName() {
		return ResponseEntity.ok().body(UserUtil.getAuthenticatedUser());
	}
}
