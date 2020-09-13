package hu.szakdolgozat.tanya.resource;

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

import hu.szakdolgozat.tanya.security.UserUtil;
import hu.szakdolgozat.tanya.service.UserService;
import hu.szakdolgozat.tanya.service.dto.UserDTO;
import hu.szakdolgozat.tanya.service.dto.UserEditerDTO;

@RestController
public class UserResource {

	private static final String api = "/api";

	@Autowired
	private UserService userService;

	@PutMapping("/registration")
	@ResponseBody
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserEditerDTO dto) {
		return ResponseEntity.ok().body(userService.save(dto));
	}

	@PostMapping(api + "/user")
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserEditerDTO dto) {
		return ResponseEntity.ok().body(userService.update(dto));
	}

	@GetMapping(api + "/user/{id}")
	public ResponseEntity<UserDTO> getUserDTO(@PathVariable Long id) {
		return ResponseEntity.ok().body(userService.getUserDTO(id));
	}

	@GetMapping(api + "/user")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		return ResponseEntity.ok().body(userService.getAllUser());
	}

	@GetMapping(api + "/logged-user")
	public ResponseEntity<UserEditerDTO> getLoggedUser() {
		return ResponseEntity.ok().body(userService.getLoggedEditerUser());
	}

	@GetMapping(api + "/user-logged")
	public ResponseEntity<Boolean> getUserLoggedIn() {
		return ResponseEntity.ok().body(userService.userLogged());
	}

	@GetMapping(api + "/getusername")
	public ResponseEntity<?> getUserName() {
		return ResponseEntity.ok().body(UserUtil.getAuthenticatedUser());
	}
}
