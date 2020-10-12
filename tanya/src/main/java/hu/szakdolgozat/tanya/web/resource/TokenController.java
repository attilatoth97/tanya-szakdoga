package hu.szakdolgozat.tanya.web.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.szakdolgozat.tanya.config.JwtUser;
import hu.szakdolgozat.tanya.entity.User;
import hu.szakdolgozat.tanya.security.JwtGenerator;
import hu.szakdolgozat.tanya.service.UserService;
import hu.szakdolgozat.tanya.service.dto.LoginDTO;

@RestController
@RequestMapping("/token")
public class TokenController {

	private JwtGenerator generator;

	@Autowired
	private UserService userService;

	public TokenController(JwtGenerator generator) {
		this.generator = generator;
	}

	@PostMapping
	public ResponseEntity<List<String>> getToken(@RequestBody @Valid LoginDTO login) {
		User user = userService.getUserByUserNameAndPassword(login.getUserName(), login.getPassword());
		JwtUser jwrUser = new JwtUser();
		jwrUser.setUserName(user.getUserName());
		jwrUser.setId(user.getId());
		jwrUser.setRole(user.getRole().stream().findFirst().toString());
		List<String> result = new ArrayList<String>();
		result.add(generator.generate(jwrUser));
		return ResponseEntity.ok(result);
	}
}
